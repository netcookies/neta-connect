package com.neta.widgets.infochart

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.TrendingDown
import androidx.compose.material.icons.automirrored.outlined.TrendingFlat
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

/**
 * 信息卡片小组件
 *
 * 显示实时数据值、趋势指示器、统计信息（最小值/最大值/平均值）。
 * 支持阈值警告颜色变化。
 *
 * 特点：
 * - 纯 Compose 布局，无 Canvas 绘制
 * - 大字号数值显示
 * - 趋势计算（基于最近N个数据点）
 * - 阈值警告
 * - 最值和平均值统计
 */
object InfoChartWidgetSpec : WidgetSpec(
    type = "info_chart",
    displayName = "信息卡片",
    recommendedGrid = Pair(2, 2),
    icon = Icons.Outlined.Info,
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
                description = "卡片标题"
            )
            +WidgetParamDesc(
                key = P.ICON.key,
                label = "图标",
                type = WidgetParamType.ICON,
                defaultValue = P.ICON.default,
                description = "卡片图标"
            )
            +WidgetParamDesc(
                key = P.UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.UNIT.default,
                description = "数值单位（如 km/h、°C）"
            )
            +WidgetParamDesc(
                key = P.DECIMALS.key,
                label = "小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.DECIMALS.default,
                description = "数值显示的小数位数"
            )
            +WidgetParamDesc(
                key = P.SHOW_TREND.key,
                label = "显示趋势",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_TREND.default,
                description = "显示趋势指示器（上升/下降/平稳）"
            )
            +WidgetParamDesc(
                key = P.SHOW_MIN_MAX.key,
                label = "显示最值",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_MIN_MAX.default,
                description = "显示最小值和最大值"
            )
            +WidgetParamDesc(
                key = P.SHOW_AVERAGE.key,
                label = "显示平均值",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_AVERAGE.default,
                description = "显示平均值"
            )
        }

        // 样式配置
        group("样式") {
            +WidgetParamDesc(
                key = P.BG_COLOR.key,
                label = "背景颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.BG_COLOR.default.toHexString(),
                description = "卡片背景颜色"
            )
            +WidgetParamDesc(
                key = P.VALUE_COLOR.key,
                label = "数值颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.VALUE_COLOR.default.toHexString(),
                description = "主数值颜色"
            )
            +WidgetParamDesc(
                key = P.FONT_SIZE.key,
                label = "字体大小",
                type = WidgetParamType.FLOAT,
                defaultValue = P.FONT_SIZE.default,
                description = "主数值字体大小（sp）"
            )
        }

        // 阈值警告
        group("阈值警告") {
            +WidgetParamDesc(
                key = P.ENABLE_WARNING.key,
                label = "启用警告",
                type = WidgetParamType.BOOL,
                defaultValue = P.ENABLE_WARNING.default,
                description = "启用阈值警告功能"
            )
            +WidgetParamDesc(
                key = P.WARNING_MIN.key,
                label = "警告下限",
                type = WidgetParamType.FLOAT,
                defaultValue = P.WARNING_MIN.default,
                description = "低于此值时显示警告",
                visibleWhen = P.ENABLE_WARNING.key to true
            )
            +WidgetParamDesc(
                key = P.WARNING_MAX.key,
                label = "警告上限",
                type = WidgetParamType.FLOAT,
                defaultValue = P.WARNING_MAX.default,
                description = "高于此值时显示警告",
                visibleWhen = P.ENABLE_WARNING.key to true
            )
            +WidgetParamDesc(
                key = P.WARNING_COLOR.key,
                label = "警告颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.WARNING_COLOR.default.toHexString(),
                description = "警告状态下的数值颜色",
                visibleWhen = P.ENABLE_WARNING.key to true
            )
        }
    },

    contentComposable = { InfoChartWidgetContent(it) }
) {
    /**
     * 参数定义
     */
    object P {
        // 数据源 - DATA_SOURCE 类型只定义 key
        const val DATASOURCE = "datasource"
        val DATASOURCE_MAX_POINTS = ParamDef("datasource_maxPoints", 100)
        val DATASOURCE_UPDATE_INTERVAL = ParamDef("datasource_updateInterval", 1000)
        val DATASOURCE_FOLLOW_SAMPLE_RATE = ParamDef("datasource_followSampleRate", false)

        // 显示设置
        val TITLE = ParamDef("title", "传感器")
        val ICON = ParamDef("icon", "Info")
        val UNIT = ParamDef("unit", "")
        val DECIMALS = ParamDef("decimals", 1)
        val SHOW_TREND = ParamDef("showTrend", true)
        val SHOW_MIN_MAX = ParamDef("showMinMax", true)
        val SHOW_AVERAGE = ParamDef("showAverage", false)

        // 样式
        val BG_COLOR = ParamDef("bgColor", Color(0xFF263238))
        val VALUE_COLOR = ParamDef("valueColor", Color(0xFFFFFFFF))
        val FONT_SIZE = ParamDef("fontSize", 48f)

        // 阈值警告
        val ENABLE_WARNING = ParamDef("enableWarning", false)
        val WARNING_MIN = ParamDef("warningMin", 0f)
        val WARNING_MAX = ParamDef("warningMax", 100f)
        val WARNING_COLOR = ParamDef("warningColor", Color(0xFFF44336))

        // 非参数常量 - 默认尺寸（设计为2x2格子大小：200x200dp）
        val WIDTH = 200.dp
        val HEIGHT = 200.dp
    }
}

/**
 * 信息卡片内容
 */
@Composable
fun InfoChartWidgetContent(config: WidgetConfig) {
    // 读取配置参数
    val title = config.getParam(InfoChartWidgetSpec.P.TITLE)
    val unit = config.getParam(InfoChartWidgetSpec.P.UNIT)
    val decimals = config.getParam(InfoChartWidgetSpec.P.DECIMALS)
    val showTrend = config.getParam(InfoChartWidgetSpec.P.SHOW_TREND)
    val showMinMax = config.getParam(InfoChartWidgetSpec.P.SHOW_MIN_MAX)
    val showAverage = config.getParam(InfoChartWidgetSpec.P.SHOW_AVERAGE)

    // 样式配置
    val bgColor = config.getParam(InfoChartWidgetSpec.P.BG_COLOR)
    val valueColor = config.getParam(InfoChartWidgetSpec.P.VALUE_COLOR)
    val fontSize = config.getParam(InfoChartWidgetSpec.P.FONT_SIZE)

    // 阈值配置
    val enableWarning = config.getParam(InfoChartWidgetSpec.P.ENABLE_WARNING)
    val warningMin = config.getParam(InfoChartWidgetSpec.P.WARNING_MIN)
    val warningMax = config.getParam(InfoChartWidgetSpec.P.WARNING_MAX)
    val warningColor = config.getParam(InfoChartWidgetSpec.P.WARNING_COLOR)

    // 获取图表数据
    val chartData = config.getChartData(InfoChartWidgetSpec.P.DATASOURCE)
    val currentValue = chartData.lastOrNull()?.value ?: 0f

    // 计算统计值
    val minValue = chartData.minOfOrNull { it.value }
    val maxValue = chartData.maxOfOrNull { it.value }
    val avgValue = remember(chartData) {
        if (chartData.isNotEmpty()) {
            chartData.map { it.value }.average().toFloat()
        } else 0f
    }

    // 计算趋势（基于最近10个数据点）
    val trend = remember(chartData) {
        if (chartData.size < 2) {
            0f
        } else {
            val recentData = chartData.takeLast(10.coerceAtMost(chartData.size))
            val first = recentData.first().value
            val last = recentData.last().value
            if (first == 0f) {
                0f
            } else {
                ((last - first) / first * 100).coerceIn(-100f, 100f)
            }
        }
    }

    // 检查是否需要警告
    val isWarning = enableWarning && (currentValue < warningMin || currentValue > warningMax)
    val displayColor = if (isWarning) warningColor else valueColor

    // 应用缩放和透明度
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 渲染卡片
    Box(
        modifier = Modifier
            .width(InfoChartWidgetSpec.P.WIDTH * scale)
            .height(InfoChartWidgetSpec.P.HEIGHT * scale)
            .graphicsLayer(alpha = alpha)
            .background(bgColor, RoundedCornerShape((12 * scale).dp))
            .padding((16 * scale).dp)
    ) {
        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 标题行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = (16 * scale).sp,
                    color = valueColor.copy(alpha = 0.7f)
                )

                // 图标（使用配置的图标或默认图标）
                val iconVector = remember(config.getParam(InfoChartWidgetSpec.P.ICON)) {
                    // 这里可以根据 icon 参数选择不同的图标
                    // 暂时使用默认图标
                    Icons.Outlined.Info
                }

                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = displayColor,
                    modifier = Modifier.size((28 * scale).dp)
                )
            }

            // 主数值（居中）
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "%.${decimals}f".format(currentValue),
                    fontSize = (fontSize * scale).sp,
                    color = displayColor,
                    fontWeight = FontWeight.Bold
                )
            }

            // 底部信息行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // 左侧：统计信息
                Column {
                    if (showMinMax && minValue != null && maxValue != null) {
                        Text(
                            text = "最小: %.${decimals}f".format(minValue),
                            fontSize = (14 * scale).sp,
                            color = valueColor.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "最大: %.${decimals}f".format(maxValue),
                            fontSize = (14 * scale).sp,
                            color = valueColor.copy(alpha = 0.6f)
                        )
                    }
                    if (showAverage) {
                        Text(
                            text = "平均: %.${decimals}f".format(avgValue),
                            fontSize = (14 * scale).sp,
                            color = valueColor.copy(alpha = 0.6f)
                        )
                    }
                }

                // 右侧：单位和趋势
                Column(horizontalAlignment = Alignment.End) {
                    if (unit.isNotEmpty()) {
                        Text(
                            text = unit,
                            fontSize = (16 * scale).sp,
                            color = valueColor.copy(alpha = 0.8f),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (showTrend) {
                        TrendIndicator(trend = trend, scale = scale)
                    }
                }
            }
        }
    }
}

/**
 * 趋势指示器
 *
 * 显示数据变化趋势（上升/下降/平稳）和百分比
 * @param trend 趋势百分比
 * @param scale 缩放比例
 */
@Composable
fun TrendIndicator(trend: Float, scale: Float = 1f) {
    val (icon, color) = when {
        trend > 1f -> Icons.AutoMirrored.Outlined.TrendingUp to Color(0xFF4CAF50)
        trend < -1f -> Icons.AutoMirrored.Outlined.TrendingDown to Color(0xFFF44336)
        else -> Icons.AutoMirrored.Outlined.TrendingFlat to Color(0xFF9E9E9E)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size((18 * scale).dp),
            tint = color
        )
        Spacer(modifier = Modifier.width((4 * scale).dp))
        Text(
            text = "${if (trend > 0) "+" else ""}%.1f%%".format(trend),
            fontSize = (14 * scale).sp,
            color = color
        )
    }
}

/**
 * Preview - 信息卡片预览
 */
@Preview
@Composable
fun InfoChartPreview() {
    // 模拟图表数据
    val mockChartData = List(50) { index ->
        ChartDataPoint(
            timestamp = System.currentTimeMillis() - (50 - index) * 1000L,
            value = 65f + (kotlin.math.sin(index * 0.3) * 15f).toFloat()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("信息卡片预览", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        // 正常状态
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        ) {
            InfoChartPreviewCard(
                title = "车速",
                unit = "km/h",
                currentValue = 80f,
                chartData = mockChartData,
                minValue = 50f,
                maxValue = 95f,
                avgValue = 72.5f
            )
        }

        // 警告状态
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        ) {
            InfoChartPreviewCard(
                title = "电池温度",
                unit = "°C",
                currentValue = 105f,
                chartData = mockChartData.map { it.copy(value = it.value + 30f) },
                minValue = 85f,
                maxValue = 110f,
                avgValue = 95f,
                isWarning = true
            )
        }
    }
}

@Composable
private fun InfoChartPreviewCard(
    title: String,
    unit: String,
    currentValue: Float,
    chartData: List<ChartDataPoint>,
    minValue: Float,
    maxValue: Float,
    avgValue: Float,
    isWarning: Boolean = false
) {
    val bgColor = Color(0xFF263238)
    val valueColor = if (isWarning) Color(0xFFF44336) else Color.White
    val decimals = 1

    // 计算趋势
    val trend = if (chartData.size >= 2) {
        val recentData = chartData.takeLast(10)
        val first = recentData.first().value
        val last = recentData.last().value
        if (first != 0f) ((last - first) / first * 100).coerceIn(-100f, 100f) else 0f
    } else 0f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 标题行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )

                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = valueColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            // 主数值
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "%.${decimals}f".format(currentValue),
                    fontSize = 48.sp,
                    color = valueColor,
                    fontWeight = FontWeight.Bold
                )
            }

            // 底部信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "最小: %.${decimals}f".format(minValue),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "最大: %.${decimals}f".format(maxValue),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = unit,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Medium
                    )
                    TrendIndicator(trend = trend)
                }
            }
        }
    }
}
