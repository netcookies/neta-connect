# 哪吒互联小组件仓库

这个目录包含了可用于哪吒互联 App 的远程小组件。

## 目录结构

```
widgets/
├── index.json                    # 小组件索引文件
├── README.md                     # 说明文档
├── battery-widget/               # 电池小组件
│   ├── 1.0.0/                   # 版本号
│   │   └── battery-widget.jar   # JAR 文件
│   └── metadata.json            # 小组件元数据
└── ...                          # 其他小组件
```

## index.json 格式说明

```json
{
  "version": "1.0.0",              // 索引版本
  "lastUpdated": "2025-01-25T00:00:00Z",  // 最后更新时间
  "widgets": [                     // 小组件列表
    {
      "id": "widget_id",           // 唯一标识符（使用下划线命名）
      "displayName": "显示名称",
      "description": "描述",
      "author": "作者",
      "category": "分类",
      "icon": "图标URL",
      "status": "STABLE|BETA|DEPRECATED|HIDDEN",  // 状态
      "minAppVersion": "1.7.9",    // 最低支持的 App 版本
      "latestVersion": "1.0.0",    // 最新版本号
      "versions": [                // 历史版本列表
        {
          "version": "1.0.0",
          "releaseDate": "2025-01-25",
          "downloadUrl": "下载链接",
          "fileSize": 11264,       // 文件大小（字节）
          "sha256": "校验和",
          "changelog": "更新日志"
        }
      ]
    }
  ]
}
```

## 小组件状态说明

- **STABLE**: 稳定版，推荐使用
- **BETA**: 测试版，可能存在问题
- **DEPRECATED**: 已弃用，不推荐使用
- **HIDDEN**: 隐藏，不在商店中显示

## 发布新版本

### 发布流程

1. 在对应小组件目录下创建版本目录
2. 将编译好的 JAR 文件放入版本目录
3. 更新 `index.json`，添加版本信息
4. 提交并推送到 main 分支

### 示例

```bash
# 1. 创建版本目录
mkdir -p widgets/battery-widget/1.0.0

# 2. 复制 JAR 文件
cp /path/to/battery-widget.jar widgets/battery-widget/1.0.0/

# 3. 获取文件信息
ls -l widgets/battery-widget/1.0.0/battery-widget.jar | awk '{print $5}'  # 文件大小
shasum -a 256 widgets/battery-widget/1.0.0/battery-widget.jar             # SHA256

# 4. 更新 index.json（添加版本信息，见下文格式）

# 5. 提交推送
git add widgets/
git commit -m "feat: 添加电池小组件 v1.0.0"
git push
```

### 下载 URL 格式

```
https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/{widget_id}/{version}/{文件名}.jar
```

示例：
```
https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/battery-widget/1.0.0/battery-widget.jar
```

## 小组件命名规范

- **ID**: 使用下划线分隔的小写字母，如 `battery_widget`
- **插件类**: 遵循约定 `com.neta.widgets.{第一部分}.{驼峰式ID}Plugin`
  - 例如: `battery_widget` → `com.neta.widgets.battery.BatteryWidgetPlugin`

## 索引 URL

- **原始 URL**: `https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/index.json`
- **加速 URL** (自动): App 会自动使用 `gh-proxy.com` 加速访问

## 注意事项

1. 每次更新 `index.json` 后，GitHub Actions 会自动更新 `lastUpdated` 时间戳
2. JAR 文件直接提交到仓库，通过 GitHub raw 链接下载
3. 文件大小建议控制在 100KB 以内（GitHub 仓库限制单文件 100MB）
4. 确保 JAR 文件包含正确的 DEX 代码
5. App 会自动使用 `gh-proxy.com` 加速 GitHub raw 链接下载
