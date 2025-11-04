package com.neta.widgets.tirepressure

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TireRepair
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getColor
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.toHexString

/**
 * TirePressureWidget 默认值常量
 */
object TirePressureWidgetDefaults {
    // 颜色常量
    val PRESSURE_COLOR = Color(0xFF00D4FF)  // 青色发光效果
    val TEMP_COLOR = Color(0xFF8AB4F8)      // 淡蓝色温度
    val BACKGROUND_COLOR = Color(0xFF0A0E27) // 深色背景
    val CAR_COLOR = Color(0xFF1E3A5F)       // 车辆轮廓颜色
    val TIRE_COLOR = Color(0xFF2C4A6E)      // 轮胎颜色

    // 默认值
    const val SHOW_PRESSURE_UNIT = true
    const val SHOW_TEMP_UNIT = true
    const val PRESSURE_UNIT = "bar"
    val WIDTH = 280.dp
    val HEIGHT = 180.dp
}

/**
 * 胎压温度监测小组件
 * 按照图片设计：深色背景、车辆俯视图、四角胎压温度显示
 */
object TirePressureWidgetSpec : WidgetSpec(
    type = "tire_pressure_widget",
    displayName = "胎压监测",
    paramSchema = listOf(
        WidgetParamDesc(
            key = "pressureColor",
            label = "胎压颜色",
            type = WidgetParamType.COLOR,
            defaultValue = TirePressureWidgetDefaults.PRESSURE_COLOR.toHexString()
        ),
        WidgetParamDesc(
            key = "tempColor",
            label = "温度颜色",
            type = WidgetParamType.COLOR,
            defaultValue = TirePressureWidgetDefaults.TEMP_COLOR.toHexString()
        ),
        WidgetParamDesc(
            key = "showPressureUnit",
            label = "显示胎压单位",
            type = WidgetParamType.BOOL,
            defaultValue = TirePressureWidgetDefaults.SHOW_PRESSURE_UNIT
        ),
        WidgetParamDesc(
            key = "showTempUnit",
            label = "显示温度单位",
            type = WidgetParamType.BOOL,
            defaultValue = TirePressureWidgetDefaults.SHOW_TEMP_UNIT
        ),
        WidgetParamDesc(
            key = "pressureUnit",
            label = "胎压单位",
            type = WidgetParamType.ENUM,
            defaultValue = TirePressureWidgetDefaults.PRESSURE_UNIT,
            options = listOf("bar", "kPa", "psi")
        ),
        WidgetParamDesc(
            key = "pressureDataSource",
            label = "胎压数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = null,
            options = emptyList(),
            required = true
        ),
        WidgetParamDesc(
            key = "temperatureDataSource",
            label = "温度数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = null,
            options = emptyList(),
            required = true
        ),
    ),
    contentComposable = {
        TirePressureWidgetContent(it)
    },
    color = Color(0xFF00BCD4),
    icon = Icons.Default.TireRepair
)

@Composable
fun TirePressureWidgetContent(config: WidgetConfig) {
    // 解析颜色参数
    val pressureColor = config.getColor("pressureColor", TirePressureWidgetDefaults.PRESSURE_COLOR)
    val tempColor = config.getColor("tempColor", TirePressureWidgetDefaults.TEMP_COLOR)

    // 使用辅助函数获取自动注入的参数
    val scale = config.getScale()
    val alpha = config.getAlpha()

    val showPressureUnit = (config.params["showPressureUnit"] as? Boolean)
        ?: TirePressureWidgetDefaults.SHOW_PRESSURE_UNIT
    val showTempUnit = (config.params["showTempUnit"] as? Boolean)
        ?: TirePressureWidgetDefaults.SHOW_TEMP_UNIT
    val pressureUnit = config.params["pressureUnit"]?.toString()
        ?: TirePressureWidgetDefaults.PRESSURE_UNIT

    // 解析胎压和温度数据
    // 支持两种方式：
    // 1. 直接传入数组值（用于测试/预览）: "pressureValues": "2.6,2.6,2.6,2.6"
    // 2. 通过数据源获取（实际运行时由主应用注入）
    val pressureValuesStr = config.params["pressureValues"]?.toString()
    val temperatureValuesStr = config.params["temperatureValues"]?.toString()

    val pressureValues = pressureValuesStr?.split(",")
        ?.mapNotNull { it.trim().toFloatOrNull() }
        ?.takeIf { it.size == 4 }
        ?: listOf(2.6f, 2.6f, 2.6f, 2.6f) // 默认值

    val temperatureValues = temperatureValuesStr?.split(",")
        ?.mapNotNull { it.trim().toFloatOrNull() }
        ?.takeIf { it.size == 4 }
        ?: listOf(22f, 22f, 22f, 22f) // 默认值

    val tireData = TireData(
        frontLeft = TirePressureTemp(pressureValues[0], temperatureValues[0]),
        frontRight = TirePressureTemp(pressureValues[1], temperatureValues[1]),
        rearLeft = TirePressureTemp(pressureValues[2], temperatureValues[2]),
        rearRight = TirePressureTemp(pressureValues[3], temperatureValues[3])
    )

    CarTirePressureIndicator(
        tireData = tireData,
        scale = scale,
        alpha = alpha,
        showPressureUnit = showPressureUnit,
        showTempUnit = showTempUnit,
        pressureUnit = pressureUnit,
        pressureColor = pressureColor,
        tempColor = tempColor
    )
}

data class TirePressureTemp(val pressure: Float = 2.6f, val temperature: Float = 22f)
data class TireData(
    val frontLeft: TirePressureTemp = TirePressureTemp(),
    val frontRight: TirePressureTemp = TirePressureTemp(),
    val rearLeft: TirePressureTemp = TirePressureTemp(),
    val rearRight: TirePressureTemp = TirePressureTemp()
)

@Composable
fun CarTirePressureIndicator(
    tireData: TireData,
    modifier: Modifier = Modifier,
    scale: Float = 1f,
    alpha: Float = 1f,
    showPressureUnit: Boolean = TirePressureWidgetDefaults.SHOW_PRESSURE_UNIT,
    showTempUnit: Boolean = TirePressureWidgetDefaults.SHOW_TEMP_UNIT,
    pressureUnit: String = TirePressureWidgetDefaults.PRESSURE_UNIT,
    pressureColor: Color = TirePressureWidgetDefaults.PRESSURE_COLOR,
    tempColor: Color = TirePressureWidgetDefaults.TEMP_COLOR,
    width: Float = 280f,
    height: Float = 180f
) {
    // 格式化胎压显示
    fun formatPressure(pressure: Float): String {
        val value = when (pressureUnit) {
            "kPa" -> pressure * 100
            "psi" -> pressure * 14.5038
            else -> pressure
        }
        return "%.1f".format(value)
    }

    // 获取压力单位显示文本
    fun getPressureUnitText(): String {
        return when (pressureUnit) {
            "kPa" -> "kPa"
            "psi" -> "psi"
            else -> "bar"
        }
    }

    Box(
        modifier = modifier
            .width((width * scale).dp)
            .height((height * scale).dp)
            .background(TirePressureWidgetDefaults.BACKGROUND_COLOR)
            .graphicsLayer {
                this.alpha = alpha
            }
    ) {
        // 绘制车辆轮廓和轮胎
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding((16 * scale).dp)
        ) {
            drawCarTopView(scale)
        }

        // 左上 - 左前轮
        TireInfoDisplay(
            position = Alignment.TopStart,
            pressure = tireData.frontLeft.pressure,
            temperature = tireData.frontLeft.temperature,
            showPressureUnit = showPressureUnit,
            showTempUnit = showTempUnit,
            pressureUnitText = getPressureUnitText(),
            pressureColor = pressureColor,
            tempColor = tempColor,
            scale = scale,
            formatPressure = { formatPressure(it) }
        )

        // 右上 - 右前轮
        TireInfoDisplay(
            position = Alignment.TopEnd,
            pressure = tireData.frontRight.pressure,
            temperature = tireData.frontRight.temperature,
            showPressureUnit = showPressureUnit,
            showTempUnit = showTempUnit,
            pressureUnitText = getPressureUnitText(),
            pressureColor = pressureColor,
            tempColor = tempColor,
            scale = scale,
            formatPressure = { formatPressure(it) }
        )

        // 左下 - 左后轮
        TireInfoDisplay(
            position = Alignment.BottomStart,
            pressure = tireData.rearLeft.pressure,
            temperature = tireData.rearLeft.temperature,
            showPressureUnit = showPressureUnit,
            showTempUnit = showTempUnit,
            pressureUnitText = getPressureUnitText(),
            pressureColor = pressureColor,
            tempColor = tempColor,
            scale = scale,
            formatPressure = { formatPressure(it) }
        )

        // 右下 - 右后轮
        TireInfoDisplay(
            position = Alignment.BottomEnd,
            pressure = tireData.rearRight.pressure,
            temperature = tireData.rearRight.temperature,
            showPressureUnit = showPressureUnit,
            showTempUnit = showTempUnit,
            pressureUnitText = getPressureUnitText(),
            pressureColor = pressureColor,
            tempColor = tempColor,
            scale = scale,
            formatPressure = { formatPressure(it) }
        )
    }
}

@Composable
private fun BoxScope.TireInfoDisplay(
    position: Alignment,
    pressure: Float,
    temperature: Float,
    showPressureUnit: Boolean,
    showTempUnit: Boolean,
    pressureUnitText: String,
    pressureColor: Color,
    tempColor: Color,
    scale: Float,
    formatPressure: (Float) -> String
) {
    Column(
        horizontalAlignment = when (position) {
            Alignment.TopStart, Alignment.BottomStart -> Alignment.Start
            Alignment.TopEnd, Alignment.BottomEnd -> Alignment.End
            else -> Alignment.CenterHorizontally
        },
        modifier = Modifier
            .align(position)
            .padding((12 * scale).dp)
    ) {
        // 胎压值（大字体，发光效果）
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = formatPressure(pressure),
                style = TextStyle(
                    color = pressureColor,
                    fontSize = (24 * scale).sp,
                    fontWeight = FontWeight.Bold,
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = pressureColor.copy(alpha = 0.6f),
                        offset = Offset(0f, 0f),
                        blurRadius = 8f
                    )
                )
            )
            if (showPressureUnit) {
                Spacer(modifier = Modifier.width((4 * scale).dp))
                Text(
                    text = pressureUnitText,
                    style = TextStyle(
                        color = pressureColor.copy(alpha = 0.7f),
                        fontSize = (14 * scale).sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(bottom = (2 * scale).dp)
                )
            }
        }

        // 温度值（小字体）
        Spacer(modifier = Modifier.height((2 * scale).dp))
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "%.0f".format(temperature),
                style = TextStyle(
                    color = tempColor,
                    fontSize = (14 * scale).sp,
                    fontWeight = FontWeight.Normal
                )
            )
            if (showTempUnit) {
                Spacer(modifier = Modifier.width((2 * scale).dp))
                Text(
                    text = "℃",
                    style = TextStyle(
                        color = tempColor.copy(alpha = 0.7f),
                        fontSize = (12 * scale).sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}

/**
 * 绘制车辆俯视图
 */
private fun DrawScope.drawCarTopView(scale: Float) {
    val carColor = TirePressureWidgetDefaults.CAR_COLOR
    val tireColor = TirePressureWidgetDefaults.TIRE_COLOR

    val centerX = size.width / 2
    val centerY = size.height / 2

    // 车身尺寸（俯视图，按照原图比例：较窄但较长）
    val carWidth = size.width * 0.233f  // 原来的 2/3
    val carHeight = size.height * 0.65f

    // 绘制车身（圆角矩形）
    val carPath = Path().apply {
        addRoundRect(
            RoundRect(
                left = centerX - carWidth / 2,
                top = centerY - carHeight / 2,
                right = centerX + carWidth / 2,
                bottom = centerY + carHeight / 2,
                cornerRadius = CornerRadius(8.dp.toPx() * scale, 8.dp.toPx() * scale)
            )
        )
    }
    drawPath(
        path = carPath,
        color = carColor,
        style = Stroke(width = 2.dp.toPx() * scale)
    )

    // 轮胎尺寸
    val tireWidth = 16.dp.toPx() * scale
    val tireHeight = 28.dp.toPx() * scale
    val tireRadius = 4.dp.toPx() * scale

    // 轮胎位置偏移
    // X: 紧贴车身两侧外面
    val tireOffsetX = carWidth / 2 + 8.dp.toPx() * scale
    // Y: 在车身顶部和底部附近，前后轮之间增加 8dp 距离（每侧+4dp）
    val tireOffsetY = carHeight / 2 - tireHeight / 2

    // 绘制四个轮胎
    val tirePositions = listOf(
        // 左前
        Offset(centerX - tireOffsetX, centerY - tireOffsetY),
        // 右前
        Offset(centerX + tireOffsetX - tireWidth, centerY - tireOffsetY),
        // 左后
        Offset(centerX - tireOffsetX, centerY + tireOffsetY - tireHeight),
        // 右后
        Offset(centerX + tireOffsetX - tireWidth, centerY + tireOffsetY - tireHeight)
    )

    tirePositions.forEach { pos ->
        drawRoundRect(
            color = tireColor,
            topLeft = pos,
            size = Size(tireWidth, tireHeight),
            cornerRadius = CornerRadius(tireRadius, tireRadius)
        )
        // 轮胎高亮
        drawRoundRect(
            color = tireColor.copy(alpha = 0.3f),
            topLeft = pos.copy(x = pos.x + tireWidth * 0.2f, y = pos.y + tireHeight * 0.1f),
            size = Size(tireWidth * 0.3f, tireHeight * 0.4f),
            cornerRadius = CornerRadius(tireRadius / 2, tireRadius)
        )
    }
}

// 预览示例
@Preview
@Composable
fun TirePressureWidgetPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "胎压监测 - 默认状态",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        CarTirePressureIndicator(
            tireData = TireData(
                frontLeft = TirePressureTemp(2.6f, 22f),
                frontRight = TirePressureTemp(2.6f, 23f),
                rearLeft = TirePressureTemp(2.6f, 22f),
                rearRight = TirePressureTemp(2.6f, 22f)
            )
        )

        Text(
            "胎压监测 - 不同胎压",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        CarTirePressureIndicator(
            tireData = TireData(
                frontLeft = TirePressureTemp(2.4f, 22f),
                frontRight = TirePressureTemp(2.5f, 25f),
                rearLeft = TirePressureTemp(2.7f, 24f),
                rearRight = TirePressureTemp(2.3f, 23f)
            )
        )

        Text(
            "胎压监测 - 不同缩放",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            CarTirePressureIndicator(
                tireData = TireData(),
                scale = 0.8f
            )
            CarTirePressureIndicator(
                tireData = TireData(),
                scale = 1.2f
            )
        }
    }
}
