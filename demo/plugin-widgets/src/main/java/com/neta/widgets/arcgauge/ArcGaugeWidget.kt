package com.neta.widgets.arcgauge

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
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
import com.neta.isulewtools.api.widget.WidgetFont
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getDataSourceFloat
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.rememberWidgetFontFamily
import com.neta.isulewtools.api.widget.toHexString

/**
 * 弧形仪表盘组件
 */
object ArcGaugeWidgetSpec : WidgetSpec(
    type = "arc_gauge_widget",
    displayName = "弧形仪表盘",
    paramSchema = WidgetParamDesc.buildParams {
        +WidgetParamDesc(
            key = P.FULL_COLOR.key,
            label = "高值颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.FULL_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.MEDIUM_COLOR.key,
            label = "中等值颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.MEDIUM_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.LOW_COLOR.key,
            label = "低值颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.LOW_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.ARC_COLOR.key,
            label = "弧线颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.ARC_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.TEXT_COLOR.key,
            label = "文字颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.TEXT_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.SHOW_TEXT.key,
            label = "显示文字",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_TEXT.default
        )
        +WidgetParamDesc(
            key = P.SHOW_PERCENTAGE.key,
            label = "显示百分比",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_PERCENTAGE.default
        )
        +WidgetParamDesc(
            key = P.MIN_VALUE.key,
            label = "最小值",
            type = WidgetParamType.FLOAT,
            defaultValue = P.MIN_VALUE.default
        )
        +WidgetParamDesc(
            key = P.MAX_VALUE.key,
            label = "最大值",
            type = WidgetParamType.FLOAT,
            defaultValue = P.MAX_VALUE.default
        )
        +WidgetParamDesc(
            key = P.OPTIMAL_RANGE_LOW.key,
            label = "推荐区间下限",
            type = WidgetParamType.FLOAT,
            defaultValue = P.OPTIMAL_RANGE_LOW.default
        )
        +WidgetParamDesc(
            key = P.OPTIMAL_RANGE_HIGH.key,
            label = "推荐区间上限",
            type = WidgetParamType.FLOAT,
            defaultValue = P.OPTIMAL_RANGE_HIGH.default
        )
        +WidgetParamDesc(
            key = P.UNIT.key,
            label = "单位文字",
            type = WidgetParamType.STRING,
            defaultValue = P.UNIT.default
        )
        +WidgetParamDesc(
            key = P.DESCRIPTION.key,
            label = "描述文字",
            type = WidgetParamType.STRING,
            defaultValue = P.DESCRIPTION.default
        )
        +WidgetParamDesc(
            key = P.DATASOURCE,
            label = "数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = null,
            options = emptyList(),
            required = true
        )
    },
    contentComposable = {
        ArcGaugeWidgetContent(it)
    },
    color = Color(0xFFFFC107),
    icon = Icons.Default.LocalGasStation
) {
    /**
     * 参数定义
     */
    object P {
        val FULL_COLOR = ParamDef("fullColor", Color(0xFF34C759))
        val MEDIUM_COLOR = ParamDef("mediumColor", Color(0xFFFFCC00))
        val LOW_COLOR = ParamDef("lowColor", Color(0xFFFF3B30))
        val ARC_COLOR = ParamDef("arcColor", Color(0xFFDDDDDD))
        val TEXT_COLOR = ParamDef("textColor", Color.Black)
        val SHOW_TEXT = ParamDef("showText", true)
        val SHOW_PERCENTAGE = ParamDef("showPercentage", false)
        val MIN_VALUE = ParamDef("minValue", 0f)
        val MAX_VALUE = ParamDef("maxValue", 100f)
        val OPTIMAL_RANGE_LOW = ParamDef("optimalRangeLow", 20f)
        val OPTIMAL_RANGE_HIGH = ParamDef("optimalRangeHigh", 80f)
        val UNIT = ParamDef("unit", "L/100km")
        val DESCRIPTION = ParamDef("description", "百公里耗油量")

        // 数据源参数只定义 key
        const val DATASOURCE = "datasource"

        // 非参数常量
        val SIZE = 150.dp
        const val ANIMATION_DURATION = 400 // 动画时长(毫秒)
    }
}

@Composable
fun ArcGaugeWidgetContent(config: WidgetConfig) {
    val fullColor = config.getParam(ArcGaugeWidgetSpec.P.FULL_COLOR)
    val mediumColor = config.getParam(ArcGaugeWidgetSpec.P.MEDIUM_COLOR)
    val lowColor = config.getParam(ArcGaugeWidgetSpec.P.LOW_COLOR)
    val arcColor = config.getParam(ArcGaugeWidgetSpec.P.ARC_COLOR)
    val textColor = config.getParam(ArcGaugeWidgetSpec.P.TEXT_COLOR)
    val showText = config.getParam(ArcGaugeWidgetSpec.P.SHOW_TEXT)
    val showPercentage = config.getParam(ArcGaugeWidgetSpec.P.SHOW_PERCENTAGE)
    val minValue = config.getParam(ArcGaugeWidgetSpec.P.MIN_VALUE)
    val maxValue = config.getParam(ArcGaugeWidgetSpec.P.MAX_VALUE)
    val optimalRangeLow = config.getParam(ArcGaugeWidgetSpec.P.OPTIMAL_RANGE_LOW)
    val optimalRangeHigh = config.getParam(ArcGaugeWidgetSpec.P.OPTIMAL_RANGE_HIGH)
    val unit = config.getParam(ArcGaugeWidgetSpec.P.UNIT)
    val description = config.getParam(ArcGaugeWidgetSpec.P.DESCRIPTION)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 从注入的数据中读取值
    val rawValue = config.getDataSourceFloat(ArcGaugeWidgetSpec.P.DATASOURCE, minValue)

    ArcGaugeIndicator(
        value = rawValue,
        minValue = minValue,
        maxValue = maxValue,
        scale = scale,
        alpha = alpha,
        fullColor = fullColor,
        mediumColor = mediumColor,
        lowColor = lowColor,
        arcColor = arcColor,
        textColor = textColor,
        showText = showText,
        showPercentage = showPercentage,
        optimalRangeLow = optimalRangeLow,
        optimalRangeHigh = optimalRangeHigh,
        unit = unit,
        description = description
    )
}

@Composable
fun ArcGaugeIndicator(
    modifier: Modifier = Modifier,
    value: Float,
    minValue: Float = ArcGaugeWidgetSpec.P.MIN_VALUE.default,
    maxValue: Float = ArcGaugeWidgetSpec.P.MAX_VALUE.default,
    scale: Float = 1f,
    alpha: Float = 1f,
    fullColor: Color = ArcGaugeWidgetSpec.P.FULL_COLOR.default,
    mediumColor: Color = ArcGaugeWidgetSpec.P.MEDIUM_COLOR.default,
    lowColor: Color = ArcGaugeWidgetSpec.P.LOW_COLOR.default,
    arcColor: Color = ArcGaugeWidgetSpec.P.ARC_COLOR.default,
    textColor: Color = ArcGaugeWidgetSpec.P.TEXT_COLOR.default,
    showText: Boolean = ArcGaugeWidgetSpec.P.SHOW_TEXT.default,
    showPercentage: Boolean = ArcGaugeWidgetSpec.P.SHOW_PERCENTAGE.default,
    optimalRangeLow: Float = ArcGaugeWidgetSpec.P.OPTIMAL_RANGE_LOW.default,
    optimalRangeHigh: Float = ArcGaugeWidgetSpec.P.OPTIMAL_RANGE_HIGH.default,
    unit: String = ArcGaugeWidgetSpec.P.UNIT.default,
    description: String = ArcGaugeWidgetSpec.P.DESCRIPTION.default,
    size: Dp = ArcGaugeWidgetSpec.P.SIZE
) {
    // 加载字体
    val fontFamily = rememberWidgetFontFamily(WidgetFont.MONTSERRAT_BOLD, FontWeight.Bold)

    val currentValue = value.coerceIn(minValue, maxValue)

    // 计算百分比（0-100）
    val percentage = if (maxValue > minValue) {
        ((currentValue - minValue) / (maxValue - minValue)) * 100f
    } else {
        0f
    }

    // 添加平滑动画过渡
    val animatedPercentage by animateFloatAsState(
        targetValue = percentage,
        animationSpec = tween(
            durationMillis = ArcGaugeWidgetSpec.P.ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        ),
        label = "gauge_animation"
    )

    // 原文使用 150度起始，240度扫描
    val startAngle = 150f
    val maxSweepAngle = 240f
    val sweepAngle = (animatedPercentage / 100f) * maxSweepAngle

    // 计算推荐区间的百分比
    val optimalLowPercent = if (maxValue > minValue) {
        ((optimalRangeLow.coerceIn(minValue, maxValue) - minValue) / (maxValue - minValue)) * 100f
    } else {
        0f
    }
    val optimalHighPercent = if (maxValue > minValue) {
        ((optimalRangeHigh.coerceIn(minValue, maxValue) - minValue) / (maxValue - minValue)) * 100f
    } else {
        0f
    }

    // 根据百分比选择颜色
    val fillColor = when {
        animatedPercentage > 50f -> fullColor
        animatedPercentage > 20f -> mediumColor
        else -> lowColor
    }

    Box(
        modifier = modifier
            .size(size * scale),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(size * scale)
                .graphicsLayer(alpha = alpha)
        ) {
            val centerX = this.size.width / 2
            val centerY = this.size.width / 2  // 使用正方形中心点

            // 改为相对尺寸计算，确保在任何size下都能正常显示
            val canvasSize = this.size.width
            val strokeWidth = canvasSize * 0.06f  // 线宽占画布的8%
            val padding = canvasSize * 0.08f      // 外边距占画布的8%

            // 圆弧的绘制区域（从左上角到右下角）
            val outerLeft = padding
            val outerTop = padding
            val outerRight = canvasSize - padding
            val outerBottom = canvasSize - padding

            // 内圈位置（外圈向内缩进strokeWidth）
            val innerPadding = padding + strokeWidth * 3.5f
            val innerLeft = innerPadding
            val innerTop = innerPadding
            val innerRight = canvasSize - innerPadding
            val innerBottom = canvasSize - innerPadding

            // 绘制外圈背景圆弧（150度起始，240度扫描）
            drawArc(
                color = arcColor,
                startAngle = startAngle,
                sweepAngle = maxSweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                topLeft = Offset(outerLeft, outerTop),
                size = Size(outerRight - outerLeft, outerBottom - outerTop)
            )

            // 绘制推荐区间（半透明）
            val optimalStartAngle = startAngle + (optimalLowPercent / 100f) * maxSweepAngle
            val optimalSweepAngle =
                ((optimalHighPercent - optimalLowPercent) / 100f) * maxSweepAngle
            if (optimalSweepAngle > 0) {
                drawArc(
                    color = mediumColor.copy(alpha = 0.25f),
                    startAngle = optimalStartAngle,
                    sweepAngle = optimalSweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    topLeft = Offset(outerLeft, outerTop),
                    size = Size(outerRight - outerLeft, outerBottom - outerTop)
                )
            }

            // 绘制进度圆弧
            if (sweepAngle > 0) {
                drawArc(
                    color = fillColor,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    topLeft = Offset(outerLeft, outerTop),
                    size = Size(outerRight - outerLeft, outerBottom - outerTop)
                )
            }

            // 绘制内圈细线（装饰）
            drawArc(
                color = arcColor,
                startAngle = startAngle,
                sweepAngle = maxSweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth / 3, cap = StrokeCap.Round),
                topLeft = Offset(innerLeft, innerTop),
                size = Size(innerRight - innerLeft, innerBottom - innerTop)
            )

            // 绘制刻度线（径向短直线）
            val tickCount = 11
            val outerRadiusValue = (outerRight - outerLeft) / 2
            val tickLength = strokeWidth * 0.5f // 刻度线长度为线宽的50%
            val tickStrokeWidth = canvasSize * 0.01f  // 刻度线粗细为画布的1%

            for (i in 0 until tickCount) {
                val tickAngle = startAngle + (i * maxSweepAngle / (tickCount - 1))

                // 计算刻度外端点（在外圈上）
                val outerTickX =
                    centerX + outerRadiusValue * kotlin.math.cos(Math.toRadians(tickAngle.toDouble()))
                        .toFloat()
                val outerTickY =
                    centerY + outerRadiusValue * kotlin.math.sin(Math.toRadians(tickAngle.toDouble()))
                        .toFloat()

                // 计算刻度内端点（向内缩进 tickLength）
                val innerTickX = centerX + (outerRadiusValue - tickLength) * kotlin.math.cos(
                    Math.toRadians(tickAngle.toDouble())
                ).toFloat()
                val innerTickY = centerY + (outerRadiusValue - tickLength) * kotlin.math.sin(
                    Math.toRadians(tickAngle.toDouble())
                ).toFloat()

                // 绘制刻度线
                drawLine(
                    color = Color.White,
                    start = Offset(outerTickX, outerTickY),
                    end = Offset(innerTickX, innerTickY),
                    strokeWidth = tickStrokeWidth,
                    cap = StrokeCap.Round
                )
            }

            // 计算指针位置（沿内圈轨道移动）
            val pointerAngle = startAngle + sweepAngle
            val innerRadiusValue = (innerRight - innerLeft) / 2

            // 辅助函数：根据角度和半径计算坐标
            fun getArcX(angle: Float, radius: Float): Float {
                return (centerX + radius * kotlin.math.cos(Math.toRadians(angle.toDouble()))).toFloat()
            }

            fun getArcY(angle: Float, radius: Float): Float {
                return (centerY + radius * kotlin.math.sin(Math.toRadians(angle.toDouble()))).toFloat()
            }

            // 在内圈上的圆点坐标
            val cx = getArcX(pointerAngle, innerRadiusValue)
            val cy = getArcY(pointerAngle, innerRadiusValue)

            // 指针元素的相对尺寸
            val pointerDotRadius = canvasSize * 0.025f  // 指针圆点半径为画布的2.5%
            val pointerCenterDotRadius = canvasSize * 0.008f  // 中心小白点半径为画布的0.8%
            val arrowBaseOffset = canvasSize * 0.03f  // 箭头底部偏移为画布的3%

            // 绘制内圈上的指示圆点
            drawCircle(
                color = fillColor,
                radius = pointerDotRadius,
                center = Offset(cx, cy)
            )

            // 三角形箭头指向外侧（缩短长度，不触碰外圈）
            // 计算外圈和内圈之间的距离，箭头长度为这个距离的50%
            val gapBetweenCircles = (outerRight - outerLeft) / 2 - innerRadiusValue
            val arrowLength = gapBetweenCircles * 0.5f

            // 圆外一点的坐标（三角形顶点）
            val outerX = getArcX(pointerAngle, innerRadiusValue + arrowLength)
            val outerY = getArcY(pointerAngle, innerRadiusValue + arrowLength)

            // 控制点1（左侧）
            val co1x = getArcX(pointerAngle - 4f, innerRadiusValue + arrowBaseOffset)
            val co1y = getArcY(pointerAngle - 4f, innerRadiusValue + arrowBaseOffset)

            // 控制点2（右侧）
            val co2x = getArcX(pointerAngle + 4f, innerRadiusValue + arrowBaseOffset)
            val co2y = getArcY(pointerAngle + 4f, innerRadiusValue + arrowBaseOffset)

            // 绘制三角形箭头
            val pointerPath = Path().apply {
                moveTo(co1x, co1y)
                lineTo(co2x, co2y)
                lineTo(outerX, outerY)
                close()
            }
            drawPath(pointerPath, fillColor, style = Fill)

            // 在圆点中心绘制小白点
            drawCircle(
                color = Color.White,
                radius = pointerCenterDotRadius,
                center = Offset(cx, cy)
            )
        }

        // 显示数值（中心位置，弧形仪表盘的视觉中心略向上）
        if (showText) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (size * scale * 0.08f)) // 向上偏移5%，弧形仪表盘的视觉中心
            ) {
                // 根据 showPercentage 决定显示内容
                val displayText = if (showPercentage) {
                    "${percentage.toInt()}"
                } else {
                    "${currentValue.toInt()}"
                }

                Text(
                    text = displayText,
                    style = TextStyle(
                        color = textColor,
                        fontSize = (32 * scale).sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontFamily
                    )
                )

                // 显示自定义单位（百分比模式下不显示单位）
                val displayUnit = if (showPercentage) "" else unit
                if (displayUnit.isNotBlank()) {
                    Text(
                        text = displayUnit,
                        style = TextStyle(
                            color = textColor.copy(alpha = 0.5f),
                            fontSize = (10 * scale).sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = fontFamily
                        )
                    )
                }

                if (description.isNotBlank()) {
                    Text(
                        text = description,
                        style = TextStyle(
                            color = textColor.copy(alpha = 0.6f),
                            fontSize = (9 * scale).sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = fontFamily
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ArcGaugePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("不同状态示例", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // 满状态
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ArcGaugeIndicator(
                value = 100f,
                minValue = 0f,
                maxValue = 100f,
                fullColor = Color(0xFF00A6FF),
                mediumColor = Color(0xFF00A6FF),
                lowColor = Color(0xFF00A6FF),
                size = 150.dp
            )
            Text("满 (100)", fontSize = 14.sp, color = Color.Gray)
        }

        // 良好状态
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ArcGaugeIndicator(
                value = 65f,
                minValue = 0f,
                maxValue = 100f,
                fullColor = Color(0xFF00A6FF),
                mediumColor = Color(0xFF00A6FF),
                lowColor = Color(0xFF00A6FF),
                size = 150.dp
            )
            Text("良好 (65)", fontSize = 14.sp, color = Color.Gray)
        }

        // 中等状态
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ArcGaugeIndicator(
                value = 35f,
                minValue = 0f,
                maxValue = 100f,
                fullColor = Color(0xFF00A6FF),
                mediumColor = Color(0xFFFFCC00),
                lowColor = Color(0xFFFFCC00),
                size = 150.dp
            )
            Text("中等 (35)", fontSize = 14.sp, color = Color.Gray)
        }

        // 低状态
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ArcGaugeIndicator(
                value = 10f,
                minValue = 0f,
                maxValue = 100f,
                fullColor = Color(0xFFFF3B30),
                mediumColor = Color(0xFFFF3B30),
                lowColor = Color(0xFFFF3B30),
                size = 150.dp
            )
            Text("低 (10)", fontSize = 14.sp, color = Color.Gray)
        }
    }
}
