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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getDataSourceFloatArray
import com.neta.isulewtools.api.widget.getDataSourceIntArray
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.toHexString

/**
 * 胎压温度监测小组件
 * 按照图片设计：深色背景、车辆俯视图、四角胎压温度显示
 */
object TirePressureWidgetSpec : WidgetSpec(
    type = "tire_pressure_widget",
    displayName = "胎压监测",
    recommendedGrid = Pair(3, 2),
    paramSchema = WidgetParamDesc.buildParams {
        +WidgetParamDesc(
            key = P.PRESSURE_COLOR.key,
            label = "胎压颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.PRESSURE_COLOR.default.toHexString(),
            description = "胎压数值的显示颜色"
        )
        +WidgetParamDesc(
            key = P.TEMP_COLOR.key,
            label = "温度颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.TEMP_COLOR.default.toHexString(),
            description = "温度数值的显示颜色"
        )
        +WidgetParamDesc(
            key = P.BACKGROUND_COLOR.key,
            label = "背景颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BACKGROUND_COLOR.default.toHexString(),
            description = "组件的背景颜色"
        )
        +WidgetParamDesc(
            key = P.SHOW_PRESSURE_UNIT.key,
            label = "显示胎压单位",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_PRESSURE_UNIT.default,
            description = "显示胎压值的单位(bar/kPa/psi)"
        )
        +WidgetParamDesc(
            key = P.SHOW_TEMP_UNIT.key,
            label = "显示温度单位",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_TEMP_UNIT.default,
            description = "显示温度值的单位(℃)"
        )
        +WidgetParamDesc(
            key = P.PRESSURE_UNIT.key,
            label = "胎压单位",
            type = WidgetParamType.ENUM,
            defaultValue = P.PRESSURE_UNIT.default,
            options = listOf("bar", "kPa", "psi"),
            description = "胎压显示的单位制"
        )
        +WidgetParamDesc(
            key = P.PRESSURE_DATA_SOURCE.key,
            label = "胎压数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.PRESSURE_DATA_SOURCE.default,
            options = emptyList(),
            required = true,
            description = "四轮胎压数据源(数组格式:左前/右前/左后/右后)"
        )
        +WidgetParamDesc(
            key = P.TEMPERATURE_DATA_SOURCE.key,
            label = "温度数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.TEMPERATURE_DATA_SOURCE.default,
            options = emptyList(),
            required = true,
            description = "四轮温度数据源(数组格式:左前/右前/左后/右后)"
        )
    },
    contentComposable = {
        TirePressureWidgetContent(it)
    },
    color = Color(0xFF00BCD4),
    icon = Icons.Default.TireRepair
) {
    /**
     * 参数定义对象（使用 ParamDef）
     */
    object P {
        val PRESSURE_COLOR = ParamDef("pressureColor", Color(0xFF00D4FF))
        val TEMP_COLOR = ParamDef("tempColor", Color(0xFF8AB4F8))
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color(0xFF0A0E27))
        val SHOW_PRESSURE_UNIT = ParamDef("showPressureUnit", true)
        val SHOW_TEMP_UNIT = ParamDef("showTempUnit", true)
        val PRESSURE_UNIT = ParamDef("pressureUnit", "bar")

        // 数据源参数（使用 ParamDef，default 为默认属性名）
        val PRESSURE_DATA_SOURCE = ParamDef("pressureDataSource", "HZ_BDCS4_TYRE_PRESSURE")
        val TEMPERATURE_DATA_SOURCE = ParamDef("temperatureDataSource", "HZ_BDCS4_TYRE_TEMPERATURE")

        // 其他默认值（不是参数）
        val CAR_COLOR = Color(0xFF1E3A5F)
        val TIRE_COLOR = Color(0xFF2C4A6E)
        val WIDTH = 280.dp
        val HEIGHT = 180.dp
    }
}

@Composable
fun TirePressureWidgetContent(config: WidgetConfig) {
    // 使用 getParam 读取参数
    val pressureColor = config.getParam(TirePressureWidgetSpec.P.PRESSURE_COLOR)
    val tempColor = config.getParam(TirePressureWidgetSpec.P.TEMP_COLOR)
    val backgroundColor = config.getParam(TirePressureWidgetSpec.P.BACKGROUND_COLOR)
    val showPressureUnit = config.getParam(TirePressureWidgetSpec.P.SHOW_PRESSURE_UNIT)
    val showTempUnit = config.getParam(TirePressureWidgetSpec.P.SHOW_TEMP_UNIT)
    val pressureUnit = config.getParam(TirePressureWidgetSpec.P.PRESSURE_UNIT)

    // 使用辅助函数获取自动注入的参数
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 从 config 获取注入的数据源值
    // 注意：数据由主 app 在运行时通过数据源订阅后注入到 params 中
    // 插件本身不直接访问数据源，因为数据获取函数在主 app 中
    // 胎压数据：FLOAT_VEC 类型，直接获取 FloatArray
    // 注意：VHAL 中的胎压数据以 0.01 bar 为单位存储（220f = 2.20 bar），需要除以 100
    val pressureArray =
        config.getDataSourceFloatArray(TirePressureWidgetSpec.P.PRESSURE_DATA_SOURCE.key)
        ?.takeIf { it.size == 4 }
        ?.map { it / 100f }  // 除以 100 得到实际 bar 值
        ?: listOf(2.6f, 2.6f, 2.6f, 2.6f) // 默认值

    // 温度数据：INT32_VEC 类型，获取 IntArray（温度为整数）
    val temperatureArray =
        config.getDataSourceIntArray(TirePressureWidgetSpec.P.TEMPERATURE_DATA_SOURCE.key)
        ?.takeIf { it.size == 4 }
        ?: intArrayOf(22, 22, 22, 22) // 默认值

    // 转换为 List 以便后续使用
    val pressureValues = pressureArray // 已经是 List<Float>
    val temperatureValues = temperatureArray.toList() // 保持为 List<Int>

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
        tempColor = tempColor,
        backgroundColor = backgroundColor
    )
}

data class TirePressureTemp(val pressure: Float = 2.6f, val temperature: Int = 22)
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
    showPressureUnit: Boolean = TirePressureWidgetSpec.P.SHOW_PRESSURE_UNIT.default,
    showTempUnit: Boolean = TirePressureWidgetSpec.P.SHOW_TEMP_UNIT.default,
    pressureUnit: String = TirePressureWidgetSpec.P.PRESSURE_UNIT.default,
    pressureColor: Color = TirePressureWidgetSpec.P.PRESSURE_COLOR.default,
    tempColor: Color = TirePressureWidgetSpec.P.TEMP_COLOR.default,
    backgroundColor: Color = TirePressureWidgetSpec.P.BACKGROUND_COLOR.default,
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
            .graphicsLayer {
                this.alpha = alpha
            }
            .background(backgroundColor, RoundedCornerShape((16 * scale).dp))
            .padding((16 * scale).dp)
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
    temperature: Int,
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
                text = temperature.toString(),
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
    val carColor = TirePressureWidgetSpec.P.CAR_COLOR
    val tireColor = TirePressureWidgetSpec.P.TIRE_COLOR

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
    val tireHeight = 20.dp.toPx() * scale
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
                frontLeft = TirePressureTemp(2.6f, 22),
                frontRight = TirePressureTemp(2.6f, 23),
                rearLeft = TirePressureTemp(2.6f, 22),
                rearRight = TirePressureTemp(2.6f, 22)
            )
        )

        Text(
            "胎压监测 - 不同缩放",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Column {
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
