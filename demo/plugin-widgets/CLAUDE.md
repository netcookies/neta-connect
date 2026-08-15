# plugin-widgets 模块文档

[根目录](../CLAUDE.md) > **plugin-widgets**

---

## 模块职责

plugin-widgets 模块包含动态加载的小组件插件示例，展示如何开发可热插拔的 JAR 插件。每个插件实现 `WidgetPlugin` 接口，通过 Jetpack Compose 编写 UI，并通过 Gradle 任务打包为独立的 JAR 文件，可在主应用中动态加载和卸载。

**核心功能**:
- 提供 15+ 个小组件插件示例
- 演示插件开发最佳实践
- 支持 Material Icons 自动提取
- 自动生成插件元数据
- JAR 打包与分发

---

## 入口与启动

### 插件结构

每个插件包含两个主要文件：
1. `{WidgetName}Widget.kt` - UI 实现
2. `{WidgetName}WidgetPlugin.kt` - 插件入口

**示例结构**:
```
plugin-widgets/src/main/java/com/neta/widgets/
├── battery/
│   ├── BatteryWidget.kt
│   └── BatteryWidgetPlugin.kt
├── progressbar/
│   ├── ProgressBarWidget.kt
│   └── ProgressBarWidgetPlugin.kt
└── ...
```

### 插件入口示例

**BatteryWidgetPlugin.kt**:
```kotlin
class BatteryWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec = BatteryWidgetSpec

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-battery",
            version = "1.0.6",
            author = "官方",
            description = "iOS 风格的电池显示小组件",
            minAppVersion = "2.1.7",
            category = "通用",
            signer = null,
            certificateSha256 = null
        )
    }
}
```

---

## 对外接口

### WidgetPlugin 接口

插件必须实现 `widget-api` 模块定义的接口：

```kotlin
interface WidgetPlugin {
    fun getSpec(): WidgetSpec
    fun getMetadata(): WidgetPluginMetadata
}
```

### 元数据对象

```kotlin
data class WidgetPluginMetadata(
    val id: String,
    val version: String,
    val author: String,
    val description: String,
    val minAppVersion: String,
    val category: String = "车机",
    val signer: String? = null,
    val certificateSha256: String? = null
)
```

- `category` 用于插件分类与商店展示。
- `signer`、`certificateSha256` 会透传到 MANIFEST 和运行时校验链路，目前保持可选以兼容旧插件。
- 示例插件统一使用 `minAppVersion = "2.1.7"`。

---

## 关键依赖与配置

### Gradle 配置

**build.gradle.kts**:
```kotlin
dependencies {
    // Widget API - compileOnly，运行时由主 App 提供
    compileOnly(project(":widget-api"))

    // Compose 依赖
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material.icons.extended)
}
```

### 自定义 Gradle 任务

**CreateWidgetJarTask**:
- 自动扫描插件源码
- 提取 Material Icons 使用
- 使用 D8 编译为 DEX
- 打包为 JAR 文件
- 生成 MANIFEST.MF 元数据

**任务注册**:
```kotlin
// 为每个 widget 创建独立的 JAR 任务
tasks.register<CreateWidgetJarTask>("createReleaseBatteryWidgetJar") {
    classDirs.from(...)
    outputJar.set(File("build/outputs/widget/release/com.neta.widgets.battery.jar"))
    widgetPackagePath.set("com/neta/widgets/battery")
}
```

---

## 数据模型

### 已实现的插件

| 插件 ID | 显示名称 | 分类 | 描述 |
|---------|---------|------|------|
| `com.neta.widgets.battery` | 电池电量 | vehicle | 显示车辆电池电量和状态 |
| `com.neta.widgets.progressbar` | 进度条 | chart | 可配置的进度条组件 |
| `com.neta.widgets.infochart` | 信息图表 | chart | 多数据源信息图表 |
| `com.neta.widgets.linechart` | 折线图 | chart | 实时数据折线图 |
| `com.neta.widgets.barchart` | 柱状图 | chart | 数据柱状图 |
| `com.neta.widgets.arcgauge` | 弧形仪表 | gauge | 弧形仪表盘 |
| `com.neta.widgets.temperature` | 温度显示 | vehicle | 车辆温度监控 |
| `com.neta.widgets.tirepressure` | 胎压监测 | vehicle | 四轮胎压显示 |
| `com.neta.widgets.gear` | 档位显示 | vehicle | 当前档位指示 |
| `com.neta.widgets.vehiclelights` | 车灯状态 | vehicle | 车灯开关状态 |
| `com.neta.widgets.minicard` | 迷你卡片 | info | 小型信息卡片 |
| `com.neta.widgets.infocard4x2d2` | 信息卡片 4x2 | info | 4x2 网格信息卡片 |
| `com.neta.widgets.infocard4x3d3` | 信息卡片 4x3 | info | 4x3 网格信息卡片 |
| `com.neta.widgets.infocard4x3d4` | 信息卡片 4x3 | info | 4x3 网格信息卡片 (变体) |
| `com.neta.widgets.batterydemo` | 电池演示 | demo | 电池小组件演示 |

### 插件参数

插件通过 `params` 接收配置参数：

```kotlin
@Composable
fun BatteryWidget(
    modifier: Modifier,
    scale: Float,
    alpha: Float,
    params: Map<String, Any?>
) {
    val batteryLevel = params["batteryLevel"] as? Float ?: 0f
    val isCharging = params["isCharging"] as? Boolean ?: false

    // UI 实现...
}
```

---

## 测试与质量

### 构建测试

**构建单个插件**:
```bash
./gradlew :plugin-widgets:createReleaseBatteryWidgetJar
```

**构建所有插件**:
```bash
./gradlew :plugin-widgets:createReleaseWidgetJar
```

**验证 JAR 文件**:
```bash
# 查看 JAR 内容
unzip -l build/outputs/widget/release/com.neta.widgets.battery.jar

# 查看 MANIFEST.MF
unzip -p build/outputs/widget/release/com.neta.widgets.battery.jar META-INF/MANIFEST.MF
```

### 插件加载测试

在主应用中测试插件加载：
1. 将 JAR 文件复制到设备
2. 通过主应用的插件管理界面导入
3. 验证插件是否正确显示
4. 测试插件功能和参数传递

---

## 常见问题 (FAQ)

### Q: 如何创建新插件？

1. 在 `src/main/java/com/neta/widgets/` 下创建新目录
2. 创建 `{Name}Widget.kt` 和 `{Name}WidgetPlugin.kt`
3. 实现 `WidgetPlugin` 接口
4. 在 `getMetadata()` 中返回 `WidgetPluginMetadata`
5. 运行 `./gradlew :plugin-widgets:createReleaseWidgetJar`

### Q: 如何使用 Material Icons？

直接在 Compose 代码中使用：
```kotlin
Icon(
    imageVector = Icons.Default.BatteryChargingFull,
    contentDescription = "Battery"
)
```

Gradle 任务会自动提取并打包到 JAR 中。

### Q: 插件如何访问车辆数据？

通过 `params` 参数接收数据：
```kotlin
val speed = params["speed"] as? Float ?: 0f
val rpm = params["rpm"] as? Int ?: 0
```

主应用负责从 VHAL 读取数据并传递给插件。

---

## 相关文件清单

**插件源码** (15 个插件):
- `src/main/java/com/neta/widgets/battery/`
- `src/main/java/com/neta/widgets/progressbar/`
- `src/main/java/com/neta/widgets/infochart/`
- `src/main/java/com/neta/widgets/linechart/`
- `src/main/java/com/neta/widgets/barchart/`
- `src/main/java/com/neta/widgets/arcgauge/`
- `src/main/java/com/neta/widgets/temperature/`
- `src/main/java/com/neta/widgets/tirepressure/`
- `src/main/java/com/neta/widgets/gear/`
- `src/main/java/com/neta/widgets/vehiclelights/`
- `src/main/java/com/neta/widgets/minicard/`
- `src/main/java/com/neta/widgets/infocard4x2d2/`
- `src/main/java/com/neta/widgets/infocard4x3d3/`
- `src/main/java/com/neta/widgets/infocard4x3d4/`
- `src/main/java/com/neta/widgets/batterydemo/`

**配置文件**:
- `build.gradle.kts` - 构建配置和自定义任务
- `src/main/AndroidManifest.xml`

**文档**:
- `README.md` - 插件系统概述
- `DEVELOPMENT_GUIDE.md` - 插件开发详细指南

---

## 变更记录 (Changelog)

### 2026-02-06 - 模块文档创建
- 创建 plugin-widgets 模块详细文档
- 记录 15 个插件示例
- 添加插件开发指南链接
- 补充 Gradle 任务说明

---

*本文档由 Claude Code 自动生成，最后更新: 2026-02-06 20:43:11*
