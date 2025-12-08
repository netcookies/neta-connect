package com.neta.widgets.temperature

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getDataSourceFloat
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.toHexString

/**
 * 温度计组件
 */
object TemperatureWidgetSpec : WidgetSpec(
    type = "temperature_widget",
    displayName = "温度计",
    recommendedGrid = Pair(1, 2),
    paramSchema = WidgetParamDesc.buildParams {
        +WidgetParamDesc(
            key = P.MIN_TEMP.key,
            label = "最低温度(℃)",
            type = WidgetParamType.INT,
            defaultValue = P.MIN_TEMP.default.toInt(),
            description = "温度计显示的最小刻度值"
        )
        +WidgetParamDesc(
            key = P.MAX_TEMP.key,
            label = "最高温度(℃)",
            type = WidgetParamType.INT,
            defaultValue = P.MAX_TEMP.default.toInt(),
            description = "温度计显示的最大刻度值"
        )
        +WidgetParamDesc(
            key = P.COLD_COLOR.key,
            label = "低温颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.COLD_COLOR.default.toHexString(),
            description = "低于10℃时的填充颜色"
        )
        +WidgetParamDesc(
            key = P.NORMAL_COLOR.key,
            label = "常温颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.NORMAL_COLOR.default.toHexString(),
            description = "10-30℃时的填充颜色"
        )
        +WidgetParamDesc(
            key = P.HOT_COLOR.key,
            label = "高温颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.HOT_COLOR.default.toHexString(),
            description = "高于30℃时的填充颜色"
        )
        +WidgetParamDesc(
            key = P.BORDER_COLOR.key,
            label = "边框颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BORDER_COLOR.default.toHexString(),
            description = "温度计外框的颜色"
        )
        +WidgetParamDesc(
            key = P.BACKGROUND_COLOR.key,
            label = "背景色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BACKGROUND_COLOR.default.toHexString(),
            description = "温度计内部的背景颜色"
        )
        +WidgetParamDesc(
            key = P.TEXT_COLOR.key,
            label = "文字颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.TEXT_COLOR.default.toHexString(),
            description = "温度数值的文字颜色"
        )
        +WidgetParamDesc(
            key = P.SHOW_TEXT.key,
            label = "显示数值",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_TEXT.default,
            description = "显示温度数字"
        )
        +WidgetParamDesc(
            key = P.DATASOURCE.key,
            label = "温度数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.DATASOURCE.default,
            options = emptyList(),
            required = true,
            description = "温度值的数据来源"
        )
    },
    contentComposable = {
        TemperatureWidgetContent(it)
    },
    color = Color(0xFFFF9800),
    icon = Icons.Default.Thermostat
) {
    /**
     * 参数定义对象（使用 ParamDef）
     */
    object P {
        val MIN_TEMP = ParamDef("minTemp", -20f)
        val MAX_TEMP = ParamDef("maxTemp", 50f)
        val COLD_COLOR = ParamDef("coldColor", Color(0xFF007AFF))
        val NORMAL_COLOR = ParamDef("normalColor", Color(0xFF34C759))
        val HOT_COLOR = ParamDef("hotColor", Color(0xFFFF3B30))
        val BORDER_COLOR = ParamDef("borderColor", Color(0xFFDDDDDD))
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color.White)
        val TEXT_COLOR = ParamDef("textColor", Color.Black)
        val SHOW_TEXT = ParamDef("showText", true)

        // 数据源参数（使用 ParamDef，default 为默认属性名）
        val DATASOURCE = ParamDef("datasource", "HZ_INSIDE_TEMP")

        // 其他默认值（不是参数）
        val WIDTH = 40.dp
        val HEIGHT = 120.dp
    }
}

@Composable
fun TemperatureWidgetContent(config: WidgetConfig) {
    val minTemp = config.getParam(TemperatureWidgetSpec.P.MIN_TEMP)
    val maxTemp = config.getParam(TemperatureWidgetSpec.P.MAX_TEMP)
    val coldColor = config.getParam(TemperatureWidgetSpec.P.COLD_COLOR)
    val normalColor = config.getParam(TemperatureWidgetSpec.P.NORMAL_COLOR)
    val hotColor = config.getParam(TemperatureWidgetSpec.P.HOT_COLOR)
    val borderColor = config.getParam(TemperatureWidgetSpec.P.BORDER_COLOR)
    val backgroundColor = config.getParam(TemperatureWidgetSpec.P.BACKGROUND_COLOR)
    val textColor = config.getParam(TemperatureWidgetSpec.P.TEXT_COLOR)
    val showText = config.getParam(TemperatureWidgetSpec.P.SHOW_TEXT)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 从注入的数据中读取温度值
    val temperature = config.getDataSourceFloat(TemperatureWidgetSpec.P.DATASOURCE.key, 20f)

    TemperatureIndicator(
        temperature = temperature,
        minTemp = minTemp,
        maxTemp = maxTemp,
        scale = scale,
        alpha = alpha,
        coldColor = coldColor,
        normalColor = normalColor,
        hotColor = hotColor,
        borderColor = borderColor,
        backgroundColor = backgroundColor,
        textColor = textColor,
        showText = showText
    )
}

@Composable
fun TemperatureIndicator(
    modifier: Modifier = Modifier,
    temperature: Float,
    minTemp: Float = TemperatureWidgetSpec.P.MIN_TEMP.default,
    maxTemp: Float = TemperatureWidgetSpec.P.MAX_TEMP.default,
    scale: Float = 1f,
    alpha: Float = 1f,
    coldColor: Color = TemperatureWidgetSpec.P.COLD_COLOR.default,
    normalColor: Color = TemperatureWidgetSpec.P.NORMAL_COLOR.default,
    hotColor: Color = TemperatureWidgetSpec.P.HOT_COLOR.default,
    borderColor: Color = TemperatureWidgetSpec.P.BORDER_COLOR.default,
    backgroundColor: Color = TemperatureWidgetSpec.P.BACKGROUND_COLOR.default,
    textColor: Color = TemperatureWidgetSpec.P.TEXT_COLOR.default,
    showText: Boolean = TemperatureWidgetSpec.P.SHOW_TEXT.default,
    width: Dp = TemperatureWidgetSpec.P.WIDTH,
    height: Dp = TemperatureWidgetSpec.P.HEIGHT
) {
    val currentTemp = temperature.coerceIn(minTemp, maxTemp)
    val fillPercent = (currentTemp - minTemp) / (maxTemp - minTemp)

    // 根据温度选择颜色
    val fillColor = when {
        currentTemp < 10f -> coldColor
        currentTemp < 30f -> normalColor
        else -> hotColor
    }

    Box(
        modifier = modifier
            .width(width * scale)
            .height(height * scale)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = alpha)
        ) {
            val bulbRadius = 15.dp.toPx() * scale
            val tubeWidth = 12.dp.toPx() * scale
            val tubeHeight = size.height - bulbRadius * 2 - 5.dp.toPx() * scale
            val tubeCenterX = size.width / 2
            val tubeTop = 5.dp.toPx() * scale

            // 绘制温度计管身背景
            drawRoundRect(
                color = backgroundColor,
                topLeft = Offset(tubeCenterX - tubeWidth / 2, tubeTop),
                size = Size(tubeWidth, tubeHeight),
                cornerRadius = CornerRadius(tubeWidth / 2, tubeWidth / 2)
            )

            // 绘制温度计管身边框
            drawRoundRect(
                color = borderColor,
                topLeft = Offset(tubeCenterX - tubeWidth / 2, tubeTop),
                size = Size(tubeWidth, tubeHeight),
                cornerRadius = CornerRadius(tubeWidth / 2, tubeWidth / 2),
                style = Stroke(width = 1.5.dp.toPx() * scale)
            )

            // 绘制底部圆球背景
            drawCircle(
                color = backgroundColor,
                radius = bulbRadius,
                center = Offset(tubeCenterX, size.height - bulbRadius)
            )

            // 绘制底部圆球边框
            drawCircle(
                color = borderColor,
                radius = bulbRadius,
                center = Offset(tubeCenterX, size.height - bulbRadius),
                style = Stroke(width = 1.5.dp.toPx() * scale)
            )

            // 绘制温度填充(从下往上)
            val fillHeight = tubeHeight * fillPercent
            val fillBottom = tubeTop + tubeHeight

            // 填充圆球
            drawCircle(
                color = fillColor,
                radius = bulbRadius - 2.dp.toPx() * scale,
                center = Offset(tubeCenterX, size.height - bulbRadius)
            )

            // 填充管身
            if (fillHeight > 0) {
                drawRoundRect(
                    color = fillColor,
                    topLeft = Offset(
                        tubeCenterX - tubeWidth / 2 + 2.dp.toPx() * scale,
                        fillBottom - fillHeight
                    ),
                    size = Size(tubeWidth - 4.dp.toPx() * scale, fillHeight),
                    cornerRadius = CornerRadius(
                        (tubeWidth - 4.dp.toPx() * scale) / 2,
                        (tubeWidth - 4.dp.toPx() * scale) / 2
                    )
                )
            }
        }

        // 温度数字
        if (showText) {
            Text(
                text = "${currentTemp.toInt()}°",
                style = TextStyle(
                    color = textColor,
                    fontSize = (14 * scale).sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp * scale)
            )
        }
    }
}

@Preview
@Composable
fun TemperaturePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("不同温度状态", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TemperatureIndicator(temperature = -10f)
            TemperatureIndicator(temperature = 5f)
            TemperatureIndicator(temperature = 20f)
            TemperatureIndicator(temperature = 35f)
            TemperatureIndicator(temperature = 45f)
        }
    }
}
