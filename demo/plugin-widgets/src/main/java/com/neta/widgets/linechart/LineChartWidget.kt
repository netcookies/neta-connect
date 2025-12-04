package com.neta.widgets.linechart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.chart.ChartDataPoint
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getChartData
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.toHexString

/**
 * 折线图小组件
 *
 * 显示实时数据的折线图，支持平滑曲线。
 * 适合显示连续变化的数据趋势。
 *
 * 特点：
 * - Canvas 绘制折线图
 * - 支持平滑曲线或直线连接
 * - 可填充曲线下方区域
 * - 自动或手动Y轴范围
 * - 线条颜色和宽度可配置
 * - 显示标题和当前值
 * - 支持网格线显示
 * - 支持数据点标记
 */
object LineChartWidgetSpec : WidgetSpec(
    type = "line_chart",
    displayName = "折线图",
    recommendedGrid = Pair(4, 2),
    icon = Icons.AutoMirrored.Filled.ShowChart,
    color = Color(0xFF4CAF50),

    paramSchema = WidgetParamDesc.buildParams {
        // 数据源配置
        group("数据源") {
            +WidgetParamDesc(
                key = P.DATASOURCE,
                label = "主数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = true
            )
            +WidgetParamDesc(
                key = P.DATASOURCE_MAX_POINTS.key,
                label = "历史点数",
                type = WidgetParamType.INT,
                defaultValue = P.DATASOURCE_MAX_POINTS.default,
                description = "缓冲的历史数据点数量"
            )
            +WidgetParamDesc(
                key = P.DATASOURCE_UPDATE_INTERVAL.key,
                label = "更新间隔(ms)",
                type = WidgetParamType.INT,
                defaultValue = P.DATASOURCE_UPDATE_INTERVAL.default,
                description = "数据更新间隔（毫秒）"
            )
            +WidgetParamDesc(
                key = P.DATASOURCE_FOLLOW_SAMPLE_RATE.key,
                label = "跟随采样率",
                type = WidgetParamType.BOOL,
                defaultValue = P.DATASOURCE_FOLLOW_SAMPLE_RATE.default,
                description = "跟随数据源采样率，忽略更新间隔"
            )
        }

        // 显示配置
        group("显示设置") {
            +WidgetParamDesc(
                key = P.TITLE.key,
                label = "标题",
                type = WidgetParamType.STRING,
                defaultValue = P.TITLE.default,
                description = "图表标题"
            )
            +WidgetParamDesc(
                key = P.UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.UNIT.default,
                description = "数值单位"
            )
            +WidgetParamDesc(
                key = P.DECIMALS.key,
                label = "小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.DECIMALS.default,
                description = "数值显示的小数位数"
            )
            +WidgetParamDesc(
                key = P.SHOW_CURRENT_VALUE.key,
                label = "显示当前值",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_CURRENT_VALUE.default,
                description = "在标题旁显示当前数值"
            )
            +WidgetParamDesc(
                key = P.SHOW_GRID.key,
                label = "显示网格",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_GRID.default,
                description = "显示Y轴网格线"
            )
            +WidgetParamDesc(
                key = P.SHOW_POINTS.key,
                label = "显示数据点",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_POINTS.default,
                description = "在线条上显示数据点标记"
            )
        }

        // 折线图样式
        group("折线图样式") {
            +WidgetParamDesc(
                key = P.LINE_WIDTH.key,
                label = "线条宽度",
                type = WidgetParamType.FLOAT,
                defaultValue = P.LINE_WIDTH.default,
                description = "线条的粗细（dp）"
            )
            +WidgetParamDesc(
                key = P.LINE_COLOR.key,
                label = "线条颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.LINE_COLOR.default.toHexString(),
                description = "线条颜色"
            )
            +WidgetParamDesc(
                key = P.SMOOTH_CURVE.key,
                label = "平滑曲线",
                type = WidgetParamType.BOOL,
                defaultValue = P.SMOOTH_CURVE.default,
                description = "使用平滑曲线连接数据点"
            )
            +WidgetParamDesc(
                key = P.FILL_AREA.key,
                label = "填充区域",
                type = WidgetParamType.BOOL,
                defaultValue = P.FILL_AREA.default,
                description = "填充曲线下方区域"
            )
            +WidgetParamDesc(
                key = P.FILL_COLOR.key,
                label = "填充颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.FILL_COLOR.default.toHexString(),
                description = "填充区域颜色",
                visibleWhen = P.FILL_AREA.key to true
            )
        }

        // 样式配置
        group("样式") {
            +WidgetParamDesc(
                key = P.BG_COLOR.key,
                label = "背景颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.BG_COLOR.default.toHexString(),
                description = "图表背景颜色"
            )
            +WidgetParamDesc(
                key = P.TEXT_COLOR.key,
                label = "文字颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.TEXT_COLOR.default.toHexString(),
                description = "标题和数值文字颜色"
            )
            +WidgetParamDesc(
                key = P.GRID_COLOR.key,
                label = "网格颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.GRID_COLOR.default.toHexString(),
                description = "网格线颜色"
            )
        }

        // Y轴范围
        group("Y轴设置") {
            +WidgetParamDesc(
                key = P.AUTO_SCALE.key,
                label = "自动缩放",
                type = WidgetParamType.BOOL,
                defaultValue = P.AUTO_SCALE.default,
                description = "自动计算Y轴范围"
            )
            +WidgetParamDesc(
                key = P.MIN_VALUE.key,
                label = "最小值",
                type = WidgetParamType.FLOAT,
                defaultValue = P.MIN_VALUE.default,
                description = "Y轴最小值",
                visibleWhen = P.AUTO_SCALE.key to false
            )
            +WidgetParamDesc(
                key = P.MAX_VALUE.key,
                label = "最大值",
                type = WidgetParamType.FLOAT,
                defaultValue = P.MAX_VALUE.default,
                description = "Y轴最大值",
                visibleWhen = P.AUTO_SCALE.key to false
            )
        }
    },

    contentComposable = { LineChartWidgetContent(it) }
) {
    /**
     * 参数定义
     */
    object P {
        // 数据源
        const val DATASOURCE = "datasource"
        val DATASOURCE_MAX_POINTS = ParamDef("datasource_maxPoints", 100)
        val DATASOURCE_UPDATE_INTERVAL = ParamDef("datasource_updateInterval", 1000)
        val DATASOURCE_FOLLOW_SAMPLE_RATE = ParamDef("datasource_followSampleRate", false)

        // 显示设置
        val TITLE = ParamDef("title", "数据趋势")
        val UNIT = ParamDef("unit", "")
        val DECIMALS = ParamDef("decimals", 1)
        val SHOW_CURRENT_VALUE = ParamDef("showCurrentValue", true)
        val SHOW_GRID = ParamDef("showGrid", true)
        val SHOW_POINTS = ParamDef("showPoints", false)

        // 折线图样式
        val LINE_WIDTH = ParamDef("lineWidth", 2f)
        val LINE_COLOR = ParamDef("lineColor", Color(0xFF4CAF50))
        val SMOOTH_CURVE = ParamDef("smoothCurve", true)
        val FILL_AREA = ParamDef("fillArea", true)
        val FILL_COLOR = ParamDef("fillColor", Color(0x334CAF50))

        // 样式
        val BG_COLOR = ParamDef("bgColor", Color(0xFF263238))
        val TEXT_COLOR = ParamDef("textColor", Color(0xFFFFFFFF))
        val GRID_COLOR = ParamDef("gridColor", Color(0x33FFFFFF))

        // Y轴设置
        val AUTO_SCALE = ParamDef("autoScale", true)
        val MIN_VALUE = ParamDef("minValue", 0f)
        val MAX_VALUE = ParamDef("maxValue", 100f)

        // 非参数常量 - 默认尺寸（设计为4x2格子大小：400x200dp）
        val WIDTH = 400.dp
        val HEIGHT = 200.dp
    }
}

/**
 * 折线图内容
 */
@Composable
fun LineChartWidgetContent(config: WidgetConfig) {
    // 读取配置参数
    val title = config.getParam(LineChartWidgetSpec.P.TITLE)
    val unit = config.getParam(LineChartWidgetSpec.P.UNIT)
    val decimals = config.getParam(LineChartWidgetSpec.P.DECIMALS)
    val showCurrentValue = config.getParam(LineChartWidgetSpec.P.SHOW_CURRENT_VALUE)
    val showGrid = config.getParam(LineChartWidgetSpec.P.SHOW_GRID)
    val showPoints = config.getParam(LineChartWidgetSpec.P.SHOW_POINTS)

    val lineWidth = config.getParam(LineChartWidgetSpec.P.LINE_WIDTH)
    val lineColor = config.getParam(LineChartWidgetSpec.P.LINE_COLOR)
    val smoothCurve = config.getParam(LineChartWidgetSpec.P.SMOOTH_CURVE)
    val fillArea = config.getParam(LineChartWidgetSpec.P.FILL_AREA)
    val fillColor = config.getParam(LineChartWidgetSpec.P.FILL_COLOR)

    val bgColor = config.getParam(LineChartWidgetSpec.P.BG_COLOR)
    val textColor = config.getParam(LineChartWidgetSpec.P.TEXT_COLOR)
    val gridColor = config.getParam(LineChartWidgetSpec.P.GRID_COLOR)

    val autoScale = config.getParam(LineChartWidgetSpec.P.AUTO_SCALE)
    val manualMinValue = config.getParam(LineChartWidgetSpec.P.MIN_VALUE)
    val manualMaxValue = config.getParam(LineChartWidgetSpec.P.MAX_VALUE)

    // 应用缩放和透明度
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 获取图表数据
    val chartData = config.getChartData(LineChartWidgetSpec.P.DATASOURCE)
    val currentValue = chartData.lastOrNull()?.value ?: 0f

    // 计算Y轴范围
    val (minValue, maxValue) = remember(chartData, autoScale, manualMinValue, manualMaxValue) {
        if (autoScale && chartData.isNotEmpty()) {
            val dataMin = chartData.minOf { it.value }
            val dataMax = chartData.maxOf { it.value }
            val padding = (dataMax - dataMin) * 0.1f
            Pair(dataMin - padding, dataMax + padding)
        } else {
            Pair(manualMinValue, manualMaxValue)
        }
    }

    // 预计算缩放后的值
    val scaledPadding = 12.dp * scale
    val scaledTitleSize = 16.sp * scale
    val scaledValueSize = 18.sp * scale
    val scaledLineWidth = lineWidth * scale

    Box(
        modifier = Modifier
            .width(LineChartWidgetSpec.P.WIDTH * scale)
            .height(LineChartWidgetSpec.P.HEIGHT * scale)
            .graphicsLayer(alpha = alpha)
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(scaledPadding)
    ) {
        Column(
            modifier = Modifier.matchParentSize()
        ) {
            // 标题行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = scaledTitleSize,
                    color = textColor.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )

                if (showCurrentValue) {
                    Text(
                        text = "%.${decimals}f$unit".format(currentValue),
                        fontSize = scaledValueSize,
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp * scale))

            // 折线图
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    if (chartData.isEmpty()) return@Canvas

                    // 绘制网格线
                    if (showGrid) {
                        val gridLineCount = 5
                        for (i in 0 until gridLineCount) {
                            val y = canvasHeight * (i.toFloat() / (gridLineCount - 1))
                            drawLine(
                                color = gridColor,
                                start = Offset(0f, y),
                                end = Offset(canvasWidth, y),
                                strokeWidth = 1f
                            )
                        }
                    }

                    // 计算数据点坐标
                    val points = chartData.mapIndexed { index, dataPoint ->
                        val x =
                            canvasWidth * (index.toFloat() / (chartData.size - 1).coerceAtLeast(1))
                        val normalizedValue = if (maxValue > minValue) {
                            ((dataPoint.value - minValue) / (maxValue - minValue)).coerceIn(0f, 1f)
                        } else {
                            0f
                        }
                        val y = canvasHeight * (1f - normalizedValue)
                        Offset(x, y)
                    }

                    // 绘制填充区域
                    if (fillArea && points.size >= 2) {
                        val fillPath = Path().apply {
                            moveTo(points.first().x, canvasHeight)
                            lineTo(points.first().x, points.first().y)

                            if (smoothCurve) {
                                drawSmoothPath(points)
                            } else {
                                points.forEach { lineTo(it.x, it.y) }
                            }

                            lineTo(points.last().x, canvasHeight)
                            close()
                        }
                        drawPath(fillPath, fillColor)
                    }

                    // 绘制线条
                    if (points.size >= 2) {
                        val linePath = Path().apply {
                            moveTo(points.first().x, points.first().y)

                            if (smoothCurve) {
                                drawSmoothPath(points)
                            } else {
                                points.forEach { lineTo(it.x, it.y) }
                            }
                        }
                        drawPath(
                            path = linePath,
                            color = lineColor,
                            style = Stroke(
                                width = scaledLineWidth,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }

                    // 绘制数据点
                    if (showPoints) {
                        points.forEach { point ->
                            drawCircle(
                                color = lineColor,
                                radius = scaledLineWidth * 1.5f,
                                center = point
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * 绘制平滑曲线路径（贝塞尔曲线）
 */
private fun Path.drawSmoothPath(points: List<Offset>) {
    if (points.size < 2) return

    for (i in 1 until points.size) {
        val p0 = points[i - 1]
        val p1 = points[i]

        val controlPoint1 = Offset(
            x = p0.x + (p1.x - p0.x) / 3f,
            y = p0.y
        )
        val controlPoint2 = Offset(
            x = p0.x + 2 * (p1.x - p0.x) / 3f,
            y = p1.y
        )

        cubicTo(
            controlPoint1.x, controlPoint1.y,
            controlPoint2.x, controlPoint2.y,
            p1.x, p1.y
        )
    }
}

/**
 * Preview - 折线图预览
 */
@Preview
@Composable
fun LineChartPreview() {
    // 模拟图表数据 - 平滑波动
    val mockChartData1 = List(50) { index ->
        ChartDataPoint(
            timestamp = System.currentTimeMillis() - (50 - index) * 1000L,
            value = 60f + (kotlin.math.sin(index * 0.3) * 15f).toFloat()
        )
    }

    // 模拟图表数据 - 持续上升
    val mockChartData2 = List(80) { index ->
        ChartDataPoint(
            timestamp = System.currentTimeMillis() - (80 - index) * 1000L,
            value = 20f + (index * 0.8f) + (kotlin.math.sin(index * 0.5) * 5f).toFloat()
        )
    }

    Column(
        modifier = Modifier
            .width(600.dp)
            .background(Color(0xFFF2F2F7))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("折线图预览", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        // 平滑曲线 + 填充
        Box(
            modifier = Modifier
                .width(550.dp)
                .height(200.dp)
        ) {
            LineChartPreviewCard(
                title = "电池电压",
                unit = "V",
                chartData = mockChartData1,
                lineColor = Color(0xFF4CAF50),
                fillColor = Color(0x334CAF50),
                smoothCurve = true,
                fillArea = true,
                showPoints = false
            )
        }

        // 直线连接 + 数据点
        Box(
            modifier = Modifier
                .width(550.dp)
                .height(200.dp)
        ) {
            LineChartPreviewCard(
                title = "功率趋势",
                unit = "kW",
                chartData = mockChartData2,
                lineColor = Color(0xFFFF9800),
                fillColor = Color(0x00000000),
                smoothCurve = false,
                fillArea = false,
                showPoints = true
            )
        }
    }
}

@Composable
private fun LineChartPreviewCard(
    title: String,
    unit: String,
    chartData: List<ChartDataPoint>,
    lineColor: Color,
    fillColor: Color,
    smoothCurve: Boolean,
    fillArea: Boolean,
    showPoints: Boolean
) {
    val bgColor = Color(0xFF263238)
    val textColor = Color.White
    val gridColor = Color(0x33FFFFFF)
    val currentValue = chartData.lastOrNull()?.value ?: 0f
    val decimals = 1
    val lineWidth = 2f

    // 计算Y轴范围
    val minValue = chartData.minOfOrNull { it.value } ?: 0f
    val maxValue = chartData.maxOfOrNull { it.value } ?: 100f
    val padding = (maxValue - minValue) * 0.1f
    val yMin = minValue - padding
    val yMax = maxValue + padding

    Box(
        modifier = Modifier
            .width(550.dp)
            .height(200.dp)
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.matchParentSize()
        ) {
            // 标题行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = textColor.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "%.${decimals}f$unit".format(currentValue),
                    fontSize = 16.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 折线图
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    if (chartData.isEmpty()) return@Canvas

                    // 绘制网格线
                    val gridLineCount = 5
                    for (i in 0 until gridLineCount) {
                        val y = canvasHeight * (i.toFloat() / (gridLineCount - 1))
                        drawLine(
                            color = gridColor,
                            start = Offset(0f, y),
                            end = Offset(canvasWidth, y),
                            strokeWidth = 1f
                        )
                    }

                    // 计算数据点坐标
                    val points = chartData.mapIndexed { index, dataPoint ->
                        val x =
                            canvasWidth * (index.toFloat() / (chartData.size - 1).coerceAtLeast(1))
                        val normalizedValue = if (yMax > yMin) {
                            ((dataPoint.value - yMin) / (yMax - yMin)).coerceIn(0f, 1f)
                        } else {
                            0f
                        }
                        val y = canvasHeight * (1f - normalizedValue)
                        Offset(x, y)
                    }

                    // 绘制填充区域
                    if (fillArea && points.size >= 2 && fillColor.alpha > 0f) {
                        val fillPath = Path().apply {
                            moveTo(points.first().x, canvasHeight)
                            lineTo(points.first().x, points.first().y)

                            if (smoothCurve) {
                                drawSmoothPath(points)
                            } else {
                                points.forEach { lineTo(it.x, it.y) }
                            }

                            lineTo(points.last().x, canvasHeight)
                            close()
                        }
                        drawPath(fillPath, fillColor)
                    }

                    // 绘制线条
                    if (points.size >= 2) {
                        val linePath = Path().apply {
                            moveTo(points.first().x, points.first().y)

                            if (smoothCurve) {
                                drawSmoothPath(points)
                            } else {
                                points.forEach { lineTo(it.x, it.y) }
                            }
                        }
                        drawPath(
                            path = linePath,
                            color = lineColor,
                            style = Stroke(
                                width = lineWidth,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }

                    // 绘制数据点
                    if (showPoints) {
                        points.forEach { point ->
                            drawCircle(
                                color = lineColor,
                                radius = lineWidth * 1.5f,
                                center = point
                            )
                        }
                    }
                }
            }
        }
    }
}
