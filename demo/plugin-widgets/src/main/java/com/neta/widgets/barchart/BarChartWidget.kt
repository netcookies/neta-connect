package com.neta.widgets.barchart

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
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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
import kotlin.random.Random

/**
 * 柱状图小组件
 *
 * 显示实时数据的柱状图，支持最多96个柱子。
 * 适合显示趋势和比较不同时间点的数据。
 *
 * 特点：
 * - Canvas 绘制柱状图
 * - 可配置柱子数量（默认24）
 * - 自动或手动Y轴范围
 * - 柱子颜色可配置
 * - 显示标题和当前值
 * - 支持网格线显示
 */
object BarChartWidgetSpec : WidgetSpec(
    type = "bar_chart",
    displayName = "柱状图",
    recommendedGrid = Pair(4, 2),
    icon = Icons.Filled.BarChart,
    color = Color(0xFF2196F3),

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
                description = "缓冲的历史数据点数量（建议24-96）"
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
        }

        // 柱状图样式
        group("柱状图样式") {
            +WidgetParamDesc(
                key = P.BAR_COUNT.key,
                label = "柱子数量",
                type = WidgetParamType.INT,
                defaultValue = P.BAR_COUNT.default,
                description = "显示的柱子数量（从最新数据开始）"
            )
            +WidgetParamDesc(
                key = P.BAR_SPACING.key,
                label = "柱子间距",
                type = WidgetParamType.FLOAT,
                defaultValue = P.BAR_SPACING.default,
                description = "柱子之间的间距比例（0-1）"
            )
            +WidgetParamDesc(
                key = P.BAR_COLOR.key,
                label = "柱子颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.BAR_COLOR.default.toHexString(),
                description = "柱子填充颜色"
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

    contentComposable = { BarChartWidgetContent(it) }
) {
    /**
     * 参数定义
     */
    object P {
        // 数据源
        const val DATASOURCE = "datasource"
        val DATASOURCE_MAX_POINTS = ParamDef("datasource_maxPoints", 48)
        val DATASOURCE_UPDATE_INTERVAL = ParamDef("datasource_updateInterval", 1000)
        val DATASOURCE_FOLLOW_SAMPLE_RATE = ParamDef("datasource_followSampleRate", false)

        // 显示设置
        val TITLE = ParamDef("title", "数据趋势")
        val UNIT = ParamDef("unit", "")
        val DECIMALS = ParamDef("decimals", 1)
        val SHOW_CURRENT_VALUE = ParamDef("showCurrentValue", true)
        val SHOW_GRID = ParamDef("showGrid", true)

        // 柱状图样式
        val BAR_COUNT = ParamDef("barCount", 24)
        val BAR_SPACING = ParamDef("barSpacing", 0.2f)
        val BAR_COLOR = ParamDef("barColor", Color(0xFF2196F3))

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
 * 柱状图内容
 */
@Composable
fun BarChartWidgetContent(config: WidgetConfig) {
    // 读取配置参数
    val title = config.getParam(BarChartWidgetSpec.P.TITLE)
    val unit = config.getParam(BarChartWidgetSpec.P.UNIT)
    val decimals = config.getParam(BarChartWidgetSpec.P.DECIMALS)
    val showCurrentValue = config.getParam(BarChartWidgetSpec.P.SHOW_CURRENT_VALUE)
    val showGrid = config.getParam(BarChartWidgetSpec.P.SHOW_GRID)

    val barCount = config.getParam(BarChartWidgetSpec.P.BAR_COUNT).coerceIn(1, 96)
    val barSpacing = config.getParam(BarChartWidgetSpec.P.BAR_SPACING).coerceIn(0f, 1f)
    val barColor = config.getParam(BarChartWidgetSpec.P.BAR_COLOR)

    val bgColor = config.getParam(BarChartWidgetSpec.P.BG_COLOR)
    val textColor = config.getParam(BarChartWidgetSpec.P.TEXT_COLOR)
    val gridColor = config.getParam(BarChartWidgetSpec.P.GRID_COLOR)

    val autoScale = config.getParam(BarChartWidgetSpec.P.AUTO_SCALE)
    val manualMinValue = config.getParam(BarChartWidgetSpec.P.MIN_VALUE)
    val manualMaxValue = config.getParam(BarChartWidgetSpec.P.MAX_VALUE)

    // 应用缩放和透明度
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 获取图表数据
    val chartData = config.getChartData(BarChartWidgetSpec.P.DATASOURCE)
    val currentValue = chartData.lastOrNull()?.value ?: 0f

    // 获取最近的N个数据点
    val displayData = remember(chartData, barCount) {
        chartData.takeLast(barCount)
    }

    // 计算Y轴范围
    val (minValue, maxValue) = remember(displayData, autoScale, manualMinValue, manualMaxValue) {
        if (autoScale && displayData.isNotEmpty()) {
            val dataMin = displayData.minOf { it.value }
            val dataMax = displayData.maxOf { it.value }
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

    Box(
        modifier = Modifier
            .width(BarChartWidgetSpec.P.WIDTH * scale)
            .height(BarChartWidgetSpec.P.HEIGHT * scale)
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

            // 柱状图
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    if (displayData.isEmpty()) return@Canvas

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

                    // 计算柱子宽度
                    val totalBars = displayData.size.coerceAtLeast(1)
                    val barWidth = canvasWidth / totalBars
                    val spacing = barWidth * barSpacing
                    val effectiveBarWidth = barWidth - spacing

                    // 绘制柱子
                    displayData.forEachIndexed { index, dataPoint ->
                        val normalizedValue = if (maxValue > minValue) {
                            ((dataPoint.value - minValue) / (maxValue - minValue)).coerceIn(0f, 1f)
                        } else {
                            0f
                        }

                        val barHeight = canvasHeight * normalizedValue
                        val x = index * barWidth + spacing / 2
                        val y = canvasHeight - barHeight

                        drawRect(
                            color = barColor,
                            topLeft = Offset(x, y),
                            size = Size(effectiveBarWidth, barHeight)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Preview - 柱状图预览
 */
@Preview
@Composable
fun BarChartPreview() {
    // 模拟图表数据
    val mockChartData1 = List(24) { index ->
        ChartDataPoint(
            timestamp = System.currentTimeMillis() - (24 - index) * 1000L,
            value = 50f + (kotlin.math.sin(index * 0.5) * 20f).toFloat()
        )
    }

    val mockChartData2 = List(48) { index ->
        ChartDataPoint(
            timestamp = System.currentTimeMillis() - (48 - index) * 1000L,
            value = 30f + (index * 1.2f) + (Random.nextFloat() * 10f)
        )
    }

    Column(
        modifier = Modifier
            .width(600.dp)
            .background(Color(0xFFF2F2F7))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("柱状图预览", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        // 24个柱子
        Box(
            modifier = Modifier
                .width(550.dp)
                .height(200.dp)
        ) {
            BarChartPreviewCard(
                title = "速度变化",
                unit = "km/h",
                chartData = mockChartData1,
                barCount = 24,
                showGrid = true
            )
        }

        // 48个柱子
        Box(
            modifier = Modifier
                .width(550.dp)
                .height(200.dp)
        ) {
            BarChartPreviewCard(
                title = "功率输出",
                unit = "kW",
                chartData = mockChartData2,
                barCount = 48,
                showGrid = true,
                barColor = Color(0xFFFF9800)
            )
        }
    }
}

@Composable
private fun BarChartPreviewCard(
    title: String,
    unit: String,
    chartData: List<ChartDataPoint>,
    barCount: Int,
    showGrid: Boolean,
    barColor: Color = Color(0xFF2196F3)
) {
    val bgColor = Color(0xFF263238)
    val textColor = Color.White
    val gridColor = Color(0x33FFFFFF)
    val currentValue = chartData.lastOrNull()?.value ?: 0f
    val decimals = 1

    // 获取最近的N个数据点
    val displayData = chartData.takeLast(barCount)

    // 计算Y轴范围
    val minValue = displayData.minOfOrNull { it.value } ?: 0f
    val maxValue = displayData.maxOfOrNull { it.value } ?: 100f
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

            // 柱状图
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    if (displayData.isEmpty()) return@Canvas

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

                    // 计算柱子宽度
                    val totalBars = displayData.size.coerceAtLeast(1)
                    val barWidth = canvasWidth / totalBars
                    val spacing = barWidth * 0.2f
                    val effectiveBarWidth = barWidth - spacing

                    // 绘制柱子
                    displayData.forEachIndexed { index, dataPoint ->
                        val normalizedValue = if (yMax > yMin) {
                            ((dataPoint.value - yMin) / (yMax - yMin)).coerceIn(0f, 1f)
                        } else {
                            0f
                        }

                        val barHeight = canvasHeight * normalizedValue
                        val x = index * barWidth + spacing / 2
                        val y = canvasHeight - barHeight

                        drawRect(
                            color = barColor,
                            topLeft = Offset(x, y),
                            size = Size(effectiveBarWidth, barHeight)
                        )
                    }
                }
            }
        }
    }
}
