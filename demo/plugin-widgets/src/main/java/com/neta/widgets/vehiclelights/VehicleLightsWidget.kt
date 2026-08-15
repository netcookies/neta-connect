package com.neta.widgets.vehiclelights

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
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
    recommendedGrid = Pair(3, 1),
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
        val WIDTH = ParamDef("width", 600f)
        val HEIGHT = ParamDef("height", 200f)

        // 背景
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color(0xFF121623))
        val CORNER_RADIUS = ParamDef("cornerRadius", 16f)

        // 远光灯
        val SHOW_HIGH_BEAM = ParamDef("showHighBeam", true)
        val HIGH_BEAM_COLOR = ParamDef("highBeamColor", Color(0xFF486DDE))
        val HIGH_BEAM_DATASOURCE = ParamDef("highBeamDatasource", "HZ_HIGHT_BEAN_STATUS")

        // 近光灯
        val SHOW_LOW_BEAM = ParamDef("showLowBeam", true)
        val LOW_BEAM_COLOR = ParamDef("lowBeamColor", Color(0xFF88FF00))
        val LOW_BEAM_DATASOURCE = ParamDef("lowBeamDatasource", "HZ_LOW_BEAN_STATUS")

        // 雾灯
        val SHOW_FOG_LIGHT = ParamDef("showFogLight", true)
        val FOG_LIGHT_COLOR = ParamDef("fogLightColor", Color(0xFFFFC107))
        val FOG_LIGHT_DATASOURCE = ParamDef("fogLightDatasource", "HZ_REAR_FOG_LAMP_ON")
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
        fogLightState = fogLightState
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
    fogLightState: Boolean = false
) {
    val lightSize = 180
    Box(
        modifier = Modifier
            .size(width = (width * scale).dp, height = (height * scale).dp)
            .clip(RoundedCornerShape((cornerRadius * scale).dp))
            .background(backgroundColor)
            .graphicsLayer(alpha = alpha),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding((10 * scale).dp),
            horizontalArrangement = Arrangement.spacedBy((10 * scale).dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showHighBeam) {
                HighBeamLight(
                    color = highBeamColor,
                    isOn = highBeamState,
                    size = (lightSize * scale).dp
                )
            }
            if (showLowBeam) {
                LowBeamLight(
                    color = lowBeamColor,
                    isOn = lowBeamState,
                    size = (lightSize * scale).dp
                )
            }
            if (showFogLight) {
                FogLight(
                    color = fogLightColor,
                    isOn = fogLightState,
                    size = (lightSize * scale).dp
                )
            }
        }
    }
}

// ===== 独立的灯光组件 =====

/**
 * 远光灯组件
 */
@Composable
fun HighBeamLight(
    color: Color,
    isOn: Boolean,
    size: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(size)) {
        drawHighBeamLight(
            center = Offset(this.size.width / 2, this.size.height / 2),
            size = this.size.width,
            color = color,
            isOn = isOn
        )
    }
}

/**
 * 近光灯组件
 */
@Composable
fun LowBeamLight(
    color: Color,
    isOn: Boolean,
    size: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(size)) {
        drawLowBeamLight(
            center = Offset(this.size.width / 2, this.size.height / 2),
            size = this.size.width,
            color = color,
            isOn = isOn
        )
    }
}

/**
 * 雾灯组件
 */
@Composable
fun FogLight(
    color: Color,
    isOn: Boolean,
    size: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(size)) {
        drawFogLight(
            center = Offset(this.size.width / 2, this.size.height / 2),
            size = this.size.width,
            color = color,
            isOn = isOn
        )
    }
}

// ===== 绘制函数（内部使用）=====

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
@Preview(showBackground = true, backgroundColor = 0xFF1C1C1E)
@Composable
fun VehicleLightsPreview() {
    Box(
        modifier = Modifier
            .size(1000.dp, 800.dp)
            .background(Color(0xFF1C1C1E)),
        contentAlignment = Alignment.Center
    ) {
        VehicleLightsIndicator(
            width = 600f,
            height = 200f,
            scale = 1f,
            backgroundColor = Color(0xFF2C2C2E),
            cornerRadius = 16f,
            highBeamState = true,
            lowBeamState = true,
            fogLightState = false
        )
    }
}
