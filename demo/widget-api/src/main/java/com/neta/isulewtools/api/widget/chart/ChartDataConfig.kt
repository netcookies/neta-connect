package com.neta.isulewtools.api.widget.chart

/**
 * 图表数据配置抽象
 *
 * 定义图表数据的缓冲和更新策略。
 * 该配置会被 app 层的 ChartDataManager 使用，
 * 动态插件通过注入机制获取配置实例。
 *
 * @property maxDataPoints 最大数据点数量（环形缓冲区大小），默认100
 * @property updateInterval 更新间隔（毫秒），默认1000ms（1秒）
 * @property autoScale 是否自动缩放Y轴，默认true
 * @property minValue 手动设置Y轴最小值（仅当 autoScale=false 时有效）
 * @property maxValue 手动设置Y轴最大值（仅当 autoScale=false 时有效）
 * @property followSampleRate 是否跟随数据源采样率（忽略 updateInterval），默认false
 */
data class ChartDataConfig(
    val maxDataPoints: Int = 100,
    val updateInterval: Long = 1000L,
    val autoScale: Boolean = true,
    val minValue: Float? = null,
    val maxValue: Float? = null,
    val followSampleRate: Boolean = false
)
