import {existsSync, mkdirSync, readdirSync, readFileSync, statSync, writeFileSync} from 'node:fs';
import path from 'node:path';
import {fileURLToPath} from 'node:url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const SITE_ROOT = path.resolve(__dirname, '..');
const REPO_ROOT = path.resolve(SITE_ROOT, '..');
const ARCHIVE_DIR = path.join(REPO_ROOT, 'archive');
const OUTPUT_DIR = path.join(SITE_ROOT, 'src', 'data');
const OUTPUT_FILE = path.join(OUTPUT_DIR, 'release-data.generated.json');
const CHANGELOG_SOURCE = path.join(REPO_ROOT, 'CHANGELOG.md');
const CHANGELOG_TARGET = path.join(SITE_ROOT, 'docs', 'changelog.md');

const REPO_SLUG = 'netcookies/neta-connect';
const REPO_BRANCH = 'main';
const PROXY_PREFIX = 'https://cdn.gh-proxy.org/';

function proxify(url) {
  return `${PROXY_PREFIX}${url}`;
}

function buildArchiveRawUrl(filename) {
  return `https://raw.githubusercontent.com/${REPO_SLUG}/${REPO_BRANCH}/archive/${encodeURIComponent(filename)}`;
}

function writeTextIfChanged(filePath, nextText) {
  const currentText = existsSync(filePath) ? readFileSync(filePath, 'utf8') : null;
  if (currentText === nextText) {
    return false;
  }
  writeFileSync(filePath, nextText, 'utf8');
  return true;
}

function parseVersion(version) {
  const normalized = version.replace(/^v/i, '');
  const [coreText, preText = ''] = normalized.split('-', 2);
  const core = coreText.split('.').map((part) => Number.parseInt(part, 10) || 0);
  const prerelease = preText ? preText.split('.').map((part) => {
    const numeric = Number.parseInt(part, 10);
    return Number.isNaN(numeric) ? part : numeric;
  }) : [];
  return {core, prerelease};
}

function compareIdentifiers(left, right) {
  const leftIsNumber = typeof left === 'number';
  const rightIsNumber = typeof right === 'number';
  if (leftIsNumber && rightIsNumber) {
    return left - right;
  }
  if (leftIsNumber) {
    return -1;
  }
  if (rightIsNumber) {
    return 1;
  }
  return String(left).localeCompare(String(right));
}

function compareVersions(leftVersion, rightVersion) {
  const left = parseVersion(leftVersion);
  const right = parseVersion(rightVersion);

  for (let index = 0; index < Math.max(left.core.length, right.core.length); index += 1) {
    const diff = (left.core[index] || 0) - (right.core[index] || 0);
    if (diff !== 0) {
      return diff;
    }
  }

  if (left.prerelease.length === 0 && right.prerelease.length === 0) {
    return 0;
  }
  if (left.prerelease.length === 0) {
    return 1;
  }
  if (right.prerelease.length === 0) {
    return -1;
  }

  for (let index = 0; index < Math.max(left.prerelease.length, right.prerelease.length); index += 1) {
    const leftId = left.prerelease[index];
    const rightId = right.prerelease[index];
    if (leftId === undefined) {
      return -1;
    }
    if (rightId === undefined) {
      return 1;
    }
    const diff = compareIdentifiers(leftId, rightId);
    if (diff !== 0) {
      return diff;
    }
  }

  return 0;
}

function resolveLatestVersion() {
  const metaFiles = readdirSync(ARCHIVE_DIR)
    .filter((name) => name.startsWith('NETA_CONNECT-v') && name.endsWith('.apk.meta'))
    .map((name) => name
      .replace('NETA_CONNECT-', '')
      .replace('.apk.meta', ''))
    .sort((left, right) => compareVersions(right, left));

  if (metaFiles.length === 0) {
    throw new Error('未找到任何 archive/*.apk.meta 文件，无法生成 release 数据。');
  }

  return metaFiles[0];
}

function resolveVersion() {
  const explicitVersion = (process.env.RELEASE_VERSION || process.argv[2] || '').trim();
  return explicitVersion || resolveLatestVersion();
}

function buildAsset(kind, payload) {
  const originalUrl = payload.originalUrl || buildArchiveRawUrl(payload.filename);
  return {
    kind,
    ...payload,
    originalUrl,
    url: proxify(originalUrl),
  };
}

function validateMeta(meta, version) {
  if (!meta || typeof meta !== 'object') {
    throw new Error(`版本 ${version} 的 meta 不是有效对象。`);
  }
  if (!meta.filename || typeof meta.filename !== 'string') {
    throw new Error(`版本 ${version} 缺少有效的 filename。`);
  }
  if (!Number.isFinite(meta.total_size) || meta.total_size <= 0) {
    throw new Error(`版本 ${version} 的 total_size 非法。`);
  }
  if (!meta.total_sha256 || typeof meta.total_sha256 !== 'string') {
    throw new Error(`版本 ${version} 缺少有效的 total_sha256。`);
  }
  if (!Array.isArray(meta.parts) || meta.parts.length === 0) {
    throw new Error(`版本 ${version} 缺少有效的 parts。`);
  }

  meta.parts.forEach((part, index) => {
    if (!part.filename || typeof part.filename !== 'string') {
      throw new Error(`版本 ${version} 的第 ${index + 1} 个分片缺少 filename。`);
    }
    if (!Number.isFinite(part.size) || part.size <= 0) {
      throw new Error(`版本 ${version} 的第 ${index + 1} 个分片 size 非法。`);
    }
    if (!part.sha256 || typeof part.sha256 !== 'string') {
      throw new Error(`版本 ${version} 的第 ${index + 1} 个分片缺少 sha256。`);
    }
  });
}

function main() {
  const version = resolveVersion();
  const metaFileName = `NETA_CONNECT-${version}.apk.meta`;
  const metaFilePath = path.join(ARCHIVE_DIR, metaFileName);

  if (!existsSync(metaFilePath)) {
    throw new Error(`未找到当前版本的元数据文件：${metaFileName}`);
  }

  const meta = JSON.parse(readFileSync(metaFilePath, 'utf8'));
  validateMeta(meta, version);
  const metaMtime = statSync(metaFilePath).mtime.toISOString();
  const partAssets = Array.isArray(meta.parts) && meta.parts.length > 1
    ? meta.parts.map((part) => buildAsset('part', {
        label: `分片 ${part.index}/${meta.parts.length}`,
        filename: part.filename,
        originalUrl: part.url,
        size: part.size,
        sha256: part.sha256,
        partIndex: part.index,
      }))
    : [];

  const data = {
    generatedAt: metaMtime,
    version,
    repoSlug: REPO_SLUG,
    proxyPrefix: PROXY_PREFIX,
    releasePath: '/release',
    githubReleaseUrl: `https://github.com/${REPO_SLUG}/releases/tag/${encodeURIComponent(version)}`,
    changelogPath: '/docs/changelog',
    installGuidePath: '/docs/install',
    summary: {
      filename: meta.filename,
      totalSize: meta.total_size,
      totalSha256: meta.total_sha256,
      partCount: partAssets.length || 1,
    },
    assets: [
      buildAsset('full', {
        label: '完整 APK',
        filename: meta.filename,
        originalUrl: meta.url,
        size: meta.total_size,
        sha256: meta.total_sha256,
      }),
      ...partAssets,
    ],
  };

  mkdirSync(OUTPUT_DIR, {recursive: true});
  writeTextIfChanged(OUTPUT_FILE, `${JSON.stringify(data, null, 2)}\n`);

  if (existsSync(CHANGELOG_SOURCE)) {
    writeTextIfChanged(CHANGELOG_TARGET, readFileSync(CHANGELOG_SOURCE, 'utf8'));
  }

  console.log(`已生成 release 数据：${path.relative(REPO_ROOT, OUTPUT_FILE)} -> ${version}`);
}

main();
