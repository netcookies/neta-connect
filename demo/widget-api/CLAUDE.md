# widget-api 模块文档

[根目录](../CLAUDE.md) > **widget-api**

---

## 模块职责

widget-api 模块定义了小组件插件系统的核心 API 接口，为第三方开发者提供统一的插件开发规范。该模块作为主应用和插件之间的契约，确保插件能够正确加载和运行。

**核心功能**:
- 定义 `WidgetPlugin` 接口
- 提供插件元数据对象
- 定义字体和样式 API
- 提供 Compose Runtime 依赖

---

## 入口与启动

### 核心接口

**WidgetPlugin.kt**:
```kotlin
package com.neta.isulewtools.api.widget.plugin

import com.neta.isulewtools.api.widget.WidgetSpec

/**
 * 小组件插件接口
 * 所有动态加载的小组件都需要实现此接口
 */
interface WidgetPlugin {
    /**
     * 获取小组件规格定义
     */
    fun getSpec(): WidgetSpec

    /**
     * 插件元信息
     */
    fun getMetadata(): WidgetPluginMetadata
}
```

### 元数据对象

**WidgetPlugin.kt**:
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

说明：
- `signer` 与 `certificateSha256` 当前是可选兼容字段。
- 运行时真实 trust chain 以主应用读取到的 JAR 实际签名证书链为准，不要求插件作者手工填写这两个值。

---

## 对外接口

### 字体 API

**WidgetFont.kt**:
```kotlin
package com.neta.isulewtools.api.widget

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

/**
 * 小组件字体定义
 * 提供统一的字体样式
 */
object WidgetFont {
    // Montserrat 字体家族
    val MontserratFamily = FontFamily(
        Font(R.font.montserrat_regular, FontWeight.Normal),
        Font(R.font.montserrat_bold, FontWeight.Bold)
    )
}
```

### 参数约定

插件通过 `params` 接收数据，主应用负责提供以下标准参数：

**车辆数据参数**:
- `speed` (Float) - 车速 (km/h)
- `rpm` (Int) - 发动机转速
- `batteryLevel` (Float) - 电池电量 (0.0 - 1.0)
- `batteryVoltage` (Float) - 电池电压 (V)
- `batteryCurrent` (Float) - 电池电流 (A)
- `batteryTemperature` (Float) - 电池温度 (°C)
- `isCharging` (Boolean) - 是否充电中
- `gear` (String) - 当前档位 (P/R/N/D)
- `tirePressure` (Map<String, Float>) - 胎压数据

**UI 参数**:
- `theme` (String) - 主题 (light/dark)
- `accentColor` (Color) - 强调色
- `backgroundColor` (Color) - 背景色

---

## 关键依赖与配置

### Gradle 配置

**build.gradle.kts**:
```kotlin
android {
    namespace = "com.neta.isulewtools.widget"
    compileSdk = 36

    buildFeatures {
        compose = true
    }
}

dependencies {
    // 使用 BOM 确保与主 App 的 Compose 版本一致
    api(platform(libs.androidx.compose.bom))

    // 只包含 API 定义所需的最小依赖
    api(libs.androidx.compose.runtime)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui)

    // Material Icons - 用于插件开发
    api(libs.androidx.compose.material.icons.extended)
}
```

### 版本兼容性

**重要**: widget-api 必须与主应用使用相同的 Compose BOM 版本，以确保插件能够正确加载。

---

## 数据模型

### 插件生命周期

```
1. 主应用扫描 JAR 文件
2. 读取 MANIFEST.MF 元数据
3. 加载插件类 (通过 DexClassLoader)
4. 实例化 WidgetPlugin
5. 调用 `getSpec()`，使用 `WidgetSpec.contentComposable` 渲染 UI
6. 读取 `getMetadata()`，执行版本/分类/信任元数据处理
7. 卸载插件 (可选)
```

### 插件分类

| 分类 | 说明 | 示例 |
|------|------|------|
| `vehicle` | 车辆相关 | 电池、胎压、档位 |
| `chart` | 图表类 | 折线图、柱状图、进度条 |
| `info` | 信息卡片 | 信息卡片、迷你卡片 |
| `gauge` | 仪表盘 | 弧形仪表、圆形仪表 |
| `demo` | 演示示例 | 演示插件 |

---

## 测试与质量

### API 稳定性

widget-api 作为公开 API，必须保持向后兼容：
- 不能删除或修改现有接口
- 新增功能使用默认参数或扩展函数
- 遵循语义化版本规范

### 版本检查

主应用在加载插件时会检查版本兼容性：
```kotlin
fun isCompatible(pluginMinVersion: String, appVersion: String): Boolean {
    return VersionComparator.compare(appVersion, pluginMinVersion) >= 0
}
```

---

## 常见问题 (FAQ)

### Q: 如何确保插件与主应用兼容？

1. 使用相同的 Compose BOM 版本
2. 在 `WidgetPluginMetadata` 中指定 `minAppVersion`
3. 避免使用主应用的内部 API

### Q: 插件可以访问哪些 API？

插件只能访问 widget-api 模块提供的公开 API：
- `WidgetPlugin` 接口
- `WidgetFont` 字体定义
- Compose Runtime API
- Material Icons

### Q: 如何处理不同的主题？

通过 `params["theme"]` 获取当前主题：
```kotlin
val isDarkTheme = params["theme"] == "dark"
val textColor = if (isDarkTheme) Color.White else Color.Black
```

---

## 相关文件清单

**API 定义**:
- `src/main/java/com/neta/isulewtools/api/widget/plugin/WidgetPlugin.kt`
- `src/main/java/com/neta/isulewtools/api/widget/WidgetFont.kt`

**配置文件**:
- `build.gradle.kts`
- `src/main/AndroidManifest.xml`
- `consumer-rules.pro`

---

## 变更记录 (Changelog)

### 2026-02-06 - 模块文档创建
- 创建 widget-api 模块详细文档
- 记录核心接口定义
- 添加参数约定说明
- 补充版本兼容性指南

---

*本文档由 Claude Code 自动生成，最后更新: 2026-02-06 20:43:11*
