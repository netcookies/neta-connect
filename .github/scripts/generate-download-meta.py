#!/usr/bin/env python3
from __future__ import annotations

import argparse
import hashlib
import json
import math
import re
import subprocess
import sys
from pathlib import Path
from urllib.parse import quote

SPLIT_THRESHOLD_BYTES = 20 * 1024 * 1024
PART_SIZE_BYTES = 19 * 1024 * 1024
PART_SUFFIX_RE = re.compile(r"\.part\d{3}(?:\.sha256)?$")
MANAGED_ROOTS = ("archive", "models", "widgets")


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Generate .meta files and split parts for downloadable assets."
    )
    parser.add_argument(
        "--repo-root",
        default=".",
        help="Repository root directory. Defaults to current directory.",
    )
    parser.add_argument(
        "--repo-slug",
        required=True,
        help="GitHub repository slug, e.g. netcookies/neta-connect.",
    )
    parser.add_argument(
        "--branch",
        required=True,
        help="Branch name used to build raw.githubusercontent.com URLs.",
    )
    parser.add_argument(
        "--mode",
        choices=("all", "changed"),
        default="changed",
        help="Whether to scan all assets or only git-changed assets.",
    )
    parser.add_argument(
        "--base",
        default="",
        help="Base commit SHA for changed mode.",
    )
    parser.add_argument(
        "--head",
        default="HEAD",
        help="Head commit SHA for changed mode.",
    )
    parser.add_argument(
        "--dry-run",
        action="store_true",
        help="Compute actions without writing files.",
    )
    return parser.parse_args()


def rel_posix(path: Path) -> str:
    return path.as_posix()


def is_generated_path(rel_path: Path) -> bool:
    name = rel_path.name
    return (
        name.endswith(".meta")
        or name.endswith(".sha256")
        or PART_SUFFIX_RE.search(name) is not None
    )


def is_control_path(rel_path: Path) -> bool:
    posix = rel_posix(rel_path)
    if rel_path.name.startswith("."):
        return True
    if rel_path.name == ".DS_Store":
        return True
    if rel_path.suffix.lower() == ".md":
        return True
    if posix in {"models/index.json", "widgets/index.json"}:
        return True
    if rel_path.parts and rel_path.parts[0] == "widgets" and rel_path.name in {
        "metadata.json",
        "version.json",
    }:
        return True
    return False


def is_managed_asset_path(rel_path: Path) -> bool:
    if not rel_path.parts or rel_path.parts[0] not in MANAGED_ROOTS:
        return False
    if is_generated_path(rel_path) or is_control_path(rel_path):
        return False

    root = rel_path.parts[0]
    if root == "widgets":
        return rel_path.suffix.lower() == ".jar"

    if root == "archive":
        return True

    if root == "models":
        return True

    return False


def canonical_url(repo_slug: str, branch: str, rel_path: Path) -> str:
    quoted = quote(rel_posix(rel_path), safe="/")
    return f"https://raw.githubusercontent.com/{repo_slug}/{branch}/{quoted}"


def sha256_file(path: Path) -> str:
    digest = hashlib.sha256()
    with path.open("rb") as handle:
        for chunk in iter(lambda: handle.read(1024 * 1024), b""):
            digest.update(chunk)
    return digest.hexdigest()


def read_json(path: Path) -> dict | None:
    if not path.is_file():
        return None
    try:
        return json.loads(path.read_text(encoding="utf-8"))
    except Exception:
        return None


def json_text(payload: dict) -> str:
    return json.dumps(payload, ensure_ascii=False, indent=2) + "\n"


def write_text_if_changed(path: Path, content: str, dry_run: bool) -> bool:
    if path.is_file() and path.read_text(encoding="utf-8") == content:
        return False
    if not dry_run:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(content, encoding="utf-8")
    return True


def write_bytes_if_changed(path: Path, content: bytes, dry_run: bool) -> bool:
    if path.is_file() and path.read_bytes() == content:
        return False
    if not dry_run:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_bytes(content)
    return True


def remove_path(path: Path, dry_run: bool) -> bool:
    if not path.exists():
        return False
    if not dry_run:
        path.unlink()
    return True


def list_generated_sidecars(asset_path: Path) -> list[Path]:
    pattern = re.compile(rf"^{re.escape(asset_path.name)}\.part\d{{3}}(?:\.sha256)?$")
    sidecars: list[Path] = []
    meta_path = asset_path.with_name(f"{asset_path.name}.meta")
    if meta_path.exists():
        sidecars.append(meta_path)
    if asset_path.parent.exists():
        for child in asset_path.parent.iterdir():
            if pattern.match(child.name):
                sidecars.append(child)
    return sorted(set(sidecars))


def remove_generated_sidecars(asset_path: Path, dry_run: bool) -> list[Path]:
    removed: list[Path] = []
    for sidecar in list_generated_sidecars(asset_path):
        if remove_path(sidecar, dry_run):
            removed.append(sidecar)
    return removed


def split_asset(
    asset_path: Path,
    rel_path: Path,
    repo_slug: str,
    branch: str,
    dry_run: bool,
) -> tuple[list[dict], list[Path], str]:
    total_size = asset_path.stat().st_size
    total_digest = hashlib.sha256()
    part_count = max(1, math.ceil(total_size / PART_SIZE_BYTES))
    expected_paths: list[Path] = []
    parts: list[dict] = []

    with asset_path.open("rb") as source:
        for index in range(part_count):
            remaining = min(PART_SIZE_BYTES, total_size - index * PART_SIZE_BYTES)
            buffer = source.read(remaining)
            if len(buffer) != remaining:
                raise RuntimeError(
                    f"Unexpected EOF while splitting {asset_path} at part {index:03d}"
                )
            total_digest.update(buffer)

            part_name = f"{asset_path.name}.part{index:03d}"
            part_path = asset_path.with_name(part_name)
            part_sha = hashlib.sha256(buffer).hexdigest()
            part_url = canonical_url(repo_slug, branch, rel_path.with_name(part_name))
            write_bytes_if_changed(part_path, buffer, dry_run)
            expected_paths.append(part_path)

            parts.append(
                {
                    "index": index + 1,
                    "filename": part_name,
                    "url": part_url,
                    "size": len(buffer),
                    "sha256": part_sha,
                }
            )

    return parts, expected_paths, total_digest.hexdigest()


def build_split_meta(asset_name: str, asset_url: str, total_size: int, total_sha256: str, parts: list[dict]) -> dict:
    return {
        "filename": asset_name,
        "url": asset_url,
        "total_size": total_size,
        "total_sha256": total_sha256,
        "part_count": len(parts),
        "part_size": PART_SIZE_BYTES,
        "parts": parts,
    }


def build_unsplit_meta(asset_name: str, asset_url: str, total_size: int, total_sha256: str) -> dict:
    return {
        "version": 1,
        "filename": asset_name,
        "url": asset_url,
        "full_file_available": True,
        "total_size": total_size,
        "total_sha256": total_sha256,
        "part_count": 1,
        "part_size": total_size,
        "parts": [
            {
                "index": 1,
                "filename": f"{asset_name}.part000",
                "url": asset_url,
                "size": total_size,
                "sha256": total_sha256,
            }
        ],
    }


def split_meta_is_current(meta: dict | None, total_size: int, total_sha256: str, expected_part_count: int) -> bool:
    if not meta:
        return False
    if meta.get("total_size") != total_size:
        return False
    if str(meta.get("total_sha256", "")).strip().lower() != total_sha256:
        return False
    part_count = meta.get("part_count", 0)
    if isinstance(part_count, int) and part_count > 0 and part_count != expected_part_count:
        return False
    parts = meta.get("parts")
    return isinstance(parts, list) and len(parts) == expected_part_count


def unsplit_meta_is_current(meta: dict | None, total_size: int, total_sha256: str) -> bool:
    if not meta:
        return False
    if meta.get("total_size") != total_size:
        return False
    if str(meta.get("total_sha256", "")).strip().lower() != total_sha256:
        return False
    parts = meta.get("parts")
    return isinstance(parts, list) and len(parts) == 1


def scan_all_assets(repo_root: Path) -> list[Path]:
    assets: list[Path] = []
    for root_name in MANAGED_ROOTS:
        root = repo_root / root_name
        if not root.is_dir():
            continue
        for file_path in root.rglob("*"):
            if not file_path.is_file():
                continue
            rel_path = file_path.relative_to(repo_root)
            if is_managed_asset_path(rel_path):
                assets.append(rel_path)
    return sorted(set(assets), key=rel_posix)


def commit_exists(repo_root: Path, sha: str) -> bool:
    if not sha or re.fullmatch(r"0+", sha):
        return False
    result = subprocess.run(
        ["git", "-C", str(repo_root), "cat-file", "-e", f"{sha}^{{commit}}"],
        stdout=subprocess.DEVNULL,
        stderr=subprocess.DEVNULL,
        check=False,
    )
    return result.returncode == 0


def changed_assets(repo_root: Path, base: str, head: str) -> list[Path]:
    if not commit_exists(repo_root, base) or not commit_exists(repo_root, head):
        return scan_all_assets(repo_root)

    output = subprocess.check_output(
        [
            "git",
            "-C",
            str(repo_root),
            "diff",
            "--name-status",
            "--find-renames",
            base,
            head,
            "--",
            "archive",
            "models",
            "widgets",
        ],
        text=True,
    )

    to_process: set[Path] = set()

    for raw_line in output.splitlines():
        if not raw_line.strip():
            continue
        parts = raw_line.split("\t")
        status = parts[0]
        code = status[0]

        if code in {"R", "C"} and len(parts) >= 3:
            new_path = Path(parts[2])
            if is_managed_asset_path(new_path):
                to_process.add(new_path)
            continue

        if len(parts) < 2:
            continue

        rel_path = Path(parts[1])
        if code != "D" and is_managed_asset_path(rel_path):
            to_process.add(rel_path)

    return sorted(to_process, key=rel_posix)


def process_asset(
    repo_root: Path,
    rel_path: Path,
    repo_slug: str,
    branch: str,
    dry_run: bool,
) -> tuple[list[Path], list[Path]]:
    asset_path = repo_root / rel_path
    if not asset_path.is_file():
        return [], []

    total_size = asset_path.stat().st_size
    total_sha256 = sha256_file(asset_path)
    asset_url = canonical_url(repo_slug, branch, rel_path)
    meta_path = asset_path.with_name(f"{asset_path.name}.meta")
    existing_meta = read_json(meta_path)

    written: list[Path] = []
    removed: list[Path] = []

    if total_size > SPLIT_THRESHOLD_BYTES:
        expected_part_count = max(1, math.ceil(total_size / PART_SIZE_BYTES))
        if split_meta_is_current(existing_meta, total_size, total_sha256, expected_part_count):
            current_parts = []
            for index in range(expected_part_count):
                current_parts.append(asset_path.with_name(f"{asset_path.name}.part{index:03d}"))
            if all(part_path.is_file() for part_path in current_parts):
                return written, removed

        parts, part_paths, split_total_sha = split_asset(
            asset_path=asset_path,
            rel_path=rel_path,
            repo_slug=repo_slug,
            branch=branch,
            dry_run=dry_run,
        )
        expected = set(part_paths)
        for sidecar in list_generated_sidecars(asset_path):
            if sidecar == meta_path:
                continue
            if sidecar not in expected and remove_path(sidecar, dry_run):
                removed.append(sidecar)

        for path in part_paths:
            if path.exists() or dry_run:
                written.append(path)

        meta_payload = build_split_meta(
            asset_name=asset_path.name,
            asset_url=asset_url,
            total_size=total_size,
            total_sha256=split_total_sha,
            parts=parts,
        )
        if write_text_if_changed(meta_path, json_text(meta_payload), dry_run):
            written.append(meta_path)
        return sorted(set(written), key=lambda p: p.as_posix()), sorted(set(removed), key=lambda p: p.as_posix())

    for sidecar in list_generated_sidecars(asset_path):
        if sidecar == meta_path:
            continue
        if remove_path(sidecar, dry_run):
            removed.append(sidecar)

    if unsplit_meta_is_current(existing_meta, total_size, total_sha256):
        return written, removed

    meta_payload = build_unsplit_meta(
        asset_name=asset_path.name,
        asset_url=asset_url,
        total_size=total_size,
        total_sha256=total_sha256,
    )
    if write_text_if_changed(meta_path, json_text(meta_payload), dry_run):
        written.append(meta_path)
    return sorted(set(written), key=lambda p: p.as_posix()), sorted(set(removed), key=lambda p: p.as_posix())


def main() -> int:
    args = parse_args()
    repo_root = Path(args.repo_root).resolve()

    if args.mode == "all":
        targets = scan_all_assets(repo_root)
    else:
        targets = changed_assets(repo_root, args.base, args.head)

    written_paths: list[Path] = []
    removed_paths: list[Path] = []

    for rel_path in targets:
        written, removed = process_asset(
            repo_root=repo_root,
            rel_path=rel_path,
            repo_slug=args.repo_slug,
            branch=args.branch,
            dry_run=args.dry_run,
        )
        written_paths.extend(written)
        removed_paths.extend(removed)

    summary = {
        "mode": args.mode,
        "dry_run": args.dry_run,
        "processed_assets": [rel_posix(path) for path in targets],
        "written_files": sorted({rel_posix(path.relative_to(repo_root)) for path in written_paths}),
        "removed_files": sorted({rel_posix(path.relative_to(repo_root)) for path in removed_paths}),
    }
    print(json.dumps(summary, ensure_ascii=False, indent=2))
    return 0


if __name__ == "__main__":
    sys.exit(main())
