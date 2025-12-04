package com.neta.isulewtools.api.widget.chart

/**
 * 图表数据点抽象
 *
 * 用于在 widget-api 层定义图表数据的统一格式，
 * 动态插件通过数据注入机制获取该类型的数据列表。
 *
 * @property timestamp 数据点的时间戳（毫秒）
 * @property value 数据点的数值
 */
data class ChartDataPoint(
    val timestamp: Long,
    val value: Float
)
