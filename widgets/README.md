# 哪吒互联小组件开发完整指南

这是哪吒互联 App 动态小组件的完整开发和发布文档。本指南涵盖从开发到发布的全流程。

---

## 📋 目录

- [快速开始](#快速开始)
- [开发指南](#开发指南)
- [电池示例模块](#电池示例模块)
- [发布和管理](#发布和管理)
- [仓库结构](#仓库结构)

---

## 🚀 快速开始

### 使用小组件商店

1. 打开哪吒互联 App
2. 进入 **高级** 选项卡
3. 点击 **小组件商店**
4. 刷新列表查看可用小组件
5. 点击 **安装** 下载小组件

### 快速发布小组件

详细发布流程请参考 [QUICKSTART.md](./QUICKSTART.md)

---

## 🎨 开发指南

### 前置要求

**知识储备**
- ✅ Kotlin 基础语法
- ✅ Jetpack Compose 基础
- ✅ Android 模块化开发
- ✅ Gradle 构建系统

**开发环境**
- Android Studio Hedgehog 或更新版本
- JDK 11 或更高
- Android SDK 30+

### 创建新模块

#### 步骤 1: 复制模板模块

最简单的方式是复制 `plugin-widgets` 模块作为起点：

```bash
# 在哪吒互联主项目中
cp -r plugin-widgets widget-speedometer
cd widget-speedometer
```

#### 步骤 2: 修改模块配置

编辑 `build.gradle.kts`：

```kotlin
android {
    namespace = "com.neta.widgets.speedometer"  // 修改命名空间
    compileSdk = 36
    defaultConfig {
        minSdk = 30
    }
    // ... 其他配置保持不变
}
```

#### 步骤 3: 重命名包和类

```bash
# 重命名包目录
mv src/main/java/com/neta/widgets/battery \
   src/main/java/com/neta/widgets/speedometer

# 修改类名
# BatteryWidgetPlugin.kt → SpeedometerWidgetPlugin.kt
# SimpleBatteryWidget.kt → SimpleSpeedometerWidget.kt
```

#### 步骤 4: 更新 settings.gradle.kts

在项目根目录的 `settings.gradle.kts` 中添加：

```kotlin
include(":widget-speedometer")
```

### 实现插件接口

创建 `SpeedometerWidgetPlugin.kt`：

```kotlin
package com.neta.widgets.speedometer

import com.neta.isulewtools.widget.WidgetSpec
import com.neta.isulewtools.widget.plugin.WidgetPlugin
import com.neta.isulewtools.widget.plugin.WidgetPluginMetadata

class SpeedometerWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return SpeedometerWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "speedometer-widget",        // 唯一 ID（用连字符）
            version = "1.0.0",
            author = "你的名字",
            description = "显示车速的小组件",
            minAppVersion = "1.7.9"           // 最低应用版本
        )
    }
}
```

**元数据字段说明**

| 字段 | 说明 | 示例 |
|------|------|------|
| `id` | 小组件唯一标识（用连字符） | `speedometer-widget` |
| `version` | 版本号（语义化版本） | `1.0.0` |
| `author` | 开发者名称 | `官方` / `你的名字` |
| `description` | 功能描述 | `显示车速的小组件` |
| `minAppVersion` | 最低支持的主应用版本 | `1.7.9` |

**注意**: 插件类路径由构建脚本自动生成到 JAR 的 MANIFEST.MF 中，无需手动配置。

### 定义小组件规格

在 `SimpleSpeedometerWidget.kt` 中定义：

```kotlin
package com.neta.widgets.speedometer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.ui.graphics.Color
import com.neta.isulewtools.widget.*

object SpeedometerWidgetSpec : WidgetSpec(
    type = "speedometer_widget",
    displayName = "车速表",
    
    // ⚡ 只需定义你自己的参数
    // scale 和 alpha 会自动注入！
    paramSchema = listOf(
        WidgetParamDesc(
            key = "maxSpeed",
            label = "最大速度",
            type = WidgetParamType.INT,
            defaultValue = 120,
            description = "速度表的最大刻度"
        ),
        WidgetParamDesc(
            key = "unit",
            label = "单位",
            type = WidgetParamType.STRING,
            defaultValue = "km/h",
            description = "速度单位"
        ),
        WidgetParamDesc(
            key = "color",
            label = "主题色",
            type = WidgetParamType.STRING,
            defaultValue = "#2196F3"
        )
    ),
    
    contentComposable = { config ->
        SpeedometerWidgetContent(config)
    },
    
    color = Color(0xFF2196F3),
    icon = Icons.Default.Speed
)
```

### 参数类型说明

| WidgetParamType | 用途 | 示例 |
|-----------------|------|------|
| `STRING` | 文本、颜色、选项 | `"#FF0000"`, `"km/h"` |
| `INT` | 整数值 | `120`, `60` |
| `FLOAT` | 小数值 | `75.5`, `1.5` |
| `BOOL` | 布尔值 | `true`, `false` |
| `COLOR` | 颜色选择器 | `"#2196F3"` |
| `SCALE` | 缩放（自动注入） | `1.0`, `1.5` |
| `ALPHA` | 透明度（自动注入） | `1.0`, `0.5` |
| `DATA_SOURCE` | 数据源绑定 | VHAL 属性 |
| `ENUM` | 枚举选项 | 需配合 `options` |

### 实现 UI 内容

```kotlin
@Composable
fun SpeedometerWidgetContent(config: WidgetConfig) {
    // 1️⃣ 读取自动注入的参数
    val scale = (config.params["scale"] as? Number)?.toFloat() ?: 1f
    val alpha = (config.params["alpha"] as? Number)?.toFloat() ?: 1f
    
    // 2️⃣ 读取自定义参数
    val maxSpeed = (config.params["maxSpeed"] as? Number)?.toInt() ?: 120
    val unit = config.params["unit"]?.toString() ?: "km/h"
    val colorStr = config.params["color"]?.toString() ?: "#2196F3"
    
    // 3️⃣ 解析颜色
    val color = try {
        Color(android.graphics.Color.parseColor(colorStr))
    } catch (e: Exception) {
        Color(0xFF2196F3)
    }
    
    // 4️⃣ 构建 UI（应用 scale 和 alpha）
    Box(
        modifier = Modifier
            .size((200 * scale).dp)  // 尺寸跟随缩放
            .graphicsLayer(alpha = alpha),  // 应用透明度
        contentAlignment = Alignment.Center
    ) {
        // 绘制速度表内容
        Canvas(modifier = Modifier.fillMaxSize()) {
            // ... 绘图代码
        }
        
        Text(
            text = "120 $unit",
            fontSize = (24 * scale).sp,  // 字体也跟随缩放
            color = color
        )
    }
}
```

### 配置构建脚本

#### 依赖配置

```kotlin
dependencies {
    // ✅ 使用 compileOnly - 运行时使用主应用的库
    compileOnly("androidx.compose.ui:ui:1.6.1")
    compileOnly("androidx.compose.material3:material3:1.2.0")
    compileOnly("androidx.compose.material:material-icons-extended:1.6.1")
    compileOnly("androidx.core:core-ktx:1.12.0")
    
    // ❌ 不要使用 implementation 打包大型库
    // implementation("androidx.compose.ui:ui:1.6.1")  // 会导致 JAR 过大
}
```

#### JAR 生成任务

JAR 生成任务已在 `build.gradle.kts` 中配置好。确保输出文件名正确：

```kotlin
outputJar.set(layout.file(generatedDir.map {
    it.asFile.resolve("speedometer-widget.jar")  // 修改文件名
}))
```

### 编译和测试

#### 编译 JAR

```bash
# 清理并编译
./gradlew :widget-speedometer:clean :widget-speedometer:assembleDebug

# 查找生成的 JAR
ls -lh widget-speedometer/build/outputs/widget/debug/
```

#### 本地测试

```bash
# 创建目录（如果不存在）
adb shell mkdir -p /sdcard/isulewTools/widgets

# 推送 JAR 文件
adb push widget-speedometer/build/outputs/widget/debug/speedometer-widget.jar \
    /sdcard/isulewTools/widgets/

# 查看日志
adb logcat | grep -E "WidgetLoader|WidgetManager"
```

#### 验证加载

启动主应用并检查日志：

```
✓ 注册动态小组件: speedometer-widget v1.0.0
  原始 type='speedometer_widget' → 自动前缀 type='dynamic_speedometer_widget'
  displayName='车速表'
  Plugin-Class: com.neta.widgets.speedometer.SpeedometerWidgetPlugin (from MANIFEST.MF)
```

---

## 🔋 电池示例模块

### 模块说明

`plugin-widgets` 是一个独立的 Android Library 模块，包含电池示例小组件：

- ✅ 实现了 `WidgetPlugin` 接口
- ✅ 可以独立编译为 JAR 文件
- ✅ 在运行时被主应用动态加载
- ✅ 自动支持缩放和透明度
- ✅ 完整的元信息和版本管理

### 项目结构

```
plugin-widgets/
├── build.gradle.kts          # 构建配置（包含 JAR 生成任务）
├── README.md                 # 本文档
├── DEVELOPMENT_GUIDE.md      # 开发指南
└── src/main/java/com/neta/
    ├── widgets/battery/
    │   ├── BatteryWidgetPlugin.kt          # 插件入口类
    │   └── SimpleBatteryWidget.kt          # 小组件实现
    └── isulewtools/widget/
        ├── WidgetSpec.kt                   # Widget 规格定义（复制）
        └── plugin/WidgetPlugin.kt          # 插件接口（复制）
```

### 核心实现

#### 插件入口

```kotlin
class BatteryWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return SimpleBatteryWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-battery-demo",
            version = "1.0.0",
            author = "官方",
            description = "显示电池电量的小组件(示例)",
            minAppVersion = "1.7.9"
        )
    }
}
```

#### 小组件实现

```kotlin
object SimpleBatteryWidgetSpec : WidgetSpec(
    type = "battery_widget",
    displayName = "电池(示例)",
    paramSchema = listOf(
        WidgetParamDesc("batteryLevel", "电量(%)", WidgetParamType.FLOAT, 75f),
        WidgetParamDesc("color", "颜色", WidgetParamType.STRING, "#66BB6A")
        // scale 和 alpha 会自动注入！
    ),
    contentComposable = { SimpleBatteryWidgetContent(it) },
    color = Color(0xFF66BB6A),
    icon = Icons.Default.BatteryChargingFull
)
```

### 构建电池示例

```bash
# 编译 Debug 版本
./gradlew :plugin-widgets:assembleDebug

# 编译 Release 版本
./gradlew :plugin-widgets:assembleRelease
```

生成的 JAR 文件位于：
```
plugin-widgets/build/outputs/widget/
├── debug/widget-battery-demo.jar       # Debug 版本
└── release/widget-battery-demo.jar     # Release 版本
```

---

## 📦 发布和管理

### 仓库结构

```
widgets/
├── index.json                       # 小组件索引文件
├── README.md                        # 本文档
├── QUICKSTART.md                    # 快速发布指南
├── widget-battery-demo/             # 电池示例小组件
│   └── 1.0.0/
│       └── widget-battery-demo.jar
└── ...                              # 其他小组件
```

### index.json 格式

```json
{
  "version": "1.0.0",
  "lastUpdated": "2025-01-25T00:00:00Z",
  "widgets": [
    {
      "id": "widget_battery_demo",
      "displayName": "电池(示例)",
      "description": "显示电池电量的示例小组件",
      "author": "官方",
      "category": "示例",
      "icon": "",
      "status": "STABLE",
      "minAppVersion": "1.7.9",
      "latestVersion": "1.0.0",
      "versions": [
        {
          "version": "1.0.0",
          "releaseDate": "2025-01-27",
          "downloadUrl": "https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/widget-battery-demo/1.0.0/widget-battery-demo.jar",
          "fileSize": 11264,
          "sha256": "",
          "changelog": "- 初始版本\n- 基础电量显示功能"
        }
      ]
    }
  ]
}
```

### 发布流程

详细发布流程请参考 [QUICKSTART.md](./QUICKSTART.md)

简要步骤：

1. **创建版本目录**
```bash
mkdir -p widgets/your-widget/1.0.0
```

2. **复制 JAR 文件**
```bash
cp /path/to/your-widget.jar widgets/your-widget/1.0.0/
```

3. **获取文件信息**
```bash
ls -l widgets/your-widget/1.0.0/your-widget.jar | awk '{print $5}'
shasum -a 256 widgets/your-widget/1.0.0/your-widget.jar
```

4. **更新 index.json**

5. **提交并推送**
```bash
git add widgets/
git commit -m "feat: 添加 your-widget v1.0.0"
git push
```

### 小组件状态

- **STABLE**: 稳定版，推荐使用
- **BETA**: 测试版，可能存在问题
- **DEPRECATED**: 已弃用，不推荐使用
- **HIDDEN**: 隐藏，不在商店中显示

### 命名规范

- **widgetId**: 使用连字符分隔，如 `battery-widget`、`speedometer-widget`
- **插件类名**: `com.neta.widgets.{第一部分}.{驼峰式名称}Plugin`
  - 例如: `battery-widget` → `com.neta.widgets.battery.BatteryWidgetPlugin`

---

## 🔧 高级主题

### 自动注入的 scale 和 alpha

`WidgetSpec` 构造函数会自动检测并注入这两个必需参数：

```kotlin
// ✅ 推荐：让系统自动注入
paramSchema = listOf(
    WidgetParamDesc("speed", "速度", ...)
    // scale 和 alpha 自动添加
)

// ❌ 不推荐：手动定义（重复且容易出错）
paramSchema = listOf(
    WidgetParamDesc("scale", "缩放", WidgetParamType.SCALE, 1f),
    WidgetParamDesc("alpha", "透明度", WidgetParamType.ALPHA, 1f),
    WidgetParamDesc("speed", "速度", ...)
)
```

### 响应式布局

```kotlin
@Composable
fun ResponsiveWidget(config: WidgetConfig) {
    val scale = (config.params["scale"] as? Number)?.toFloat() ?: 1f
    
    // 基础尺寸
    val baseSize = 100.dp
    val baseFontSize = 16.sp
    val basePadding = 8.dp
    
    Box(
        modifier = Modifier
            .size(baseSize * scale)
            .padding(basePadding * scale)
    ) {
        Text(
            text = "内容",
            fontSize = baseFontSize * scale
        )
    }
}
```

### 性能优化

```kotlin
@Composable
fun OptimizedWidget(config: WidgetConfig) {
    // ✅ 使用 remember 缓存配置解析
    val scale = remember(config) {
        (config.params["scale"] as? Number)?.toFloat() ?: 1f
    }
    
    // ✅ 避免重复计算
    val scaledSize = remember(scale) { 100.dp * scale }
    
    // ✅ 使用 key 优化重组
    key(config.params.hashCode()) {
        // Widget 内容
    }
}
```

---

## 📝 最佳实践

### DO（推荐做法）

- ✅ 使用 `compileOnly` 声明依赖
- ✅ 在 Composable 中应用 scale 和 alpha
- ✅ 提供清晰的参数描述
- ✅ 设置合理的默认值
- ✅ 遵循命名规范
- ✅ JAR 文件大小控制在 100KB 以内
- ✅ 在不同缩放比例下测试

### DON'T（避免事项）

- ❌ 不要手动添加 scale 和 alpha 参数
- ❌ 不要使用 `implementation` 打包大型库
- ❌ 不要依赖主应用的私有类
- ❌ 不要在 JAR 中包含资源文件

---

## 🐛 故障排除

### JAR 无法加载

**症状**: 日志显示"加载失败"

**可能原因**:
1. 插件类路径错误
2. 依赖版本不匹配
3. 缺少必需参数

**解决方法**:
```bash
# 查看日志
adb logcat | grep WidgetLoader

# 检查 JAR 内容
unzip -l your-widget.jar
```

### 小组件不显示

**检查清单**:
- [ ] JAR 文件是否成功加载？
- [ ] 数据库中是否有记录？
- [ ] 参数配置是否正确？
- [ ] scale 和 alpha 是否生效？

---

## 📚 相关资源

- **快速发布**: [QUICKSTART.md](./QUICKSTART.md)
- **索引 URL**: `https://raw.githubusercontent.com/netcookies/neta-connect/main/widgets/index.json`
- **主应用仓库**: [哪吒互联](https://github.com/netcookies/isulewTools)
- **问题反馈**: [GitHub Issues](https://github.com/netcookies/neta-connect/issues)

---

## 📄 许可证

与主应用保持一致

---

祝你开发愉快！🎉

---

## 📄 metadata.json 说明

每个小组件目录下都应该包含一个 `metadata.json` 文件，用于描述小组件的元数据信息。

### 文件位置

```
widgets/
└── widget-battery-demo/
    ├── metadata.json          # 小组件元数据（必需）
    └── 1.0.0/
        └── widget-battery-demo.jar
```

### metadata.json 格式

```json
{
  "id": "widget-battery-demo",
  "displayName": "电池(示例)",
  "description": "显示电池电量的示例小组件",
  "author": "官方",
  "category": "示例",
  "minAppVersion": "1.7.9",
  "permissions": [],
  "screenshots": [],
  "repository": "https://github.com/netcookies/neta-connect",
  "supportUrl": "https://github.com/netcookies/neta-connect/issues"
}
```

### 字段说明

| 字段 | 类型 | 必需 | 说明 |
|------|------|------|------|
| `id` | string | ✅ | 小组件唯一标识符（与 WidgetPluginMetadata 中的 id 一致） |
| `displayName` | string | ✅ | 显示名称（从 WidgetSpec 中获取） |
| `description` | string | ✅ | 功能描述 |
| `author` | string | ✅ | 作者名称 |
| `category` | string | ✅ | 分类（如：系统信息、生活服务、示例等） |
| `minAppVersion` | string | ✅ | 最低支持的应用版本 |
| `permissions` | array | ❌ | 所需权限列表（可选） |
| `screenshots` | array | ❌ | 截图 URL 列表（可选） |
| `repository` | string | ❌ | 源代码仓库地址 |
| `supportUrl` | string | ❌ | 问题反馈地址 |

**注意**: 插件类路径无需在 metadata.json 中配置，它会由构建脚本自动生成到 JAR 的 MANIFEST.MF 中。

### 自动生成

如果使用哪吒互联项目的 CI/CD 工作流，`metadata.json` 会在首次部署时自动生成：

1. **提取元数据**：从 `BatteryWidgetPlugin.kt` 和 `SimpleBatteryWidget.kt` 中提取
2. **自动创建**：如果 `metadata.json` 不存在，则自动创建
3. **保留现有**：如果已存在，则跳过创建（避免覆盖手动修改）

### 手动创建

如果需要手动创建或更新 `metadata.json`：

```bash
# 1. 进入小组件目录
cd widgets/your-widget-name

# 2. 创建或编辑 metadata.json
cat > metadata.json << 'EOF'
{
  "id": "your_widget_id",
  "displayName": "你的小组件名称",
  "description": "功能描述",
  "author": "你的名字",
  "category": "分类",
  "minAppVersion": "1.7.9",
  "permissions": [],
  "screenshots": [],
  "repository": "https://github.com/netcookies/neta-connect",
  "supportUrl": "https://github.com/netcookies/neta-connect/issues"
}
EOF

# 3. 提交更改
git add metadata.json
git commit -m "docs: Add metadata.json for your-widget"
git push
```

### 注意事项

- ✅ `id` 必须与代码中的 `WidgetPluginMetadata.id` 一致
- ✅ JSON 格式必须正确（使用 `jq` 或在线工具验证）
- ✅ `category` 建议使用统一的分类名称
- ✅ 插件类路径会自动从 JAR 的 MANIFEST.MF 读取，无需在 metadata.json 中配置
- ⚠️ 修改 `metadata.json` 后记得提交到仓库
