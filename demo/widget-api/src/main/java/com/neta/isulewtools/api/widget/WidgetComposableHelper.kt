package com.neta.isulewtools.api.widget

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

/**
 * Widget Composable 辅助工具
 *
 * 提供统一的扩展函数来处理小组件参数、数据源、颜色和 UI 效果
 *
 * 文件结构：
 * 1. 基础参数获取方法
 * 2. 颜色相关方法
 * 3. 数据源获取方法（核心功能）
 * 4. UI 辅助方法
 */

// ===============================
// 1. 基础参数获取方法
// ===============================

/**
 * Widget 参数定义
 *
 * 用于在运行时读取参数值,结合 key 和 default 值,支持 Kotlin 属性委托语法
 *
 * 使用示例:
 * ```
 * object ButtonWidgetSpec : WidgetSpec(...) {
 *     object P {
 *         val TEXT = ParamDef("text", "按钮")
 *         val TEXT_COLOR = ParamDef("textColor", Color(0xFF007AFF))
 *         val SHOW_BORDER = ParamDef("showBorder", true)
 *     }
 * }
 *
 * @Composable
 * fun ButtonWidgetContent(config: WidgetConfig) {
 *     // 方式1: 使用委托属性 (推荐，支持主题感知颜色)
 *     val text by config.param(ButtonWidgetSpec.P.TEXT)
 *     val textColor by config.param(ButtonWidgetSpec.P.TEXT_COLOR)
 *
 *     // 方式2: 使用扩展函数
 *     val showBorder = config.getParam(ButtonWidgetSpec.P.SHOW_BORDER)
 * }
 * ```
 *
 * @param T 参数值的类型
 * @param key 参数的唯一标识符
 * @param default 参数的默认值
 */
data class ParamDef<T>(val key: String, val default: T)

/**
 * 从 WidgetConfig 中获取参数值（支持主题感知颜色）
 *
 * 当参数类型为 Color 时，会自动从注入的主题状态获取对应的颜色
 * 可在任何上下文中使用（Composable、LaunchedEffect、事件处理函数等）
 *
 * 使用示例:
 * ```
 * fun MyWidget(config: WidgetConfig) {
 *     val text = config.getParam(ButtonWidgetSpec.P.TEXT)
 *     val textColor = config.getParam(ButtonWidgetSpec.P.TEXT_COLOR)  // 自动支持主题切换
 *
 *     // 可在任何地方使用
 *     LaunchedEffect(key) {
 *         val color = config.getParam(MySpec.P.COLOR)
 *     }
 *
 *     fun onClick() {
 *         val color = config.getParam(MySpec.P.COLOR)
 *     }
 * }
 * ```
 *
 * @param param 参数定义
 * @return 参数值
 */
@Suppress("UNCHECKED_CAST")
fun <T> WidgetConfig.getParam(param: ParamDef<T>): T {
    val isDarkTheme = getIsDarkTheme()  // 从注入的参数中获取主题状态

    return when (val defaultValue = param.default) {
        is String -> getString(param.key, defaultValue)
        is Color -> getThemeColor(param.key, isDarkTheme, defaultValue)
        is Float -> getFloat(param.key, defaultValue)
        is Boolean -> getBoolean(param.key, defaultValue)
        is Int -> getInt(param.key, defaultValue)
        else -> defaultValue
    } as T
}

/**
 * 从 WidgetConfig 中获取注入的主题状态
 *
 * 主题状态由 DataSourceInjector 在渲染前注入到 config 中
 *
 * @return 当前是否为暗色主题
 */
fun WidgetConfig.getIsDarkTheme(): Boolean {
    return (params["__isDarkTheme"] as? Boolean) ?: false
}

/**
 * 从 WidgetConfig 中安全获取字符串值（适用于 STRING 和 ENUM 类型）
 *
 * 使用示例：
 * ```
 * val clockType = config.getString("clockType", "analog")
 * val orientation = config.getString("orientation", "horizontal")
 * ```
 *
 * @param key 参数键
 * @param defaultValue 默认值
 * @return 字符串值
 */
fun WidgetConfig.getString(key: String, defaultValue: String): String {
    return params[key]?.toString() ?: defaultValue
}

/**
 * 从 WidgetConfig 中安全获取布尔值
 *
 * 使用示例：
 * ```
 * val showSeconds = config.getBoolean("showSeconds", true)
 * val showText = config.getBoolean("showText", false)
 * ```
 *
 * @param key 参数键
 * @param defaultValue 默认值
 * @return 布尔值
 */
fun WidgetConfig.getBoolean(key: String, defaultValue: Boolean): Boolean {
    return (params[key] as? Boolean) ?: defaultValue
}

/**
 * 从 WidgetConfig 中安全获取浮点数值
 *
 * 使用示例：
 * ```
 * val cornerRadius = config.getFloat("cornerRadius", 8f)
 * val threshold = config.getFloat("threshold", 0.5f)
 * ```
 *
 * @param key 参数键
 * @param defaultValue 默认值
 * @return 浮点数值
 */
fun WidgetConfig.getFloat(key: String, defaultValue: Float): Float {
    return (params[key] as? Number)?.toFloat() ?: defaultValue
}

/**
 * 从 WidgetConfig 中安全获取整数值
 *
 * 使用示例：
 * ```
 * val maxValue = config.getInt("maxValue", 100)
 * val minValue = config.getInt("minValue", 0)
 * ```
 *
 * @param key 参数键
 * @param defaultValue 默认值
 * @return 整数值
 */
fun WidgetConfig.getInt(key: String, defaultValue: Int): Int {
    return (params[key] as? Number)?.toInt() ?: defaultValue
}

/**
 * 从 WidgetConfig 中获取缩放值
 * @return 缩放比例，默认为 1.0f
 */
fun WidgetConfig.getScale(): Float {
    return (params["scale"] as? Number)?.toFloat() ?: 1f
}

/**
 * 从 WidgetConfig 中获取透明度值
 * @return 透明度，范围 0.0-1.0，默认为 1.0f
 */
fun WidgetConfig.getAlpha(): Float {
    return (params["alpha"] as? Number)?.toFloat() ?: 1f
}

// ===============================
// 2. 颜色相关方法
// ===============================

/**
 * 主题感知颜色数据类
 *
 * 支持根据系统主题（亮色/暗色）自动切换颜色
 *
 * 使用示例：
 * ```
 * // 在参数定义中使用
 * val TEXT_COLOR = ParamDef("textColor", ThemeAwareColor(
 *     light = Color.Black,
 *     dark = Color.White
 * ))
 *
 * // 在小组件中使用
 * val textColor = config.getThemeColor("textColor", Color.Black)
 * ```
 *
 * @param light 亮色主题下的颜色（也可作为单色使用）
 * @param dark 暗色主题下的颜色（可选，如果不提供则与 light 相同）
 */
data class ThemeAwareColor(
    val light: String,
    val dark: String? = null
) {
    /**
     * 从 Color 对象构造
     */
    constructor(light: Color, dark: Color? = null) : this(
        light = light.toHexString(),
        dark = dark?.toHexString()
    )

    /**
     * 判断是否为主题感知颜色（是否配置了不同的亮暗色）
     */
    fun isThemeAware(): Boolean = dark != null && dark != light

    /**
     * 根据主题获取颜色字符串
     */
    fun getColorString(isDark: Boolean): String {
        return if (isDark && dark != null) dark else light
    }

    /**
     * 根据主题获取 Color 对象
     */
    fun getColor(isDark: Boolean): Color {
        return getColorString(isDark).toColorOrDefault(Color.White)
    }

    override fun toString(): String {
        return if (dark != null) {
            "$light|$dark"
        } else {
            light
        }
    }

    companion object {
        /**
         * 从字符串解析 ThemeAwareColor
         *
         * 支持格式：
         * - 单色：`#AARRGGBB` 或 `#RRGGBB`
         * - 双色：`#AARRGGBB|#AARRGGBB` 或 `#RRGGBB|#RRGGBB`
         */
        fun fromString(value: String): ThemeAwareColor {
            val parts = value.split("|")
            return if (parts.size == 2) {
                ThemeAwareColor(light = parts[0].trim(), dark = parts[1].trim())
            } else {
                ThemeAwareColor(light = value.trim())
            }
        }
    }
}

/**
 * 从 WidgetConfig 中获取主题感知颜色（根据应用主题自动切换）
 *
 * 支持两种格式：
 * - 单色格式：`#AARRGGBB` 或 `#RRGGBB`（亮暗主题使用相同颜色）
 * - 双色格式：`#AARRGGBB|#AARRGGBB`（第一个为亮色主题，第二个为暗色主题）
 *
 * 使用示例：
 * ```
 * @Composable
 * fun MyWidget(config: WidgetConfig, isDarkTheme: Boolean) {
 *     val textColor = config.getThemeColor("textColor", isDarkTheme, Color.Black)
 *     Text("Hello", color = textColor)
 * }
 * ```
 *
 * @param key 参数键
 * @param isDarkTheme 是否为暗色主题（通常从 ServiceStateStore.isDarkTheme 或 LocalWidgetDarkTheme 获取）
 * @param defaultColor 默认颜色（当解析失败或值不存在时使用）
 * @return Color 对象（根据当前主题返回对应颜色）
 */
fun WidgetConfig.getThemeColor(
    key: String,
    isDarkTheme: Boolean,
    defaultColor: Color = Color.White
): Color {
    val colorValue = params[key]?.toString() ?: return defaultColor

    // 尝试解析为 ThemeAwareColor
    val themeAwareColor = ThemeAwareColor.fromString(colorValue)
    return themeAwareColor.getColor(isDarkTheme)
}

/**
 * 从 WidgetConfig 中安全获取颜色值（不支持主题切换，使用单一颜色）
 *
 * 如果需要主题切换功能，请使用 getThemeColor() 方法
 *
 * 使用示例：
 * ```
 * val fillColor = config.getColor("fillColor", Color.Green)
 * val bgColor = config.getColor("backgroundColor")  // 默认 Color.White
 * ```
 *
 * @param key 参数键
 * @param defaultColor 默认颜色（当解析失败或值不存在时使用），默认为 Color.White
 * @return Color 对象
 */
fun WidgetConfig.getColor(key: String, defaultColor: Color = Color.White): Color {
    val colorStr = params[key]?.toString() ?: return defaultColor
    // 如果是双色格式，只取第一个颜色（亮色）
    val colorValue = if (colorStr.contains("|")) {
        colorStr.split("|")[0].trim()
    } else {
        colorStr
    }
    return colorValue.toColorOrDefault(defaultColor)
}

/**
 * 将颜色字符串（#AARRGGBB 或 #RRGGBB）转换为 Color
 *
 * 使用示例：
 * ```
 * val color1 = "#FF5733".toColorOrDefault()
 * val color2 = "#80FF5733".toColorOrDefault(Color.Red)
 * ```
 *
 * @param defaultColor 默认颜色（当解析失败时使用），默认为 Color.White
 * @return Color 对象
 */
fun String.toColorOrDefault(defaultColor: Color = Color.White): Color {
    return try {
        val colorStr = this.removePrefix("#")
        val colorLong = when (colorStr.length) {
            6 -> "FF$colorStr".toLong(16) // #RRGGBB -> #FFRRGGBB
            8 -> colorStr.toLong(16)      // #AARRGGBB
            else -> return defaultColor
        }
        // 显式解析 ARGB 分量以确保使用正确的 sRGB 颜色空间
        // 避免 Color(ULong) 构造函数可能导致的无效颜色空间索引
        Color(
            alpha = ((colorLong shr 24) and 0xFF) / 255f,
            red = ((colorLong shr 16) and 0xFF) / 255f,
            green = ((colorLong shr 8) and 0xFF) / 255f,
            blue = (colorLong and 0xFF) / 255f
        )
    } catch (_: Exception) {
        defaultColor
    }
}

/**
 * 将 Color 转换为十六进制字符串（格式：#AARRGGBB）
 *
 * 使用示例：
 * ```
 * val hexString = Color.Red.toHexString()  // "#FFFF0000"
 * val hexString2 = Color(0x80FF5733).toHexString()  // "#80FF5733"
 * ```
 *
 * @return 十六进制颜色字符串，格式为 #AARRGGBB
 */
fun Color.toHexString(): String {
    return String.format(
        "#%02x%02x%02x%02x",
        (this.alpha * 255).toInt(),
        (this.red * 255).toInt(),
        (this.green * 255).toInt(),
        (this.blue * 255).toInt()
    )
}

// ===============================
// 3. 数据源获取方法（核心功能）
// ===============================

/*
 * 📖 数据源 API 使用指南
 *
 * 本文件提供了多种数据源获取方法，适用于不同场景。请根据需求选择合适的API：
 *
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │ 场景                          │ 推荐方法                                │
 * ├─────────────────────────────────────────────────────────────────────────┤
 * │ 📱 显示文本（自动格式化）      │ getDataSourceDisplay()       ⭐ 推荐   │
 * │ 🔢 获取数值用于计算            │ getDataSourceFloat()                   │
 * │ 🔍 检测数据源类型              │ getDataSourceType()                    │
 * │ ✅ 判断是否为数值类型          │ isDataSourceNumeric()                  │
 * │ 📊 同时获取数值和显示文本      │ getDataSourceWithDisplay()             │
 * │ 🎯 获取特定类型的值            │ getDataSource<T>()                     │
 * │ 📝 获取字符串表示              │ getDataSourceValue()                   │
 * │ 🔓 获取原始值（不转换）        │ getDataSourceRawValue()                │
 * └─────────────────────────────────────────────────────────────────────────┘
 *
 * 快速示例：
 *
 * 1️⃣ 通用显示组件（最简单）：
 *    val text = config.getDataSourceDisplay("datasource")
 *    Text(text = text)
 *
 * 2️⃣ 数值计算 + 显示：
 *    val value = config.getDataSourceFloat("datasource", 0f)
 *    val text = config.getDataSourceDisplay("datasource", decimals = 2)
 *    if (value > 100) { /* 警告 */ }
 *
 * 3️⃣ 根据类型显示不同UI：
 *    if (config.isDataSourceNumeric("datasource")) {
 *        // 显示图表
 *    } else {
 *        // 显示文本
 *    }
 *
 * 详细文档请参考各方法的注释说明。
 */

// ─────────────────────────────
// 3.1 基础泛型方法
// ─────────────────────────────

/**
 * 从 WidgetConfig 中获取数据源注入的原始值（泛型版本）
 *
 * 支持所有类型：String, Int, Float, Long, Boolean, IntArray, FloatArray, LongArray, ByteArray 等
 *
 * 使用示例：
 * ```
 * // 基本类型（需要提供默认值）
 * val temperature: Float = config.getDataSource("datasource", 0f)
 * val speed: Int = config.getDataSource("speedDataSource", 0)
 * val name: String = config.getDataSource("nameDataSource", "Unknown")
 *
 * // 数组类型（返回可空类型）
 * val pressures: FloatArray? = config.getDataSource("pressureDataSource")
 * val temps: IntArray? = config.getDataSource("tempDataSource")
 *
 * // 可空基本类型
 * val optionalValue: String? = config.getDataSource("datasource")
 * ```
 *
 * @param T 返回值类型
 * @param dataSourceKey 数据源参数键（会自动添加 "Value" 后缀查找注入的值）
 * @param defaultValue 默认值（可选，用于基本类型）
 * @return 数据源值，类型由泛型参数决定
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> WidgetConfig.getDataSource(
    dataSourceKey: String,
    defaultValue: T? = null
): T? {
    val rawValue = params["${dataSourceKey}Value"]

    return when (T::class) {
        // 数组类型 - 直接类型转换
        IntArray::class -> rawValue as? T
        FloatArray::class -> rawValue as? T
        LongArray::class -> rawValue as? T
        ByteArray::class -> rawValue as? T

        // 基本类型 - 从原始值转换
        String::class -> (rawValue?.toString() ?: defaultValue) as? T
        Int::class -> ((rawValue as? Number)?.toInt() ?: defaultValue) as? T
        Float::class -> ((rawValue as? Number)?.toFloat() ?: defaultValue) as? T
        Long::class -> ((rawValue as? Number)?.toLong() ?: defaultValue) as? T
        Double::class -> ((rawValue as? Number)?.toDouble() ?: defaultValue) as? T
        Boolean::class -> ((rawValue as? Boolean) ?: defaultValue) as? T

        // 其他类型 - 尝试直接转换
        else -> (rawValue as? T) ?: defaultValue
    }
}

// ─────────────────────────────
// 3.2 向后兼容便捷方法
// ─────────────────────────────

/**
 * 获取数据源的字符串表示（自动转换为字符串）
 *
 * ⚠️ 注意：此方法会自动将任何类型转换为字符串（调用 toString()）
 *
 * 与 getDataSourceRawValue() 的区别：
 * - getDataSourceValue()：返回 String?，自动转换为字符串
 * - getDataSourceRawValue()：返回 Any?，保留原始类型
 *
 * 推荐使用场景：
 * - 需要字符串表示但不关心具体格式 → 使用本方法
 * - 需要格式化的显示文本 → 使用 getDataSourceDisplay()（推荐）
 * - 需要保留原始类型做判断 → 使用 getDataSourceRawValue()
 * - 需要具体类型的值 → 使用 getDataSource<T>()
 *
 * 使用示例：
 * ```
 * // 获取字符串表示（任何类型都会转为字符串）
 * val text = config.getDataSourceValue("datasource") // 可能返回 "123.45" 或 "P档"
 * ```
 *
 * @param dataSourceKey 数据源参数键
 * @return 字符串表示，如果数据源不存在则返回 null
 */
fun WidgetConfig.getDataSourceValue(dataSourceKey: String): String? {
    return getDataSource(dataSourceKey)
}

/**
 * 获取浮点数值（便捷方法）
 */
fun WidgetConfig.getDataSourceFloat(dataSourceKey: String, defaultValue: Float): Float {
    return getDataSource(dataSourceKey, defaultValue) ?: defaultValue
}

/**
 * 获取整数值（便捷方法）
 */
fun WidgetConfig.getDataSourceInt(dataSourceKey: String, defaultValue: Int): Int {
    return getDataSource(dataSourceKey, defaultValue) ?: defaultValue
}

/**
 * 获取长整数值（便捷方法）
 */
fun WidgetConfig.getDataSourceLong(dataSourceKey: String, defaultValue: Long): Long {
    return getDataSource(dataSourceKey, defaultValue) ?: defaultValue
}

/**
 * 获取字节数组（便捷方法）
 */
fun WidgetConfig.getDataSourceBytes(dataSourceKey: String): ByteArray? {
    return getDataSource(dataSourceKey)
}

/**
 * 获取整数数组（便捷方法）
 */
fun WidgetConfig.getDataSourceIntArray(dataSourceKey: String): IntArray? {
    return getDataSource(dataSourceKey)
}

/**
 * 获取浮点数组（便捷方法）
 */
fun WidgetConfig.getDataSourceFloatArray(dataSourceKey: String): FloatArray? {
    return getDataSource(dataSourceKey)
}

/**
 * 获取长整数数组（便捷方法）
 */
fun WidgetConfig.getDataSourceLongArray(dataSourceKey: String): LongArray? {
    return params["${dataSourceKey}Value"] as? LongArray
}

// ─────────────────────────────
// 3.3 图表数据方法
// ─────────────────────────────

/**
 * 从 WidgetConfig 中获取图表历史数据点列表
 *
 * 图表数据由 app 层的 ChartDataManager 管理，通过 DataSourceInjector 注入到 config 中。
 * 数据注入键名规则：{dataSourceKey}ChartData
 *
 * 使用示例：
 * ```
 * @Composable
 * fun LineChartContent(config: WidgetConfig) {
 *     val chartData = config.getChartData("datasource")
 *
 *     Canvas(modifier = Modifier.fillMaxSize()) {
 *         chartData.forEachIndexed { index, point ->
 *             // 绘制数据点
 *             val x = index * spacing
 *             val y = height - (point.value * height / maxValue)
 *             // ...
 *         }
 *     }
 * }
 * ```
 *
 * @param dataSourceKey 数据源参数键（与 WidgetParamType.DATA_SOURCE 参数对应）
 * @return 数据点列表（时间戳 + 数值）
 */
fun WidgetConfig.getChartData(dataSourceKey: String): List<com.neta.isulewtools.api.widget.chart.ChartDataPoint> {
    return (params["${dataSourceKey}ChartData"] as? List<*>)
        ?.filterIsInstance<com.neta.isulewtools.api.widget.chart.ChartDataPoint>()
        ?: emptyList()
}

/**
 * 从 WidgetConfig 中获取图表数据配置
 *
 * 图表配置由 app 层根据小组件参数构造，包含缓冲大小、更新间隔、Y轴范围等信息。
 * 数据注入键名规则：{dataSourceKey}ChartConfig
 *
 * @param dataSourceKey 数据源参数键
 * @return 图表配置对象，如果不存在则返回 null
 */
fun WidgetConfig.getChartConfig(dataSourceKey: String): com.neta.isulewtools.api.widget.chart.ChartDataConfig? {
    return params["${dataSourceKey}ChartConfig"] as? com.neta.isulewtools.api.widget.chart.ChartDataConfig
}

/**
 * 获取最新的图表数据点值（便捷方法）
 *
 * 等价于 `getChartData(key).lastOrNull()?.value`
 *
 * @param dataSourceKey 数据源参数键
 * @return 最新数据点的值，如果没有数据则返回 null
 */
fun WidgetConfig.getLatestChartValue(dataSourceKey: String): Float? {
    return getChartData(dataSourceKey).lastOrNull()?.value
}

/**
 * 获取图表数据的值域范围（最小值和最大值）
 *
 * 用于自动计算 Y 轴范围或归一化数据值。
 *
 * @param dataSourceKey 数据源参数键
 * @return 值域范围 (最小值, 最大值)，如果没有数据则返回 null
 */
fun WidgetConfig.getChartValueRange(dataSourceKey: String): Pair<Float, Float>? {
    val data = getChartData(dataSourceKey)
    if (data.isEmpty()) return null
    val min = data.minOf { it.value }
    val max = data.maxOf { it.value }
    return min to max
}

// ─────────────────────────────
// 3.4 类型检测方法（新增）
// ─────────────────────────────

/**
 * 数据源类型枚举
 *
 * 用于识别数据源注入的值的实际类型，便于组件根据类型做不同处理
 */
enum class DataSourceType {
    /** 字符串类型 */
    STRING,
    /** 整数类型 */
    INT,
    /** 浮点数类型 */
    FLOAT,
    /** 长整数类型 */
    LONG,
    /** 双精度浮点数类型 */
    DOUBLE,
    /** 布尔类型 */
    BOOLEAN,
    /** 整数数组类型 */
    INT_ARRAY,
    /** 浮点数组类型 */
    FLOAT_ARRAY,
    /** 长整数组类型 */
    LONG_ARRAY,
    /** 字节数组类型 */
    BYTE_ARRAY,
    /** 未知类型或无数据 */
    UNKNOWN
}

/**
 * 获取数据源的类型
 *
 * 使用示例：
 * ```
 * when (config.getDataSourceType("datasource")) {
 *     DataSourceType.STRING -> // 显示文本
 *     DataSourceType.FLOAT -> // 显示图表
 *     else -> // 默认处理
 * }
 * ```
 *
 * @param key 数据源参数键
 * @return 数据源类型，如果不存在则返回 UNKNOWN
 */
fun WidgetConfig.getDataSourceType(key: String): DataSourceType {
    val rawValue = params["${key}Value"]
    return when (rawValue) {
        is String -> DataSourceType.STRING
        is Int -> DataSourceType.INT
        is Float -> DataSourceType.FLOAT
        is Long -> DataSourceType.LONG
        is Double -> DataSourceType.DOUBLE
        is Boolean -> DataSourceType.BOOLEAN
        is IntArray -> DataSourceType.INT_ARRAY
        is FloatArray -> DataSourceType.FLOAT_ARRAY
        is LongArray -> DataSourceType.LONG_ARRAY
        is ByteArray -> DataSourceType.BYTE_ARRAY
        null -> DataSourceType.UNKNOWN
        else -> DataSourceType.UNKNOWN
    }
}

/**
 * 检查数据源是否为数值类型（Int、Float、Long、Double）
 *
 * 使用示例：
 * ```
 * if (config.isDataSourceNumeric("datasource")) {
 *     // 可以进行数值计算和显示图表
 *     val value = config.getDataSourceFloat("datasource", 0f)
 * } else {
 *     // 只能显示文本
 *     val text = config.getDataSourceDisplay("datasource")
 * }
 * ```
 *
 * @param key 数据源参数键
 * @return 如果是数值类型返回 true，否则返回 false
 */
fun WidgetConfig.isDataSourceNumeric(key: String): Boolean {
    val type = getDataSourceType(key)
    return type in listOf(
        DataSourceType.INT,
        DataSourceType.FLOAT,
        DataSourceType.LONG,
        DataSourceType.DOUBLE
    )
}

/**
 * 检查数据源是否为字符串类型
 *
 * @param key 数据源参数键
 * @return 如果是字符串类型返回 true，否则返回 false
 */
fun WidgetConfig.isDataSourceString(key: String): Boolean {
    return getDataSourceType(key) == DataSourceType.STRING
}

/**
 * 检查数据源是否为数组类型
 *
 * @param key 数据源参数键
 * @return 如果是数组类型返回 true，否则返回 false
 */
fun WidgetConfig.isDataSourceArray(key: String): Boolean {
    val type = getDataSourceType(key)
    return type in listOf(
        DataSourceType.INT_ARRAY,
        DataSourceType.FLOAT_ARRAY,
        DataSourceType.LONG_ARRAY,
        DataSourceType.BYTE_ARRAY
    )
}

// ─────────────────────────────
// 3.5 智能显示方法（新增）⭐ 推荐
// ─────────────────────────────

/**
 * 获取数据源的显示文本（自动格式化所有类型）⭐ 核心方法
 *
 * 这是一个通用方法，会自动处理所有数据源类型的格式化显示：
 * - String：直接返回字符串
 * - Float/Double：格式化为指定小数位数
 * - Int/Long：转换为字符串
 * - Boolean：显示为"是"/"否"（可自定义）
 * - Array：显示为逗号分隔的列表
 *
 * 使用示例：
 * ```
 * // 最简单的用法 - 自动适配所有类型
 * val text = config.getDataSourceDisplay("datasource")
 *
 * // 指定小数位数（用于数值类型）
 * val text = config.getDataSourceDisplay("datasource", decimals = 1)
 *
 * // 自定义默认值
 * val text = config.getDataSourceDisplay("datasource", defaultValue = "无数据")
 *
 * // 自定义布尔值显示
 * val text = config.getDataSourceDisplay(
 *     "enableStatus",
 *     booleanTrue = "✓ 已开启",
 *     booleanFalse = "✗ 已关闭"
 * )
 *
 * // 自定义格式化（高级用法）
 * val text = config.getDataSourceDisplay("datasource") { value ->
 *     when (value) {
 *         is Float -> if (value > 100) "高温" else "正常"
 *         is String -> value.uppercase()
 *         else -> value.toString()
 *     }
 * }
 * ```
 *
 * @param key 数据源参数键
 * @param decimals 数值类型的小数位数，默认 2 位
 * @param defaultValue 无数据时的默认显示文本，默认 "—"
 * @param booleanTrue 布尔值为 true 时的显示文本，默认 "是"
 * @param booleanFalse 布尔值为 false 时的显示文本，默认 "否"
 * @param format 自定义格式化函数（可选），接收原始值返回格式化字符串
 * @return 格式化后的显示文本
 */
fun WidgetConfig.getDataSourceDisplay(
    key: String,
    decimals: Int = 2,
    defaultValue: String = "—",
    booleanTrue: String = "是",
    booleanFalse: String = "否",
    format: ((Any) -> String)? = null
): String {
    val rawValue = params["${key}Value"]

    // 无数据时返回默认值
    if (rawValue == null) {
        return defaultValue
    }

    // 如果提供了自定义格式化函数，优先使用
    if (format != null) {
        return try {
            format(rawValue)
        } catch (e: Exception) {
            rawValue.toString()
        }
    }

    // 自动格式化
    return when (rawValue) {
        is String -> rawValue
        is Float -> "%.${decimals}f".format(rawValue)
        is Double -> "%.${decimals}f".format(rawValue)
        is Int -> rawValue.toString()
        is Long -> rawValue.toString()
        is Boolean -> if (rawValue) booleanTrue else booleanFalse
        is IntArray -> rawValue.joinToString(", ")
        is FloatArray -> rawValue.joinToString(", ") { "%.${decimals}f".format(it) }
        is LongArray -> rawValue.joinToString(", ")
        is ByteArray -> rawValue.joinToString(", ") { "0x%02X".format(it) }
        else -> rawValue.toString()
    }
}

/**
 * 获取数据源显示文本的简化版本（使用默认参数）
 *
 * 使用示例：
 * ```
 * Text(text = config.getDataSourceText("datasource"))
 * ```
 *
 * @param key 数据源参数键
 * @return 格式化后的显示文本
 */
fun WidgetConfig.getDataSourceText(key: String): String {
    return getDataSourceDisplay(key)
}

/**
 * 获取格式化的数值字符串（仅用于数值类型）
 *
 * 如果数据源不是数值类型，返回 defaultValue。
 * 这个方法适合明确知道数据源是数值类型的场景。
 *
 * 使用示例：
 * ```
 * val valueText = config.getDataSourceNumberText(
 *     "datasource",
 *     decimals = 1,
 *     defaultValue = "N/A"
 * )
 * ```
 *
 * @param key 数据源参数键
 * @param decimals 小数位数，默认 2 位
 * @param defaultValue 非数值类型时的默认值，默认 "—"
 * @return 格式化后的数值字符串，如果不是数值类型则返回 defaultValue
 */
fun WidgetConfig.getDataSourceNumberText(
    key: String,
    decimals: Int = 2,
    defaultValue: String = "—"
): String {
    val rawValue = params["${key}Value"]
    return when (rawValue) {
        is Float -> "%.${decimals}f".format(rawValue)
        is Double -> "%.${decimals}f".format(rawValue)
        is Int -> rawValue.toString()
        is Long -> rawValue.toString()
        is Number -> "%.${decimals}f".format(rawValue.toDouble())
        else -> defaultValue
    }
}

// ─────────────────────────────
// 3.6 增强方法：原始值和计算支持（新增）
// ─────────────────────────────

/**
 * 获取数据源的原始值（Any? 类型，保留原始类型）
 *
 * ⚠️ 注意：此方法返回原始值，不做任何类型转换
 *
 * 与 getDataSourceValue() 的区别：
 * - getDataSourceRawValue()：返回 Any?，保留原始类型（Float、Int、String等）
 * - getDataSourceValue()：返回 String?，自动转换为字符串
 *
 * 推荐使用场景：
 * - 需要保留原始类型进行类型判断 → 使用本方法
 * - 需要根据不同类型做完全不同的处理 → 使用本方法
 * - 只需要字符串表示 → 使用 getDataSourceValue()
 * - 需要格式化显示 → 使用 getDataSourceDisplay()（推荐）
 * - 需要检测类型 → 使用 getDataSourceType() 或 isDataSourceNumeric()（更清晰）
 *
 * 使用示例：
 * ```
 * val rawValue = config.getDataSourceRawValue("datasource")
 * val displayText = when (rawValue) {
 *     is String -> rawValue                     // 保留原始字符串
 *     is Float -> "%.2f".format(rawValue)       // 格式化浮点数
 *     is Int -> rawValue.toString()             // 转换整数
 *     is Boolean -> if (rawValue) "开" else "关"  // 自定义布尔显示
 *     else -> "—"
 * }
 *
 * // 更推荐的方式：使用类型检测方法
 * if (config.isDataSourceNumeric("datasource")) {
 *     val value = config.getDataSourceFloat("datasource", 0f)
 *     // 处理数值...
 * } else if (config.isDataSourceString("datasource")) {
 *     val text = config.getDataSourceValue("datasource")
 *     // 处理字符串...
 * }
 * ```
 *
 * @param key 数据源参数键
 * @return 原始值，可能是任意类型（String、Int、Float、Boolean、Array等）
 */
fun WidgetConfig.getDataSourceRawValue(key: String): Any? {
    return params["${key}Value"]
}

/**
 * 获取数据源的数值用于计算（如果不是数值类型返回 null）
 *
 * 这个方法会尝试将任何类型转换为 Float，失败则返回 null。
 * 适合需要进行数值计算的场景。
 *
 * 使用示例：
 * ```
 * val numericValue = config.getDataSourceNumericValue("datasource")
 * if (numericValue != null) {
 *     val doubled = numericValue * 2
 *     val isHigh = numericValue > 100
 *     // 进行数值计算...
 * } else {
 *     // 不是数值类型，只能显示文本
 * }
 * ```
 *
 * @param key 数据源参数键
 * @return 数值（Float），如果无法转换为数值则返回 null
 */
fun WidgetConfig.getDataSourceNumericValue(key: String): Float? {
    val rawValue = params["${key}Value"]
    return when (rawValue) {
        is Float -> rawValue
        is Double -> rawValue.toFloat()
        is Int -> rawValue.toFloat()
        is Long -> rawValue.toFloat()
        is String -> rawValue.toFloatOrNull()
        else -> null
    }
}

/**
 * 同时获取数值和显示文本
 *
 * 这是一个便捷方法，一次性返回用于计算的数值和用于显示的文本。
 * 如果数据源不是数值类型，第一个返回值为 null。
 *
 * 使用示例：
 * ```
 * val (numericValue, displayText) = config.getDataSourceWithDisplay(
 *     "datasource",
 *     decimals = 2
 * )
 *
 * Column {
 *     // 显示格式化文本
 *     Text(text = displayText, fontSize = 48.sp)
 *
 *     // 如果有数值，显示额外的计算信息
 *     numericValue?.let { value ->
 *         Text("双倍: ${"%.2f".format(value * 2)}")
 *         Text("是否超标: ${if (value > 100) "是" else "否"}")
 *     }
 * }
 * ```
 *
 * @param key 数据源参数键
 * @param decimals 数值类型的小数位数，默认 2 位
 * @param defaultValue 无数据时的默认显示文本，默认 "—"
 * @return Pair(数值用于计算, 文本用于显示)，如果不是数值类型则第一个值为 null
 */
fun WidgetConfig.getDataSourceWithDisplay(
    key: String,
    decimals: Int = 2,
    defaultValue: String = "—"
): Pair<Float?, String> {
    val numericValue = getDataSourceNumericValue(key)
    val displayText = getDataSourceDisplay(key, decimals, defaultValue)
    return numericValue to displayText
}

// ===============================
// 4. UI 辅助方法
// ===============================

/**
 * 应用小组件的变换效果（缩放和透明度）
 *
 * 使用示例：
 * ```
 * Box(
 *     modifier = Modifier
 *         .size(100.dp)
 *         .applyWidgetTransform(config)
 * ) {
 *     // 小组件内容
 * }
 * ```
 *
 * @param config Widget 配置
 * @return 应用了缩放和透明度的 Modifier
 */
fun Modifier.applyWidgetTransform(config: WidgetConfig): Modifier {
    val scale = config.getScale()
    val alpha = config.getAlpha()
    return this.graphicsLayer(
        scaleX = scale,
        scaleY = scale,
        alpha = alpha
    )
}

/**
 * 应用小组件的透明度效果
 *
 * 使用示例：
 * ```
 * Canvas(
 *     modifier = Modifier
 *         .size(100.dp)
 *         .applyWidgetAlpha(config)
 * ) {
 *     // Canvas 绘图
 * }
 * ```
 *
 * @param config Widget 配置
 * @return 应用了透明度的 Modifier
 */
fun Modifier.applyWidgetAlpha(config: WidgetConfig): Modifier {
    return this.graphicsLayer(alpha = config.getAlpha())
}

/**
 * 在 Composable 中获取主应用的字体（FontFamily）
 *
 * 用于 Compose Text 组件的字体设置。
 * 函数会自动获取 Context 并缓存结果。
 *
 * 注意：此函数使用资源反射（getIdentifier）是动态小组件的必要实现方式，
 * 因为 JAR 插件在编译时无法访问主应用的 R 类。
 *
 * 使用示例：
 * ```
 * @Composable
 * fun MyWidget(config: WidgetConfig) {
 *     val fontFamily = rememberWidgetFontFamily(
 *         font = WidgetFont.MONTSERRAT_BOLD,
 *         weight = FontWeight.Bold
 *     )
 *
 *     Text(
 *         text = "Hello World",
 *         fontFamily = fontFamily,
 *         fontSize = 24.sp
 *     )
 * }
 * ```
 *
 * @param font 字体枚举（提供 IDE 自动补全）
 * @param weight 字体权重，默认为 FontWeight.Normal
 * @return FontFamily 对象（字体不存在时返回 FontFamily.Default）
 */
@SuppressLint("LocalContextResourcesRead")
@Suppress("DiscouragedApi")  // 动态小组件必须使用资源反射
@Composable
fun rememberWidgetFontFamily(
    font: WidgetFont,
    weight: FontWeight = FontWeight.Normal
): FontFamily {
    val context = LocalContext.current
    return remember(font, weight) {
        val fontResId = context.resources.getIdentifier(
            font.resourceName,
            "font",
            context.packageName
        )
        if (fontResId != 0) {
            try {
                // 直接使用资源ID创建Font
                FontFamily(Font(fontResId, weight))
            } catch (_: Exception) {
                FontFamily.Default
            }
        } else {
            FontFamily.Default
        }
    }
}
