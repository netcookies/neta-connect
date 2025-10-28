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
import com.neta.isulewtools.widget.WidgetConfig
import com.neta.isulewtools.widget.WidgetParamDesc
import com.neta.isulewtools.widget.WidgetParamType
import com.neta.isulewtools.widget.WidgetSpec

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

### 参数配置示例

```kotlin
// 字符串参数（自由输入）
WidgetParamDesc(
    key = "title",
    label = "标题",
    type = WidgetParamType.STRING,
    defaultValue = "车速",
    required = false,  // 是否必填
    description = "显示在顶部的标题文字"
)

// 枚举参数（下拉选择）
WidgetParamDesc(
    key = "theme",
    label = "主题",
    type = WidgetParamType.ENUM,
    defaultValue = "light",
    options = listOf("light", "dark", "auto")
)

// 颜色参数
WidgetParamDesc(
    key = "backgroundColor",
    label = "背景色",
    type = WidgetParamType.COLOR,
    defaultValue = "#FFFFFF"
)

// 数据源绑定（连接 VHAL 属性）
WidgetParamDesc(
    key = "datasource",
    label = "速度数据源",
    type = WidgetParamType.DATA_SOURCE,
    defaultValue = null,
    required = true,
    description = "选择车速信号来源"
)
```

---

## 🎨 实现 UI 内容

### Composable 函数

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

### 使用数据源绑定

如果你的小组件需要从 VHAL 读取实时数据：

```kotlin
@Composable
fun SpeedometerWidgetContent(config: WidgetConfig) {
    // 获取数据源配置
    val subscribedProperty = config.getSubscribedProperty("datasource")
    val propertyId = subscribedProperty?.propertyId
    val areaId = subscribedProperty?.areaId

    // 读取实时数据（需要主应用支持）
    val propertyValue = if (propertyId != null && areaId != null) {
        getWidgetPropertyValue(propertyId, areaId).value
    } else null

    val speed = propertyValue?.toFloatOrNull() ?: 0f

    // 使用 speed 值渲染 UI
    // ...
}
```

⚠️ **注意：** `getWidgetPropertyValue` 是主应用提供的函数，动态小组件无法直接使用。你需要：

1. 在参数中接收静态值
2. 或在主应用中创建辅助工具类供动态小组件调用

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

```bash
# 清理并编译
./gradlew :widget-speedometer:clean :widget-speedometer:assembleDebug

# 查找生成的 JAR
ls -lh widget-speedometer/build/outputs/widget/debug/
```

### 安装到设备

```bash
# 创建目录（如果不存在）
adb shell mkdir -p /sdcard/isulewTools/widgets

# 推送 JAR 文件
adb push widget-speedometer/build/outputs/widget/debug/speedometer-widget.jar \
    /sdcard/isulewTools/widgets/

# 查看日志
adb logcat | grep -E "WidgetLoader|WidgetManager"
```

### 验证加载

启动主应用并检查日志：

```
✓ 注册动态小组件: speedometer_widget v1.0.0
  原始 type='speedometer_widget' → 自动前缀 type='dynamic_speedometer_widget'
  displayName='车速表', pluginClass=com.neta.widgets.speedometer.SpeedometerWidgetPlugin
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

### 2. 响应式布局

适配不同缩放比例：

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
            .size(baseSize * scale)  // 尺寸缩放
            .padding(basePadding * scale)  // 内边距缩放
    ) {
        Text(
            text = "内容",
            fontSize = baseFontSize * scale  // 字体缩放
        )
    }
}
```

### 3. 性能优化

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

### 4. 错误处理

```kotlin
@Composable
fun SafeWidget(config: WidgetConfig) {
    try {
        val colorStr = config.params["color"]?.toString() ?: "#FFFFFF"
        val color = Color(android.graphics.Color.parseColor(colorStr))

        // 正常渲染
        NormalContent(color)
    } catch (e: Exception) {
        // 降级显示
        ErrorContent("参数配置错误: ${e.message}")
    }
}
```

---

## 📝 检查清单

发布前确保：

- [ ] `pluginClass` 路径完全正确
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
2. `pluginClass` 路径是否正确
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
