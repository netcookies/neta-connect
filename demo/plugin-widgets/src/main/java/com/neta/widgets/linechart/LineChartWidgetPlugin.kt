package com.neta.widgets.linechart

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 折线图小组件插件
 *
 * 动态加载的插件，显示实时数据的折线图。
 */
class LineChartWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return LineChartWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-line-chart",
            version = "1.0.3",
            author = "官方",
            description = "折线图，显示实时数据趋势，支持平滑曲线和区域填充",
            minAppVersion = "2.1.7",
            category = "图表"
        )
    }
}
