package com.neta.isulewtools.api.widget

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Widget Composable 辅助工具
 *
 * 提供统一的扩展函数来处理所有小组件必需的 scale 和 alpha 参数
 */

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
 * 从 WidgetConfig 中安全获取颜色值
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
    return colorStr.toColorOrDefault(defaultColor)
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
        Color(colorLong.toULong())
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
