# 小组件快速开始指南

本指南帮助你快速发布第一个小组件到 neta-connect 仓库。

## 前置准备

1. ✅ 编译好的小组件 JAR 文件（包含 DEX）
2. ✅ 测试通过的小组件
3. ✅ GitHub 账号和 neta-connect 仓库的写权限

## 发布步骤

### 1. 准备 JAR 文件

确保你的 JAR 文件：
- 包含编译后的 DEX 代码
- 文件名格式: `{widget_id}.jar`，例如 `battery-widget.jar`
- 大小建议小于 100KB

### 2. 创建目录并复制文件

```bash
# 假设你要发布 battery-widget v1.0.0

# 1. 创建版本目录
mkdir -p widgets/battery-widget/1.0.0

# 2. 复制 JAR 文件
cp /path/to/battery-widget.jar widgets/battery-widget/1.0.0/
```

### 3. 获取文件信息

```bash
# 获取文件大小（字节）
ls -l widgets/battery-widget/1.0.0/battery-widget.jar | awk '{print $5}'

# 获取 SHA256 校验和（可选）
shasum -a 256 widgets/battery-widget/1.0.0/battery-widget.jar
```

### 4. 更新 index.json

编辑 `widgets/index.json`，添加或更新小组件信息：

```json
{
  "version": "1.0.0",
  "lastUpdated": "2025-01-25T00:00:00Z",
  "widgets": [
    {
      "id": "battery_widget",
      "displayName": "电池小组件",
      "description": "显示车辆电池电量、续航里程等信息",
      "author": "你的名字",
      "category": "系统信息",
      "icon": "",
      "status": "BETA",
      "minAppVersion": "1.7.9",
      "latestVersion": "1.0.0",
      "versions": [
        {
          "version": "1.0.0",
          "releaseDate": "2025-01-25",
          "downloadUrl": "https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/battery-widget/1.0.0/battery-widget.jar",
          "fileSize": 11264,
          "sha256": "",
          "changelog": "- 初始版本\n- 基础功能实现"
        }
      ]
    }
  ]
}
```

**重要字段说明:**
- `downloadUrl`: GitHub Raw 下载链接，格式为 `https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/{widget_id}/{version}/{文件名}.jar`
- `fileSize`: JAR 文件的字节大小
- `sha256`: 文件的 SHA256 校验和（可选，建议填写）

### 5. 提交并推送

```bash
git add widgets/
git commit -m "feat: 添加电池小组件 v1.0.0"
git push
```

GitHub Actions 会自动更新 `index.json` 中的 `lastUpdated` 字段。

## 升级现有小组件

### 1. 创建新版本目录

```bash
# 创建新版本目录
mkdir -p widgets/battery-widget/1.0.1

# 复制新版本的 JAR 文件
cp /path/to/battery-widget.jar widgets/battery-widget/1.0.1/
```

### 2. 更新 index.json

在 `versions` 数组**开头**添加新版本（保持倒序）：

```json
{
  "latestVersion": "1.0.1",  // 更新最新版本号
  "versions": [
    {
      "version": "1.0.1",  // 新版本放在最前面
      "releaseDate": "2025-01-26",
      "downloadUrl": "https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/battery-widget/1.0.1/battery-widget.jar",
      "fileSize": 11500,
      "sha256": "",
      "changelog": "- 修复电量显示问题\n- 优化性能"
    },
    {
      "version": "1.0.0",  // 旧版本保留
      "releaseDate": "2025-01-25",
      "downloadUrl": "https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/battery-widget/1.0.0/battery-widget.jar",
      "fileSize": 11264,
      "sha256": "",
      "changelog": "- 初始版本"
    }
  ]
}
```

### 3. 提交推送

```bash
git add widgets/
git commit -m "feat: 升级电池小组件到 v1.0.1"
git push
```

## 常见问题

### Q: 如何测试小组件是否能正常下载？

A: 可以使用以下方式测试：

1. **本地测试下载链接:**
```bash
curl -L "你的downloadUrl" -o test.jar
```

2. **在 App 中测试:**
   - 打开小组件商店
   - 刷新列表
   - 尝试安装

### Q: index.json 更新后多久生效？

A:
- GitHub Raw CDN 通常有 5-10 分钟缓存
- App 使用 gh-proxy.com 加速，缓存时间更短
- 建议更新后等待 5 分钟再测试

### Q: 如何处理文件太大的问题？

A:
1. 检查是否包含不必要的资源文件
2. 使用 ProGuard/R8 压缩代码
3. GitHub 仓库单文件限制 100MB，但建议控制在 100KB 以内

### Q: 如何修改小组件状态？

A: 在 `index.json` 中修改 `status` 字段：
- `BETA` → `STABLE`: 测试完成，进入稳定版
- `STABLE` → `DEPRECATED`: 标记为已弃用
- `*` → `HIDDEN`: 从商店中隐藏

### Q: 是否可以删除旧版本？

A: 可以，但建议保留至少最近 2-3 个版本，方便用户降级。删除步骤：
1. 删除对应版本目录（如 `widgets/battery-widget/1.0.0/`）
2. 从 `index.json` 的 `versions` 数组中删除对应版本信息
3. 提交并推送

## 小组件 ID 命名规范

- 使用下划线分隔的小写字母
- 示例: `battery_widget`, `weather_widget`, `music_player`
- 插件类名自动推导: `battery_widget` → `com.neta.widgets.battery.BatteryWidgetPlugin`

## 发布检查清单

发布前请确认：

- [ ] JAR 文件包含 DEX 代码
- [ ] 在真实设备上测试通过
- [ ] `id` 命名符合规范
- [ ] `downloadUrl` 可以正常访问（使用 curl 测试）
- [ ] `fileSize` 正确（与实际文件大小一致）
- [ ] `minAppVersion` 合理设置
- [ ] `changelog` 详细描述更新内容
- [ ] 版本号遵循语义化版本规范（MAJOR.MINOR.PATCH）

## 完整示例

假设你开发了一个天气小组件：

```bash
# 1. 创建目录
mkdir -p widgets/weather-widget/1.0.0

# 2. 复制 JAR
cp ~/Downloads/weather-widget.jar widgets/weather-widget/1.0.0/

# 3. 获取文件信息
ls -l widgets/weather-widget/1.0.0/weather-widget.jar | awk '{print $5}'
# 输出: 8532

shasum -a 256 widgets/weather-widget/1.0.0/weather-widget.jar
# 输出: abc123...

# 4. 编辑 index.json，添加：
# {
#   "id": "weather_widget",
#   "displayName": "天气小组件",
#   "description": "显示实时天气信息",
#   "author": "Your Name",
#   "category": "生活服务",
#   "icon": "",
#   "status": "BETA",
#   "minAppVersion": "1.7.9",
#   "latestVersion": "1.0.0",
#   "versions": [
#     {
#       "version": "1.0.0",
#       "releaseDate": "2025-01-25",
#       "downloadUrl": "https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/weather-widget/1.0.0/weather-widget.jar",
#       "fileSize": 8532,
#       "sha256": "abc123...",
#       "changelog": "- 首次发布\n- 支持实时天气显示"
#     }
#   ]
# }

# 5. 提交
git add widgets/
git commit -m "feat: 添加天气小组件 v1.0.0"
git push
```

## 需要帮助？

- 📖 详细文档: [widgets/README.md](./README.md)
- 🐛 报告问题: [GitHub Issues](https://github.com/netcookies/neta-connect/issues)
- 💬 讨论交流: [GitHub Discussions](https://github.com/netcookies/neta-connect/discussions)
