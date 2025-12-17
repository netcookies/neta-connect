package com.neta.widgets.vehiclelights

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getDataSourceInt
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.toHexString

/**
 * VehicleLightsWidget 的 WidgetSpec 注册示例
 */
object VehicleLightsWidgetSpec : WidgetSpec(
    type = "vehicle_lights_widget",
    displayName = "车辆灯光",
    recommendedGrid = Pair(2, 1),
    paramSchema = WidgetParamDesc.buildParams {
        // 整体尺寸
        +WidgetParamDesc(
            key = P.WIDTH.key,
            label = "宽度",
            type = WidgetParamType.FLOAT,
            defaultValue = P.WIDTH.default,
            description = "小组件的宽度 (单位: dp)"
        )
        +WidgetParamDesc(
            key = P.HEIGHT.key,
            label = "高度",
            type = WidgetParamType.FLOAT,
            defaultValue = P.HEIGHT.default,
            description = "小组件的高度 (单位: dp)"
        )

        // 背景配置
        +WidgetParamDesc(
            key = P.BACKGROUND_COLOR.key,
            label = "背景色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BACKGROUND_COLOR.default.toHexString(),
            description = "背景框的颜色"
        )
        +WidgetParamDesc(
            key = P.CORNER_RADIUS.key,
            label = "圆角半径",
            type = WidgetParamType.FLOAT,
            defaultValue = P.CORNER_RADIUS.default,
            description = "背景框的圆角半径 (单位: dp)"
        )

        // 远光灯配置
        +WidgetParamDesc(
            key = P.SHOW_HIGH_BEAM.key,
            label = "显示远光灯",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_HIGH_BEAM.default,
            description = "是否显示远光灯"
        )
        +WidgetParamDesc(
            key = P.HIGH_BEAM_COLOR.key,
            label = "远光灯颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.HIGH_BEAM_COLOR.default.toHexString(),
            description = "远光灯的颜色"
        )
        +WidgetParamDesc(
            key = P.HIGH_BEAM_DATASOURCE.key,
            label = "远光灯数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.HIGH_BEAM_DATASOURCE.default,
            options = emptyList(),
            required = true,
            description = "远光灯状态数据源"
        )

        // 近光灯配置
        +WidgetParamDesc(
            key = P.SHOW_LOW_BEAM.key,
            label = "显示近光灯",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_LOW_BEAM.default,
            description = "是否显示近光灯"
        )
        +WidgetParamDesc(
            key = P.LOW_BEAM_COLOR.key,
            label = "近光灯颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.LOW_BEAM_COLOR.default.toHexString(),
            description = "近光灯的颜色"
        )
        +WidgetParamDesc(
            key = P.LOW_BEAM_DATASOURCE.key,
            label = "近光灯数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.LOW_BEAM_DATASOURCE.default,
            options = emptyList(),
            required = true,
            description = "近光灯状态数据源"
        )

        // 雾灯配置
        +WidgetParamDesc(
            key = P.SHOW_FOG_LIGHT.key,
            label = "显示雾灯",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_FOG_LIGHT.default,
            description = "是否显示雾灯"
        )
        +WidgetParamDesc(
            key = P.FOG_LIGHT_COLOR.key,
            label = "雾灯颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.FOG_LIGHT_COLOR.default.toHexString(),
            description = "雾灯的颜色"
        )
        +WidgetParamDesc(
            key = P.FOG_LIGHT_DATASOURCE.key,
            label = "雾灯数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.FOG_LIGHT_DATASOURCE.default,
            options = emptyList(),
            required = true,
            description = "雾灯状态数据源"
        )

        // 示宽灯配置
        +WidgetParamDesc(
            key = P.SHOW_POSITION_LIGHT.key,
            label = "显示示宽灯",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_POSITION_LIGHT.default,
            description = "是否显示示宽灯"
        )
        +WidgetParamDesc(
            key = P.POSITION_LIGHT_COLOR.key,
            label = "示宽灯颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.POSITION_LIGHT_COLOR.default.toHexString(),
            description = "示宽灯的颜色"
        )
        +WidgetParamDesc(
            key = P.POSITION_LIGHT_DATASOURCE.key,
            label = "示宽灯数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.POSITION_LIGHT_DATASOURCE.default,
            options = emptyList(),
            required = true,
            description = "示宽灯状态数据源"
        )
    },
    contentComposable = {
        VehicleLightsWidgetContent(it)
    },
    color = Color(0xFFFFB74D),
    icon = Icons.Default.Lightbulb
) {
    /**
     * 参数定义对象（使用 ParamDef）
     */
    object P {
        // 尺寸
        val WIDTH = ParamDef("width", 280f)
        val HEIGHT = ParamDef("height", 120f)

        // 背景
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color(0xFF2C2C2E))
        val CORNER_RADIUS = ParamDef("cornerRadius", 16f)

        // 远光灯
        val SHOW_HIGH_BEAM = ParamDef("showHighBeam", true)
        val HIGH_BEAM_COLOR = ParamDef("highBeamColor", Color(0xFF4FC3F7))
        val HIGH_BEAM_DATASOURCE = ParamDef("highBeamDatasource", "557843761")

        // 近光灯
        val SHOW_LOW_BEAM = ParamDef("showLowBeam", true)
        val LOW_BEAM_COLOR = ParamDef("lowBeamColor", Color(0xFF81D4FA))
        val LOW_BEAM_DATASOURCE = ParamDef("lowBeamDatasource", "557843760")

        // 雾灯
        val SHOW_FOG_LIGHT = ParamDef("showFogLight", true)
        val FOG_LIGHT_COLOR = ParamDef("fogLightColor", Color(0xFFFFC107))
        val FOG_LIGHT_DATASOURCE = ParamDef("fogLightDatasource", "557843759")

        // 示宽灯
        val SHOW_POSITION_LIGHT = ParamDef("showPositionLight", true)
        val POSITION_LIGHT_COLOR = ParamDef("positionLightColor", Color(0xFFFF9800))
        val POSITION_LIGHT_DATASOURCE = ParamDef("positionLightDatasource", "557843762")
    }
}

@Composable
fun VehicleLightsWidgetContent(config: WidgetConfig) {
    // 读取尺寸参数
    val width = config.getParam(VehicleLightsWidgetSpec.P.WIDTH)
    val height = config.getParam(VehicleLightsWidgetSpec.P.HEIGHT)

    // 读取背景参数
    val backgroundColor = config.getParam(VehicleLightsWidgetSpec.P.BACKGROUND_COLOR)
    val cornerRadius = config.getParam(VehicleLightsWidgetSpec.P.CORNER_RADIUS)

    // 读取缩放和透明度
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 远光灯
    val showHighBeam = config.getParam(VehicleLightsWidgetSpec.P.SHOW_HIGH_BEAM)
    val highBeamColor = config.getParam(VehicleLightsWidgetSpec.P.HIGH_BEAM_COLOR)
    val highBeamState =
        config.getDataSourceInt(VehicleLightsWidgetSpec.P.HIGH_BEAM_DATASOURCE.key, 0) == 1

    // 近光灯
    val showLowBeam = config.getParam(VehicleLightsWidgetSpec.P.SHOW_LOW_BEAM)
    val lowBeamColor = config.getParam(VehicleLightsWidgetSpec.P.LOW_BEAM_COLOR)
    val lowBeamState =
        config.getDataSourceInt(VehicleLightsWidgetSpec.P.LOW_BEAM_DATASOURCE.key, 0) == 1

    // 雾灯
    val showFogLight = config.getParam(VehicleLightsWidgetSpec.P.SHOW_FOG_LIGHT)
    val fogLightColor = config.getParam(VehicleLightsWidgetSpec.P.FOG_LIGHT_COLOR)
    val fogLightState =
        config.getDataSourceInt(VehicleLightsWidgetSpec.P.FOG_LIGHT_DATASOURCE.key, 0) == 1

    // 示宽灯
    val showPositionLight = config.getParam(VehicleLightsWidgetSpec.P.SHOW_POSITION_LIGHT)
    val positionLightColor = config.getParam(VehicleLightsWidgetSpec.P.POSITION_LIGHT_COLOR)
    val positionLightState =
        config.getDataSourceInt(VehicleLightsWidgetSpec.P.POSITION_LIGHT_DATASOURCE.key, 0) == 1

    VehicleLightsIndicator(
        width = width,
        height = height,
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        scale = scale,
        alpha = alpha,
        showHighBeam = showHighBeam,
        highBeamColor = highBeamColor,
        highBeamState = highBeamState,
        showLowBeam = showLowBeam,
        lowBeamColor = lowBeamColor,
        lowBeamState = lowBeamState,
        showFogLight = showFogLight,
        fogLightColor = fogLightColor,
        fogLightState = fogLightState,
        showPositionLight = showPositionLight,
        positionLightColor = positionLightColor,
        positionLightState = positionLightState
    )
}

@Composable
fun VehicleLightsIndicator(
    width: Float,
    height: Float,
    backgroundColor: Color,
    cornerRadius: Float,
    scale: Float = 1f,
    alpha: Float = 1f,
    showHighBeam: Boolean = true,
    highBeamColor: Color = Color(0xFF4FC3F7),
    highBeamState: Boolean = false,
    showLowBeam: Boolean = true,
    lowBeamColor: Color = Color(0xFF81D4FA),
    lowBeamState: Boolean = false,
    showFogLight: Boolean = true,
    fogLightColor: Color = Color(0xFFFFC107),
    fogLightState: Boolean = false,
    showPositionLight: Boolean = true,
    positionLightColor: Color = Color(0xFFFF9800),
    positionLightState: Boolean = false
) {
    Box(
        modifier = Modifier
            .width((width * scale).dp)
            .height((height * scale).dp)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = alpha)
        ) {
            // 绘制背景圆角矩形
            drawRoundRect(
                color = backgroundColor,
                size = size,
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                    cornerRadius.dp.toPx() * scale,
                    cornerRadius.dp.toPx() * scale
                )
            )

            // 计算需要显示的灯光数量和布局
            val lights = mutableListOf<LightInfo>()
            if (showHighBeam) lights.add(LightInfo("远光", highBeamColor, highBeamState))
            if (showLowBeam) lights.add(LightInfo("近光", lowBeamColor, lowBeamState))
            if (showFogLight) lights.add(LightInfo("雾灯", fogLightColor, fogLightState))
            if (showPositionLight) lights.add(
                LightInfo(
                    "示宽",
                    positionLightColor,
                    positionLightState
                )
            )

            if (lights.isNotEmpty()) {
                // 计算布局参数（排成一排）
                val padding = 20f * scale
                val spacing = 16f * scale
                val availableWidth = size.width - padding * 2
                val availableHeight = size.height - padding * 2

                // 计算每个灯的尺寸（所有灯排成一排，高度一致）
                val lightSize = minOf(
                    availableHeight,
                    (availableWidth - spacing * (lights.size - 1)) / lights.size
                )

                // 计算起始X位置，使灯光居中
                val totalWidth = lightSize * lights.size + spacing * (lights.size - 1)
                val startX = (size.width - totalWidth) / 2

                // 绘制灯光（排成一排）
                lights.forEachIndexed { index, light ->
                    val x = startX + index * (lightSize + spacing) + lightSize / 2
                    val y = size.height / 2

                    when (light.name) {
                        "远光" -> drawHighBeamLight(
                            center = Offset(x, y),
                            size = lightSize,
                            color = light.color,
                            isOn = light.isOn
                        )

                        "近光" -> drawLowBeamLight(
                            center = Offset(x, y),
                            size = lightSize,
                            color = light.color,
                            isOn = light.isOn
                        )

                        "雾灯" -> drawFogLight(
                            center = Offset(x, y),
                            size = lightSize,
                            color = light.color,
                            isOn = light.isOn
                        )

                        "示宽" -> drawPositionLight(
                            center = Offset(x, y),
                            size = lightSize,
                            color = light.color,
                            isOn = light.isOn
                        )
                    }
                }
            }
        }
    }
}

/**
 * 灯光信息数据类
 */
private data class LightInfo(
    val name: String,
    val color: Color,
    val isOn: Boolean
)

/**
 * 绘制示宽灯图标
 * 一比一还原 SVG: indicators-indicator-svgrepo-com (1).svg
 */
private fun DrawScope.drawPositionLight(
    center: Offset,
    size: Float,
    color: Color,
    isOn: Boolean
) {
    val lightColor = if (isOn) color else color.copy(alpha = 0.3f)

    // SVG viewBox: 511.573 x 511.573
    val svgScale = size / 400f * 0.648f
    val strokeWidth = 8.533f * svgScale

    // 中间两条竖线
    // 第一条线 x=228.76, y从156.653到345.52
    val line1X = center.x - 25.6f * svgScale
    val lineY1 = center.y - 94.4f * svgScale
    val lineY2 = center.y + 94.4f * svgScale

    drawLine(
        color = lightColor,
        start = Offset(line1X, lineY1),
        end = Offset(line1X, lineY2),
        strokeWidth = strokeWidth,
        cap = androidx.compose.ui.graphics.StrokeCap.Round
    )

    // 第二条线 x=279.96
    val line2X = center.x + 25.6f * svgScale
    drawLine(
        color = lightColor,
        start = Offset(line2X, lineY1),
        end = Offset(line2X, lineY2),
        strokeWidth = strokeWidth,
        cap = androidx.compose.ui.graphics.StrokeCap.Round
    )

    // 左箭头路径（根据SVG path）
    val leftArrowPath = Path().apply {
        val baseX = center.x - 150f * svgScale
        val arrowY = 51.2f * svgScale

        // 箭头尖端
        moveTo(baseX - 85.333f * svgScale, center.y)
        // 上边
        lineTo(baseX, center.y - arrowY)
        lineTo(baseX, center.y - arrowY + 13.653f * svgScale)
        lineTo(baseX + 85.333f * svgScale, center.y - arrowY + 13.653f * svgScale)
        lineTo(baseX + 85.333f * svgScale, center.y + arrowY - 13.653f * svgScale)
        lineTo(baseX, center.y + arrowY - 13.653f * svgScale)
        lineTo(baseX, center.y + arrowY)
        close()
    }

    // 右箭头路径（镜像）
    val rightArrowPath = Path().apply {
        val baseX = center.x + 150f * svgScale
        val arrowY = 51.2f * svgScale

        moveTo(baseX + 85.333f * svgScale, center.y)
        lineTo(baseX, center.y - arrowY)
        lineTo(baseX, center.y - arrowY + 13.653f * svgScale)
        lineTo(baseX - 85.333f * svgScale, center.y - arrowY + 13.653f * svgScale)
        lineTo(baseX - 85.333f * svgScale, center.y + arrowY - 13.653f * svgScale)
        lineTo(baseX, center.y + arrowY - 13.653f * svgScale)
        lineTo(baseX, center.y + arrowY)
        close()
    }

    drawPath(path = leftArrowPath, color = lightColor, style = Fill)
    drawPath(path = rightArrowPath, color = lightColor, style = Fill)
}

/**
 * 绘制远光灯图标
 * 一比一还原 SVG: car-lights-car-svgrepo-com (1).svg
 */
private fun DrawScope.drawHighBeamLight(
    center: Offset,
    size: Float,
    color: Color,
    isOn: Boolean
) {
    val lightColor = if (isOn) color else color.copy(alpha = 0.3f)

    // SVG viewBox: 504.446 x 504.446
    val svgScale = size / 400f
    val strokeWidth = 8.393f * svgScale
    val bulbRadius = 88f * svgScale

    // 绘制左半圆灯泡
    val bulbPath = Path().apply {
        moveTo(center.x, center.y + bulbRadius)

        arcTo(
            rect = androidx.compose.ui.geometry.Rect(
                left = center.x - bulbRadius,
                top = center.y - bulbRadius,
                right = center.x + bulbRadius,
                bottom = center.y + bulbRadius
            ),
            startAngleDegrees = 90f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )

        lineTo(center.x, center.y + bulbRadius)
    }

    drawPath(path = bulbPath, color = lightColor, style = Stroke(width = strokeWidth))

    // 4条水平线（向右发射）
    val lineStartX = center.x + bulbRadius * 0.5f
    val lineLength = 100f * svgScale
    val linePositions = floatArrayOf(
        center.y - 84.9f * svgScale,   // Line 1
        center.y - 28.3f * svgScale,   // Line 2
        center.y + 28.3f * svgScale,   // Line 3
        center.y + 84.9f * svgScale    // Line 4
    )

    linePositions.forEach { y ->
        drawLine(
            color = lightColor,
            start = Offset(lineStartX, y),
            end = Offset(lineStartX + lineLength, y),
            strokeWidth = strokeWidth,
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )
    }
}

/**
 * 绘制近光灯图标
 * 一比一还原 SVG: car-lights-car-svgrepo-com (2).svg
 */
private fun DrawScope.drawLowBeamLight(
    center: Offset,
    size: Float,
    color: Color,
    isOn: Boolean
) {
    val lightColor = if (isOn) color else color.copy(alpha = 0.3f)

    // SVG viewBox: 512.168 x 512.168
    val svgScale = size / 400f
    val strokeWidth = 8.393f * svgScale
    val bulbRadius = 89.6f * svgScale

    // 绘制左半圆灯泡
    val bulbPath = Path().apply {
        moveTo(center.x, center.y + bulbRadius)

        arcTo(
            rect = androidx.compose.ui.geometry.Rect(
                left = center.x - bulbRadius,
                top = center.y - bulbRadius,
                right = center.x + bulbRadius,
                bottom = center.y + bulbRadius
            ),
            startAngleDegrees = 90f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )

        lineTo(center.x, center.y + bulbRadius)
    }

    drawPath(path = bulbPath, color = lightColor, style = Stroke(width = strokeWidth))

    // 3条向右下发散的直线（根据SVG中的3个path）
    val lineStartX = center.x + bulbRadius * 0.5f
    val lineLength = 100f * svgScale

    // 第一条线：上方
    drawLine(
        color = lightColor,
        start = Offset(lineStartX, center.y - 76.8f * svgScale),
        end = Offset(lineStartX + lineLength, center.y - 46.8f * svgScale),
        strokeWidth = strokeWidth * 0.9f,
        cap = androidx.compose.ui.graphics.StrokeCap.Round
    )

    // 第二条线：中间
    drawLine(
        color = lightColor,
        start = Offset(lineStartX, center.y),
        end = Offset(lineStartX + lineLength, center.y + 30f * svgScale),
        strokeWidth = strokeWidth * 0.9f,
        cap = androidx.compose.ui.graphics.StrokeCap.Round
    )

    // 第三条线：下方
    drawLine(
        color = lightColor,
        start = Offset(lineStartX, center.y + 76.8f * svgScale),
        end = Offset(lineStartX + lineLength, center.y + 46.8f * svgScale + 60f * svgScale),
        strokeWidth = strokeWidth * 0.9f,
        cap = androidx.compose.ui.graphics.StrokeCap.Round
    )
}

/**
 * 绘制雾灯图标
 * 一比一还原 SVG: fog-light-fog-svgrepo-com (1).svg
 */
private fun DrawScope.drawFogLight(
    center: Offset,
    size: Float,
    color: Color,
    isOn: Boolean
) {
    val lightColor = if (isOn) color else color.copy(alpha = 0.3f)

    // SVG viewBox: 504.446 x 504.446
    val svgScale = size / 400f
    val strokeWidth = 8.393f * svgScale
    val bulbRadius = 88f * svgScale

    // 绘制左半圆灯泡
    val bulbPath = Path().apply {
        moveTo(center.x, center.y + bulbRadius)

        arcTo(
            rect = androidx.compose.ui.geometry.Rect(
                left = center.x - bulbRadius,
                top = center.y - bulbRadius,
                right = center.x + bulbRadius,
                bottom = center.y + bulbRadius
            ),
            startAngleDegrees = 90f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )

        lineTo(center.x, center.y + bulbRadius)
    }

    drawPath(path = bulbPath, color = lightColor, style = Stroke(width = strokeWidth))

    // 绘制右侧波浪线（根据SVG的复杂path）
    val waveStartX = center.x + bulbRadius * 0.6f
    val waveLength = 100f * svgScale
    val waveAmplitude = 12f * svgScale

    // 3层波浪
    for (i in 0 until 3) {
        val yBase = center.y + (i - 1) * 35f * svgScale

        val wavePath = Path().apply {
            moveTo(waveStartX, yBase)

            // 创建平滑波浪
            val steps = 4
            for (j in 0 until steps) {
                val progress = j.toFloat() / steps
                val nextProgress = (j + 0.5f) / steps

                val cpx = waveStartX + waveLength * nextProgress
                val cpy =
                    yBase + waveAmplitude * kotlin.math.sin((nextProgress * 4 * Math.PI)).toFloat()

                val endx = waveStartX + waveLength * (progress + 1f / steps)

                quadraticTo(cpx, cpy, endx, yBase)
            }
        }

        drawPath(
            path = wavePath,
            color = lightColor,
            style = Stroke(
                width = strokeWidth * 0.8f,
                cap = androidx.compose.ui.graphics.StrokeCap.Round
            )
        )
    }
}

// 预览示例
@Preview
@Composable
fun VehicleLightsPreview() {
    VehicleLightsIndicator(
        width = 280f,
        height = 120f,
        backgroundColor = Color(0xFF2C2C2E),
        cornerRadius = 16f,
        highBeamState = true,
        lowBeamState = true,
        fogLightState = false,
        positionLightState = true
    )
}
