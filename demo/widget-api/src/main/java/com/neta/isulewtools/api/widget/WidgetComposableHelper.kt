package com.neta.isulewtools.api.widget

import androidx.compose.ui.Modifier
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
