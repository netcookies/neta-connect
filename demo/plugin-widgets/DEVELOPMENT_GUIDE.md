# 🎨 动态小组件开发指南

本指南详细介绍如何从零开始创建一个动态加载的小组件插件。

---

## 📋 目录

- [前置要求](#前置要求)
- [创建新模块](#创建新模块)
- [实现插件接口](#实现插件接口)
- [定义小组件规格](#定义小组件规格)
- [实现 UI 内容](#实现-ui-内容)
- [配置构建脚本](#配置构建脚本)
- [编译和测试](#编译和测试)
- [高级主题](#高级主题)

---

## 🔰 前置要求

### 知识储备

- ✅ Kotlin 基础语法
- ✅ Jetpack Compose 基础
- ✅ Android 模块化开发
- ✅ Gradle 构建系统

### 开发环境

- Android Studio Hedgehog 或更新版本
- JDK 11 或更高
- Android SDK 30+

---

## 🆕 创建新模块

### 步骤 1: 复制模板模块

最简单的方式是复制 `plugin-widgets` 模块作为起点：

```bash
# 复制模块
cp -r plugin-widgets widget-speedometer

# 进入新模块
cd widget-speedometer
```

### 步骤 2: 修改模块配置

编辑 `build.gradle.kts`，修改以下内容：

```kotlin
android {
    namespace = "com.neta.widgets.speedometer"  // 修改命名空间
    // ... 其他配置保持不变
}
```

### 步骤 3: 重命名包和类

```bash
# 重命名包目录
mv src/main/java/com/neta/widgets/battery \
   src/main/java/com/neta/widgets/speedometer

# 修改类名
# BatteryWidgetPlugin.kt → SpeedometerWidgetPlugin.kt
# SimpleBatteryWidget.kt → SimpleSpeedometerWidget.kt
```

### 步骤 4: 更新 settings.gradle.kts

在项目根目录的 `settings.gradle.kts` 中添加：

```kotlin
include(":widget-speedometer")
```

---

## 🔌 实现插件接口

### 插件入口类

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
            id = "speedometer_widget",              // 唯一 ID
            version = "1.0.0",                      // 版本号
            author = "你的名字",                     // 作者
            description = "显示车速的小组件",        // 描述
            minAppVersion = "1.7.9"                 // 最低应用版本
        )
    }
}
```

**关键说明：**

| 字段              | 说明                               | 示例                                                     |
|-----------------|----------------------------------|---------------------------------------------------------|
| `id`            | 小组件唯一标识，用于文件名和数据库              | `speedometer_widget`                                   |
| `version`       | 版本号，遵循语义化版本                      | `1.0.0`                                                |
| `author`        | 开发者名称                            | `官方` / `你的名字`                                          |
| `description`   | 功能描述，显示在商店中                      | `显示车速的小组件`                                             |
| `minAppVersion` | 最低支持的主应用版本                       | `1.7.9`                                                |

---

## 📐 定义小组件规格

### 创建 WidgetSpec

在 `SimpleSpeedometerWidget.kt` 中定义小组件规格：

```kotlin
package com.neta.widgets.speedometer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.toHexString

object SpeedometerWidgetSpec : WidgetSpec(
    type = "speedometer_widget",
    displayName = "车速表",
    recommendedGrid = Pair(2, 2),  // 📐 推荐网格尺寸: 2×2

    // ⚡ 使用 buildParams DSL 定义参数
    // scale 和 alpha 会自动注入！
    paramSchema = WidgetParamDesc.buildParams {
        // 可以使用 group() 对参数分组
        group("基础设置") {
            +WidgetParamDesc(
                key = P.MAX_SPEED.key,
                label = "最大速度",
                type = WidgetParamType.INT,
                defaultValue = P.MAX_SPEED.default,
                description = "速度表的最大刻度"
            )
            +WidgetParamDesc(
                key = P.UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.UNIT.default,
                description = "速度单位"
            )
        }

        group("样式") {
            +WidgetParamDesc(
                key = P.COLOR.key,
                label = "主题色",
                type = WidgetParamType.COLOR,
                defaultValue = P.COLOR.default.toHexString()
            )
        }
    },

    contentComposable = { config ->
        SpeedometerWidgetContent(config)
    },

    color = Color(0xFF2196F3),
    icon = Icons.Default.Speed
) {
    /**
     * 参数定义 - 使用 ParamDef 实现类型安全
     * 集中定义所有参数的 key 和 default 值
     */
    object P {
        val MAX_SPEED = ParamDef("maxSpeed", 120)
        val UNIT = ParamDef("unit", "km/h")
        val COLOR = ParamDef("color", Color(0xFF2196F3))

        // 数据源参数只定义 key（类型由系统处理）
        const val DATASOURCE = "datasource"

        // 非参数常量 - 组件尺寸等
        val WIDTH = 200.dp
        val HEIGHT = 200.dp
    }
}
```

### 新的参数定义模式 (object P + ParamDef)

**核心优势：**

✅ **类型安全**：使用泛型 `ParamDef<T>`，避免类型转换错误
✅ **集中管理**：所有参数在 `object P` 中统一定义
✅ **避免重复**：key 和 default 只需定义一次
✅ **易于重构**：修改参数名只需改一处
✅ **更好的 IDE 支持**：自动补全和类型检查

**模式对比：**

| 特性           | 旧模式 (listOf)                    | 新模式 (object P + buildParams)      |
|--------------|---------------------------------|-----------------------------------|
| **参数定义**     | `listOf(WidgetParamDesc(...))`  | `object P { val X = ParamDef() }` |
| **参数使用**     | `config.params["key"] as? Type` | `config.getParam(P.X)`            |
| **类型安全**     | ❌ 需要手动转换和空值检查                   | ✅ 自动类型推断                          |
| **分组支持**     | ❌ 不支持                           | ✅ 使用 `group("标题") { ... }`        |
| **代码复用**     | ❌ key 和 default 分散              | ✅ 集中定义，复用性强                       |
| **IDE 自动补全** | ⚠️ 仅字符串                         | ✅ 完整的类型提示                         |

**ParamDef 定义规则：**

```kotlin
object P {
    // 基础类型参数
    val MAX_SPEED = ParamDef("maxSpeed", 120)           // Int
    val UNIT = ParamDef("unit", "km/h")                 // String
    val ENABLED = ParamDef("enabled", true)             // Boolean
    val SCALE_FACTOR = ParamDef("scaleFactor", 1.5f)    // Float
    val COLOR = ParamDef("color", Color(0xFF2196F3))    // Color

    // 数据源参数（特殊处理）
    const val DATASOURCE = "datasource"

    // 非参数常量（不会显示在配置界面）
    val WIDTH = 200.dp
    val HEIGHT = 100.dp
}
```

### 参数类型说明

| WidgetParamType | 用途        | 示例                    |
|-----------------|-----------|-----------------------|
| `STRING`        | 文本、颜色、选项  | `"#FF0000"`, `"km/h"` |
| `INT`           | 整数值       | `120`, `60`           |
| `FLOAT`         | 小数值       | `75.5`, `1.5`         |
| `BOOL`          | 布尔值       | `true`, `false`       |
| `COLOR`         | 颜色选择器     | `"#2196F3"`           |
| `SCALE`         | 缩放（自动注入）  | `1.0`, `1.5`          |
| `ALPHA`         | 透明度（自动注入） | `1.0`, `0.5`          |
| `DATA_SOURCE`   | 数据源绑定     | VHAL 属性               |
| `ENUM`          | 枚举选项      | 需配合 `options` 使用      |

### 推荐网格尺寸 (recommendedGrid)

`recommendedGrid` 参数用于建议小组件在仪表盘网格中的默认占用空间。格式为 `Pair(宽度, 高度)`，单位为网格格子数。

**常见尺寸推荐：**

| 类型   | 尺寸建议         | 示例小组件       | 说明            |
|------|--------------|-------------|---------------|
| 小型方形 | `Pair(1, 1)` | 电池、小图标      | 单个格子，适合紧凑显示   |
| 标准方形 | `Pair(2, 2)` | 信息卡片、仪表盘    | 2×2格子，常用尺寸    |
| 宽矩形  | `Pair(4, 2)` | 折线图、柱状图     | 横向宽屏，适合展示趋势数据 |
| 窄矩形  | `Pair(2, 1)` | 进度条、横向状态栏   | 扁平横条，适合进度显示   |
| 竖矩形  | `Pair(1, 2)` | 温度计、竖向进度条   | 竖直窄条，适合竖向仪表   |
| 宽屏   | `Pair(3, 2)` | 胎压监测、复杂信息面板 | 较宽显示区域        |

**示例：**

```kotlin
// 小型方形小组件
object BatteryWidgetSpec : WidgetSpec(
    type = "battery_widget",
    displayName = "电池",
    recommendedGrid = Pair(1, 1),  // 1×1 格子
    // ...
)

// 宽屏图表小组件
object LineChartWidgetSpec : WidgetSpec(
    type = "line_chart",
    displayName = "折线图",
    recommendedGrid = Pair(4, 2),  // 4×2 格子
    // ...
)
```

**注意事项：**

- ⚠️ `recommendedGrid` 仅作为UI建议，用户可以在编辑时调整实际大小
- ✅ 建议根据小组件的内容复杂度和显示需求设置合适的初始尺寸
- ✅ 确保小组件在推荐尺寸下能正常显示所有重要信息

### 参数配置示例

**第一步：在 object P 中定义参数**

```kotlin
object P {
    // 字符串参数
    val TITLE = ParamDef("title", "车速")

    // 枚举参数
    val THEME = ParamDef("theme", "light")

    // 颜色参数（使用 Color 类型）
    val BG_COLOR = ParamDef("backgroundColor", Color.White)

    // 数据源参数（只定义 key）
    const val DATASOURCE = "datasource"
}
```

**第二步：在 paramSchema 中使用 buildParams DSL**

```kotlin
paramSchema = WidgetParamDesc.buildParams {
    group("基础设置") {
        // 字符串参数（自由输入）
        +WidgetParamDesc(
            key = P.TITLE.key,
            label = "标题",
            type = WidgetParamType.STRING,
            defaultValue = P.TITLE.default,
            required = false,  // 是否必填
            description = "显示在顶部的标题文字"
        )

        // 枚举参数（下拉选择）
        +WidgetParamDesc(
            key = P.THEME.key,
            label = "主题",
            type = WidgetParamType.ENUM,
            defaultValue = P.THEME.default,
            options = listOf("light", "dark", "auto")
        )
    }

    group("样式") {
        // 颜色参数
        +WidgetParamDesc(
            key = P.BG_COLOR.key,
            label = "背景色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BG_COLOR.default.toHexString()  // Color 需要转换为十六进制
        )
    }

    group("数据源") {
        // 数据源绑定（连接 VHAL 属性）
        +WidgetParamDesc(
            key = P.DATASOURCE,
            label = "速度数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = null,
            required = true,
            description = "选择车速信号来源"
        )
    }
}
```

**第三步：在 Composable 中使用参数**

```kotlin
@Composable
fun MyWidgetContent(config: WidgetConfig) {
    val title = config.getParam(MyWidgetSpec.P.TITLE)      // String
    val theme = config.getParam(MyWidgetSpec.P.THEME)      // String
    val bgColor = config.getParam(MyWidgetSpec.P.BG_COLOR) // Color
    val speed = config.getDataSourceFloat(MyWidgetSpec.P.DATASOURCE, 0f)  // Float

    // 使用参数渲染 UI
}
```

---

## 🎨 实现 UI 内容

### Composable 函数

```kotlin
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.getAlpha

@Composable
fun SpeedometerWidgetContent(config: WidgetConfig) {
    // 1️⃣ 读取自动注入的参数（使用扩展函数）
    val scale = config.getScale()    // 默认 1.0f
    val alpha = config.getAlpha()    // 默认 1.0f

    // 2️⃣ 读取自定义参数（使用类型安全的 getParam）
    val maxSpeed = config.getParam(SpeedometerWidgetSpec.P.MAX_SPEED)  // 自动推断为 Int
    val unit = config.getParam(SpeedometerWidgetSpec.P.UNIT)           // 自动推断为 String
    val color = config.getParam(SpeedometerWidgetSpec.P.COLOR)          // 自动推断为 Color

    // 3️⃣ 构建 UI（应用 scale 和 alpha）
    Box(
        modifier = Modifier
            .width(SpeedometerWidgetSpec.P.WIDTH * scale)   // 使用定义的宽度常量
            .height(SpeedometerWidgetSpec.P.HEIGHT * scale) // 使用定义的高度常量
            .graphicsLayer(alpha = alpha),                  // 应用透明度
        contentAlignment = Alignment.Center
    ) {
        // 绘制速度表内容
        Canvas(modifier = Modifier.matchParentSize()) {  // 内部使用 matchParentSize
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

### 参数获取方式对比

**❌ 旧方式（不推荐）：**

```kotlin
// 需要手动类型转换和空值检查
val maxSpeed = (config.params["maxSpeed"] as? Number)?.toInt() ?: 120
val colorStr = config.params["color"]?.toString() ?: "#2196F3"
val color = Color(android.graphics.Color.parseColor(colorStr))  // 可能抛异常
```

**✅ 新方式（推荐）：**

```kotlin
// 类型安全，自动推断，无需转换
val maxSpeed = config.getParam(SpeedometerWidgetSpec.P.MAX_SPEED)  // Int
val color = config.getParam(SpeedometerWidgetSpec.P.COLOR)          // Color
```

**优势说明：**

- ✅ **自动类型推断**：根据 ParamDef 的泛型自动推断返回类型
- ✅ **颜色主题支持**：Color 类型参数自动支持深色/浅色主题切换
- ✅ **零空指针风险**：始终返回有效值（defaultValue）
- ✅ **编译期检查**：参数名错误会在编译时发现

### 使用数据源绑定

如果你的小组件需要从 VHAL 读取实时数据：

```kotlin
import com.neta.isulewtools.api.widget.getDataSourceFloat

@Composable
fun SpeedometerWidgetContent(config: WidgetConfig) {
    // ✅ 使用便捷方法读取数据源的 Float 值
    val speed = config.getDataSourceFloat(
        key = SpeedometerWidgetSpec.P.DATASOURCE,
        defaultValue = 0f
    )

    // 渲染速度表
    Canvas(modifier = Modifier.matchParentSize()) {
        // 使用 speed 绘制表盘
        drawSpeedometer(speed)
    }
}
```

**数据源相关扩展函数：**

```kotlin
// 获取 Float 类型数据
val value = config.getDataSourceFloat("datasource", 0f)

// 获取 Int 类型数据
val count = config.getDataSourceInt("datasource", 0)

// 获取 Boolean 类型数据
val isActive = config.getDataSourceBoolean("datasource", false)

// 获取 String 类型数据
val text = config.getDataSourceString("datasource", "")
```

**完整示例（带数据源）：**

```kotlin
object SpeedometerWidgetSpec : WidgetSpec(
    // ...
    paramSchema = WidgetParamDesc.buildParams {
        +WidgetParamDesc(
            key = P.DATASOURCE,
            label = "速度数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = null,
            required = true,
            description = "选择车速信号来源"
        )
    }
) {
    object P {
        const val DATASOURCE = "datasource"  // 数据源只定义 key
    }
}

@Composable
fun SpeedometerWidgetContent(config: WidgetConfig) {
    val speed = config.getDataSourceFloat(SpeedometerWidgetSpec.P.DATASOURCE, 0f)

    // 使用 speed 渲染 UI
    Text("当前车速: $speed km/h")
}
```

### 添加 Compose Preview 预览

为了方便在 Android Studio 中预览小组件效果，你可以添加 `@Preview` 注解：

```kotlin
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight

@Preview
@Composable
fun SpeedometerWidgetPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))  // 背景色
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("不同速度状态", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        // 不同速度值
        SpeedometerWidgetContent(
            WidgetConfig(params = mapOf("maxSpeed" to 120, "color" to "#2196F3"))
        )
        SpeedometerWidgetContent(
            WidgetConfig(params = mapOf("maxSpeed" to 180, "color" to "#FF5722"))
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("不同缩放", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        SpeedometerWidgetContent(
            WidgetConfig(params = mapOf("maxSpeed" to 120, "scale" to 1.5f))
        )
        SpeedometerWidgetContent(
            WidgetConfig(params = mapOf("maxSpeed" to 120, "scale" to 0.8f))
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("不同透明度", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        SpeedometerWidgetContent(
            WidgetConfig(params = mapOf("maxSpeed" to 120, "alpha" to 1f))
        )
        SpeedometerWidgetContent(
            WidgetConfig(params = mapOf("maxSpeed" to 120, "alpha" to 0.5f))
        )
    }
}
```

**预览示例的好处：**

- ✅ 在 Android Studio 中实时预览 UI
- ✅ 快速测试不同参数组合
- ✅ 无需运行应用即可查看效果
- ✅ 支持交互式预览（Interactive Mode）

**添加预览依赖：**

确保在 `build.gradle.kts` 中添加了预览支持：

```kotlin
dependencies {
    compileOnly("androidx.compose.ui:ui-tooling-preview:1.9.4")  // Preview 注解支持
    // ... 其他依赖
}
```

**示例参考：**

可以查看 `SimpleBatteryWidget.kt` 中的预览示例：`SimpleBatteryWidgetPreview()`

---

## ⚙️ 配置构建脚本

### 0. MANIFEST.MF 自动生成

**重要变更：** 从 v1.8.0 开始，JAR 文件使用 MANIFEST.MF 自描述机制，并且**完全自动化**。

`build.gradle.kts` 中的 `CreateWidgetJarTask` 会：
1. **自动扫描源代码**查找实现 `WidgetPlugin` 的类
2. 提取完整的类路径（包名 + 类名）
3. 自动写入 MANIFEST.MF

```kotlin
// 自动查找实现 WidgetPlugin 接口的类
val pluginClassName = findPluginClass()
println("🔍 Detected plugin class: $pluginClassName")

// 创建 MANIFEST.MF
val manifest = Manifest()
manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION] = "1.0"
manifest.mainAttributes[Attributes.Name("Plugin-Class")] = pluginClassName

// 打包到 JAR（自动包含 MANIFEST）
JarOutputStream(FileOutputStream(outputJarFile), manifest).use { jarOut ->
    // ...
}
```

**你无需做任何事！** 只要：
1. ✅ 创建一个实现 `WidgetPlugin` 的类
2. ✅ 在 `getMetadata()` 中使用 `this::class.qualifiedName!!`
3. ✅ 运行 Gradle 构建任务

**优点：**
- ✅ 零手动配置，自动检测插件类
- ✅ 插件类重命名/移动时无需修改构建脚本
- ✅ JAR 自己声明入口类，无需外部配置
- ✅ 加载时自动读取，不会出错
- ✅ 符合 Java 标准

### 1. 依赖配置

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

### 2. JAR 生成任务

```kotlin
android.buildTypes.forEach { buildType ->
    val variantName = buildType.name
    val variantCapped = variantName.replaceFirstChar { it.uppercaseChar() }

    val jarTask = tasks.register<CreateWidgetJarTask>("create${variantCapped}WidgetJar") {
        classDirs.from(
            layout.buildDirectory.dir("intermediates/javac/${variantName}/compile${variantCapped}JavaWithJavac/classes"),
            layout.buildDirectory.dir("tmp/kotlin-classes/${variantName}")
        )

        // 修改输出文件名
        val generatedDir = layout.buildDirectory.dir("outputs/widget/${variantName}")
        outputJar.set(layout.file(generatedDir.map {
            it.asFile.resolve("speedometer-widget.jar")  // 修改文件名
        }))

        // ... 其他配置
    }
}
```

### 3. 复制必要文件

确保你的模块包含这些文件（从主应用复制）：

```
src/main/java/com/neta/isulewtools/widget/
├── WidgetSpec.kt          # 小组件规格定义
├── plugin/
│   └── WidgetPlugin.kt    # 插件接口
```

---

## 🧪 编译和测试

### 编译 JAR

**方式一：编译所有小组件（推荐用于发布）**

```bash
# 清理构建缓存
./gradlew :plugin-widgets:clean

# 编译所有小组件的 Release JAR
./gradlew createReleaseWidgetJar

# 查看生成的所有 JAR
ls -lh plugin-widgets/build/outputs/widget/release/
```

**方式二：编译单个小组件（推荐用于开发调试）**

```bash
# 清理构建缓存
./gradlew :plugin-widgets:clean

# 编译指定小组件（首字母大写）
# 格式: createRelease{WidgetName}WidgetJar
./gradlew :plugin-widgets:createReleaseBatteryWidgetJar      # battery 小组件
./gradlew :plugin-widgets:createReleaseLinechartWidgetJar    # linechart 小组件
./gradlew :plugin-widgets:createReleaseInfochartWidgetJar    # infochart 小组件
./gradlew :plugin-widgets:createReleaseBarchartWidgetJar     # barchart 小组件

# 查看生成的 JAR
ls -lh plugin-widgets/build/outputs/widget/release/info_chart.jar
```

**方式三：使用 assembleRelease（会触发所有任务）**

```bash
# 编译整个模块（包含所有小组件）
./gradlew :plugin-widgets:assembleRelease

# 查看生成的所有 JAR
ls -lh plugin-widgets/build/outputs/widget/release/
```

**JAR 输出位置说明：**

- 📁 **输出目录：** `plugin-widgets/build/outputs/widget/release/`
- 📄 **文件名格式：** `{widget-id}.jar`（使用 WidgetPluginMetadata 中定义的 id）
- 📝 **示例：**
    - `info_chart.jar` (信息卡片小组件)
    - `line_chart.jar` (折线图小组件)
    - `bar_chart.jar` (柱状图小组件)
    - `widget_battery.jar` (电池小组件)

**注意事项：**

- ✅ `createReleaseWidgetJar` 是聚合任务，会编译所有小组件
- ✅ `createRelease{WidgetName}WidgetJar` 只编译单个小组件，速度更快
- ✅ JAR 文件名使用小组件的 `id` 字段，不是模块名
- ⚠️ 首次编译可能需要下载依赖，时间较长

### 安装到设备

**临时加载本地小组件目录：** `/sdcard/Download/neta_connect`

```bash
# 创建目录（如果不存在）
adb shell mkdir -p /sdcard/Download/neta_connect

# 推送 JAR 文件（示例：推送信息卡片小组件）
adb push plugin-widgets/build/outputs/widget/release/info_chart.jar \
    /sdcard/Download/neta_connect/

# 推送多个小组件
adb push plugin-widgets/build/outputs/widget/release/line_chart.jar \
    /sdcard/Download/neta_connect/
adb push plugin-widgets/build/outputs/widget/release/bar_chart.jar \
    /sdcard/Download/neta_connect/

# 查看日志
adb logcat | grep -E "WidgetLoader|WidgetManager"
```

**注意：**

- 📁 主应用会自动扫描 `/sdcard/Download/neta_connect` 目录下的所有 `.jar` 文件
- 🔄 推送新的 JAR 文件后，重启主应用即可加载更新
- 🗑️ 删除目录中的 JAR 文件并重启应用，可卸载对应的小组件

### 验证加载

启动主应用并检查日志：

```
✓ 注册动态小组件: speedometer_widget v1.0.0
  原始 type='speedometer_widget' → 自动前缀 type='dynamic_speedometer_widget'
  displayName='车速表'
  Plugin-Class: com.neta.widgets.speedometer.SpeedometerWidgetPlugin (from MANIFEST.MF)
```

---

## 🚀 高级主题

### 1. 自动注入的 scale 和 alpha

**工作原理：**

`WidgetSpec` 构造函数会自动检测 `paramSchema` 中是否包含 `scale` 和 `alpha`：

```kotlin
// WidgetSpec.kt (简化版)
class WidgetSpec(
    paramSchema: List<WidgetParamDesc>
) {
    val paramSchema: List<WidgetParamDesc> = ensureRequiredParams(paramSchema)

    companion object {
        private fun ensureRequiredParams(params: List<WidgetParamDesc>): List<WidgetParamDesc> {
            val requiredParams = mutableListOf<WidgetParamDesc>()

            if (!params.any { it.key == "scale" }) {
                requiredParams.add(
                    WidgetParamDesc("scale", "缩放", WidgetParamType.SCALE, 1f)
                )
            }

            if (!params.any { it.key == "alpha" }) {
                requiredParams.add(
                    WidgetParamDesc("alpha", "透明度", WidgetParamType.ALPHA, 1f)
                )
            }

            return requiredParams + params  // 必需参数在前
        }
    }
}
```

**使用示例：**

```kotlin
// ✅ 推荐：让系统自动注入
paramSchema = WidgetParamDesc.buildParams {
    +WidgetParamDesc(
        key = P.SPEED.key,
        label = "速度",
        type = WidgetParamType.FLOAT,
        defaultValue = P.SPEED.default
    )
    // scale 和 alpha 自动添加
}

// ❌ 不推荐：手动定义（重复且容易出错）
paramSchema = WidgetParamDesc.buildParams {
    +WidgetParamDesc("scale", "缩放", WidgetParamType.SCALE, 1f)
    +WidgetParamDesc("alpha", "透明度", WidgetParamType.ALPHA, 1f)
    +WidgetParamDesc(P.SPEED.key, "速度", ...)
}
```

**在 Composable 中使用：**

```kotlin
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.getAlpha

@Composable
fun MyWidgetContent(config: WidgetConfig) {
    // ✅ 使用扩展函数读取 scale 和 alpha
    val scale = config.getScale()  // 默认 1.0f
    val alpha = config.getAlpha()  // 默认 1.0f

    Box(
        modifier = Modifier
            .width(MyWidgetSpec.P.WIDTH * scale)
            .height(MyWidgetSpec.P.HEIGHT * scale)
            .graphicsLayer(alpha = alpha)
    ) {
        // 小组件内容
    }
}
```

### 2. 响应式布局

适配不同缩放比例：

```kotlin
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.getAlpha

@Composable
fun ResponsiveWidget(config: WidgetConfig) {
    // ✅ 使用扩展函数获取缩放值
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 基础尺寸（定义在 object P 中）
    val baseSize = 100.dp
    val baseFontSize = 16.sp
    val basePadding = 8.dp

    Box(
        modifier = Modifier
            .size(baseSize * scale)          // 尺寸缩放
            .padding(basePadding * scale)    // 内边距缩放
            .graphicsLayer(alpha = alpha)    // 应用透明度
    ) {
        Text(
            text = "内容",
            fontSize = baseFontSize * scale  // 字体缩放
        )
    }
}
```

**或者使用便捷的 Modifier 扩展：**

```kotlin
import com.neta.isulewtools.api.widget.applyWidgetTransform

@Composable
fun ResponsiveWidget(config: WidgetConfig) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .applyWidgetTransform(config)  // 自动应用 scale 和 alpha
    ) {
        Text("内容")
    }
}
```

### 3. 性能优化

```kotlin
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale

@Composable
fun OptimizedWidget(config: WidgetConfig) {
    // ✅ getParam 内部已做优化，无需手动 remember
    val scale = config.getScale()
    val color = config.getParam(MyWidgetSpec.P.COLOR)

    // ✅ 缓存计算结果
    val scaledSize = remember(scale) { 100.dp * scale }

    // ✅ 使用 key 优化重组
    key(config.params.hashCode()) {
        // Widget 内容
    }

    // ✅ 对于复杂的数据处理，使用 derivedStateOf
    val processedData = remember {
        derivedStateOf {
            // 复杂计算
        }
    }
}
```

### 4. 错误处理

```kotlin
import com.neta.isulewtools.api.widget.getParam

@Composable
fun SafeWidget(config: WidgetConfig) {
    // ✅ 使用 getParam 自动提供默认值，无需手动异常处理
    val color = config.getParam(MyWidgetSpec.P.COLOR)  // 自动返回 ParamDef 中的 default
    val title = config.getParam(MyWidgetSpec.P.TITLE)

    // 正常渲染（无需 try-catch）
    NormalContent(color, title)
}
```

**对于数据源的错误处理：**

```kotlin
@Composable
fun SafeWidgetWithDataSource(config: WidgetConfig) {
    // ✅ 数据源读取失败时自动返回默认值
    val speed = config.getDataSourceFloat(MyWidgetSpec.P.DATASOURCE, 0f)

    // 可选：显示数据源状态
    val hasDataSource = config.params.containsKey(MyWidgetSpec.P.DATASOURCE)

    if (!hasDataSource) {
        // 提示用户配置数据源
        ErrorContent("请配置数据源")
    } else {
        // 正常显示
        NormalContent(speed)
    }
}
```

---

## 📝 检查清单

发布前确保：

- [ ] 插件类正确实现了 `WidgetPlugin` 接口（构建脚本会自动检测并写入 MANIFEST.MF）
- [ ] 所有参数都有合理的默认值
- [ ] 应用了 scale 和 alpha 到 UI
- [ ] 使用 `compileOnly` 声明依赖
- [ ] JAR 文件大小合理（< 500KB）
- [ ] 在不同缩放比例下测试
- [ ] 测试透明度效果
- [ ] 版本号遵循语义化规范
- [ ] 编写了清晰的 description

---

## 🐛 常见问题

### Q1: 为什么小组件不显示？

**A:** 检查以下几点：

1. JAR 是否成功加载（查看日志）
2. MANIFEST.MF 中的 Plugin-Class 是否正确（使用 `unzip -p your.jar META-INF/MANIFEST.MF` 查看）
3. scale 和 alpha 是否被正确应用
4. 是否有编译错误（查看 Logcat）

### Q2: 如何调试动态小组件？

**A:** 使用日志输出：

```kotlin
@Composable
fun DebugWidget(config: WidgetConfig) {
    println("Widget params: ${config.params}")
    println("Scale: ${config.params["scale"]}")
    println("Alpha: ${config.params["alpha"]}")

    // 渲染内容
}
```

### Q3: 为什么我的修改没有生效？

**A:** 尝试以下步骤：

1. 重新编译 JAR
2. 重新推送到设备
3. 在主应用中卸载并重新安装小组件
4. 重启主应用

### Q4: 可以使用哪些 Android API？

**A:** 你可以使用：

- ✅ Jetpack Compose UI
- ✅ Kotlin 标准库
- ✅ Android 基础类（Color, Canvas 等）
- ❌ 资源文件（R.drawable, R.string）
- ❌ 主应用的私有类

---

## 📚 延伸阅读

- [WidgetSpec API 文档](../docs/widget-spec-api.md)
- [主应用架构说明](../README.md)
- [Compose 最佳实践](https://developer.android.com/jetpack/compose/best-practices)

---

## 💡 灵感来源

参考现有小组件获取灵感：

- `BatteryWidget` - 简单的数值显示
- `ClockWidget` - Canvas 绘图
- `ButtonWidget` - 交互式控制
- `SpeedometerWidget` - 复杂仪表盘

---

祝你开发愉快！🎉
