package com.neta.isulewtools.api.widget.chart

import androidx.compose.ui.geometry.Offset

/**
 * 图表坐标映射工具
 *
 * 用于将数据值映射到Canvas像素坐标。
 * 支持自动缩放和手动Y轴范围设置。
 */
class CoordinateMapper(
    private val canvasWidth: Float,
    private val canvasHeight: Float,
    private val dataPoints: List<ChartDataPoint>,
    private val autoScale: Boolean = true,
    private val manualMinY: Float? = null,
    private val manualMaxY: Float? = null,
    private val paddingTop: Float = 0f,
    private val paddingBottom: Float = 0f,
    private val paddingLeft: Float = 0f,
    private val paddingRight: Float = 0f
) {
    private val drawableWidth = canvasWidth - paddingLeft - paddingRight
    private val drawableHeight = canvasHeight - paddingTop - paddingBottom

    // 计算Y轴范围
    private val yRange: Pair<Float, Float> by lazy {
        if (autoScale) {
            if (dataPoints.isEmpty()) {
                0f to 100f
            } else {
                val min = dataPoints.minOf { it.value }
                val max = dataPoints.maxOf { it.value }
                // 添加10%的边距使图表更美观
                val margin = (max - min) * 0.1f
                (min - margin) to (max + margin)
            }
        } else {
            (manualMinY ?: 0f) to (manualMaxY ?: 100f)
        }
    }

    val minY: Float get() = yRange.first
    val maxY: Float get() = yRange.second

    /**
     * 将数据点索引和值映射到Canvas坐标
     *
     * @param index 数据点索引
     * @param value 数据点值
     * @return Canvas坐标（Offset）
     */
    fun mapToCanvas(index: Int, value: Float): Offset {
        val dataCount = dataPoints.size.coerceAtLeast(1)

        // X坐标：线性分布
        val x = paddingLeft + (index.toFloat() / (dataCount - 1).coerceAtLeast(1)) * drawableWidth

        // Y坐标：归一化到0-1范围，然后映射到canvas高度（翻转Y轴，因为Canvas的Y轴向下）
        val normalizedValue = ((value - minY) / (maxY - minY)).coerceIn(0f, 1f)
        val y = paddingTop + drawableHeight * (1f - normalizedValue)

        return Offset(x, y)
    }

    /**
     * 将数据点映射到Canvas坐标
     */
    fun mapToCanvas(point: ChartDataPoint, index: Int): Offset {
        return mapToCanvas(index, point.value)
    }

    /**
     * 获取所有数据点的Canvas坐标列表
     */
    fun mapAllToCanvas(): List<Offset> {
        return dataPoints.mapIndexed { index, point ->
            mapToCanvas(index, point.value)
        }
    }

    /**
     * 计算网格线的Y坐标（用于绘制水平网格线）
     *
     * @param gridLineCount 网格线数量
     * @return Y坐标列表和对应的数值标签
     */
    fun calculateGridLines(gridLineCount: Int = 5): List<Pair<Float, Float>> {
        return (0..gridLineCount).map { i ->
            val ratio = i.toFloat() / gridLineCount
            val y = paddingTop + drawableHeight * ratio
            val value = maxY - (maxY - minY) * ratio
            y to value
        }
    }
}
