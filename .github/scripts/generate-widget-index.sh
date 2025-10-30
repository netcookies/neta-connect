#!/bin/bash
set -e

WIDGETS_DIR="widgets"
INDEX_FILE="$WIDGETS_DIR/index.json"
TIMESTAMP=$(date -u +"%Y-%m-%dT%H:%M:%SZ")

echo "🔍 扫描 widgets 目录..."

# 开始构建 JSON
cat > "$INDEX_FILE" << 'EOF'
{
  "version": "1.0.0",
  "generatedAt": "TIMESTAMP_PLACEHOLDER",
  "widgets": [
EOF

FIRST_WIDGET=true

# 遍历所有小组件目录（排除 index.json 等文件）
for widget_dir in "$WIDGETS_DIR"/*; do
  if [ ! -d "$widget_dir" ]; then
    continue
  fi

  widget_name=$(basename "$widget_dir")
  metadata_file="$widget_dir/metadata.json"

  # 跳过没有 metadata.json 的目录
  if [ ! -f "$metadata_file" ]; then
    echo "⚠️  跳过 $widget_name (没有 metadata.json)"
    continue
  fi

  echo "📦 处理小组件: $widget_name"

  # 读取 metadata.json
  if ! jq empty "$metadata_file" 2>/dev/null; then
    echo "❌ $widget_name/metadata.json 格式错误，跳过"
    continue
  fi

  WIDGET_ID=$(jq -r '.id' "$metadata_file")
  DISPLAY_NAME=$(jq -r '.displayName' "$metadata_file")
  DESCRIPTION=$(jq -r '.description' "$metadata_file")
  AUTHOR=$(jq -r '.author' "$metadata_file")
  CATEGORY=$(jq -r '.category' "$metadata_file")
  MIN_APP_VERSION=$(jq -r '.minAppVersion' "$metadata_file")

  # 第一步：收集所有有效的版本号
  declare -a valid_versions=()

  for version_dir in "$widget_dir"/*; do
    if [ ! -d "$version_dir" ]; then
      continue
    fi

    version=$(basename "$version_dir")

    # 检查是否是有效的版本号格式（x.y.z）
    if ! [[ "$version" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
      continue
    fi

    # 查找 JAR 文件
    jar_file=$(find "$version_dir" -name "*.jar" -type f | head -1)

    if [ -z "$jar_file" ]; then
      echo "  ⚠️  版本 $version 没有 JAR 文件"
      continue
    fi

    valid_versions+=("$version")
  done

  # 对版本号进行排序(降序)，找到最新版本
  LATEST_VERSION=""
  if [ ${#valid_versions[@]} -gt 0 ]; then
    LATEST_VERSION=$(printf '%s\n' "${valid_versions[@]}" | sort -V -r | head -1)
    echo "  🔝 最新版本: $LATEST_VERSION"
  fi

  # 第二步：处理每个版本的详细信息
  VERSIONS_JSON="["
  FIRST_VERSION=true

  for version_dir in "$widget_dir"/*; do
    if [ ! -d "$version_dir" ]; then
      continue
    fi

    version=$(basename "$version_dir")

    # 检查是否是有效的版本号格式（x.y.z）
    if ! [[ "$version" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
      continue
    fi

    # 查找 JAR 文件
    jar_file=$(find "$version_dir" -name "*.jar" -type f | head -1)

    if [ -z "$jar_file" ]; then
      continue
    fi

    echo "  ✓ 处理版本: $version"

    # 获取文件信息
    FILE_SIZE=$(stat -f%z "$jar_file" 2>/dev/null || stat -c%s "$jar_file" 2>/dev/null)
    SHA256=$(shasum -a 256 "$jar_file" | awk '{print $1}')

    # 构建下载 URL
    DOWNLOAD_URL="https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/$widget_name/$version/$(basename "$jar_file")"

    # 从git历史获取真实的发布日期（JAR文件首次提交的日期）
    RELEASE_DATE=$(git log --format=%aI --diff-filter=A -- "$jar_file" | tail -1 | cut -d'T' -f1)
    # 如果git历史中没有找到（比如新文件未提交），使用当前日期
    if [ -z "$RELEASE_DATE" ]; then
      RELEASE_DATE=$(date -u +"%Y-%m-%d")
    fi

    # 检查版本目录下是否有version.json，优先使用版本特定的minAppVersion
    VERSION_METADATA="$version_dir/version.json"
    if [ -f "$VERSION_METADATA" ] && jq empty "$VERSION_METADATA" 2>/dev/null; then
      VERSION_MIN_APP=$(jq -r '.minAppVersion // empty' "$VERSION_METADATA")
      if [ -n "$VERSION_MIN_APP" ] && [ "$VERSION_MIN_APP" != "null" ]; then
        VERSION_SPECIFIC_MIN_APP="$VERSION_MIN_APP"
      else
        VERSION_SPECIFIC_MIN_APP="$MIN_APP_VERSION"
      fi
    else
      VERSION_SPECIFIC_MIN_APP="$MIN_APP_VERSION"
    fi

    # 添加版本信息
    if [ "$FIRST_VERSION" = true ]; then
      FIRST_VERSION=false
    else
      VERSIONS_JSON+=","
    fi

    VERSIONS_JSON+='
    {
      "version": "'"$version"'",
      "releaseDate": "'"$RELEASE_DATE"'",
      "downloadUrl": "'"$DOWNLOAD_URL"'",
      "changelog": "版本 '"$version"'",
      "minAppVersion": "'"$VERSION_SPECIFIC_MIN_APP"'",
      "fileSize": '"$FILE_SIZE"',
      "sha256": "'"$SHA256"'"
    }'
  done

  VERSIONS_JSON+="]"

  # 如果没有找到任何版本，跳过这个小组件
  if [ "$FIRST_VERSION" = true ]; then
    echo "  ⚠️  $widget_name 没有有效的版本目录"
    continue
  fi

  # 添加小组件信息到 index.json
  if [ "$FIRST_WIDGET" = true ]; then
    FIRST_WIDGET=false
  else
    echo "," >> "$INDEX_FILE"
  fi

  cat >> "$INDEX_FILE" <<EOF
    {
      "id": "$WIDGET_ID",
      "displayName": "$DISPLAY_NAME",
      "description": "$DESCRIPTION",
      "author": "$AUTHOR",
      "category": "$CATEGORY",
      "icon": null,
      "color": null,
      "minAppVersion": "$MIN_APP_VERSION",
      "latestVersion": "$LATEST_VERSION",
      "status": "STABLE",
      "versions": $VERSIONS_JSON
    }
EOF

done

# 结束 JSON 结构
cat >> "$INDEX_FILE" << 'EOF'

  ]
}
EOF

# 替换时间戳 (兼容 macOS 和 Linux)
if [[ "$OSTYPE" == "darwin"* ]]; then
  sed -i "" "s/TIMESTAMP_PLACEHOLDER/$TIMESTAMP/" "$INDEX_FILE"
else
  sed -i "s/TIMESTAMP_PLACEHOLDER/$TIMESTAMP/" "$INDEX_FILE"
fi

# 格式化 JSON（如果 jq 可用）
if command -v jq &> /dev/null; then
  jq . "$INDEX_FILE" > "${INDEX_FILE}.tmp" && mv "${INDEX_FILE}.tmp" "$INDEX_FILE"
fi

echo "✅ index.json 生成完成"
cat "$INDEX_FILE"
