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
 * Widget Composable 辅助工具
 *
 * 提供统一的扩展函数来处理所有小组件必需的 scale 和 alpha 参数
 */

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

// 向后兼容的便捷函数（保留原有 API，避免破坏现有代码）

/**
 * 获取字符串值（便捷方法）
 * 推荐使用 getDataSource<String>(key, default) 替代
 */
fun WidgetConfig.getDataSourceValue(dataSourceKey: String): String? {
    return getDataSource(dataSourceKey)
}

/**
 * 获取浮点数值（便捷方法）
 * 推荐使用 getDataSource<Float>(key, default) 替代
 */
fun WidgetConfig.getDataSourceFloat(dataSourceKey: String, defaultValue: Float): Float {
    return getDataSource(dataSourceKey, defaultValue) ?: defaultValue
}

/**
 * 获取整数值（便捷方法）
 * 推荐使用 getDataSource<Int>(key, default) 替代
 */
fun WidgetConfig.getDataSourceInt(dataSourceKey: String, defaultValue: Int): Int {
    return getDataSource(dataSourceKey, defaultValue) ?: defaultValue
}

/**
 * 获取长整数值（便捷方法）
 * 推荐使用 getDataSource<Long>(key, default) 替代
 */
fun WidgetConfig.getDataSourceLong(dataSourceKey: String, defaultValue: Long): Long {
    return getDataSource(dataSourceKey, defaultValue) ?: defaultValue
}

/**
 * 获取字节数组（便捷方法）
 * 推荐使用 getDataSource<ByteArray>(key) 替代
 */
fun WidgetConfig.getDataSourceBytes(dataSourceKey: String): ByteArray? {
    return getDataSource(dataSourceKey)
}

/**
 * 获取整数数组（便捷方法）
 * 推荐使用 getDataSource<IntArray>(key) 替代
 */
fun WidgetConfig.getDataSourceIntArray(dataSourceKey: String): IntArray? {
    return getDataSource(dataSourceKey)
}

/**
 * 获取浮点数组（便捷方法）
 * 推荐使用 getDataSource<FloatArray>(key) 替代
 */
fun WidgetConfig.getDataSourceFloatArray(dataSourceKey: String): FloatArray? {
    return getDataSource(dataSourceKey)
}

/**
 * 从 WidgetConfig 中获取数据源注入的长整数数组值（用于 INT64_VEC 类型）
 *
 * 使用示例：
 * ```
 * val timestampArray = config.getDataSourceLongArray("timestampDataSource")
 * val value = timestampArray?.getOrNull(0) ?: 0L
 * ```
 *
 * @param dataSourceKey 数据源参数键（会自动添加 "Value" 后缀查找注入的值）
 * @return 长整数数组，如果不存在或类型不匹配则返回 null
 */
fun WidgetConfig.getDataSourceLongArray(dataSourceKey: String): LongArray? {
    return params["${dataSourceKey}Value"] as? LongArray
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
