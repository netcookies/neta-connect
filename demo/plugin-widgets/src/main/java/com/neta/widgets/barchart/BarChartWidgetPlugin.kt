package com.neta.widgets.barchart

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 柱状图小组件插件
 *
 * 动态加载的插件，显示实时数据的柱状图。
 */
class BarChartWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return BarChartWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-bar-chart",
            version = "1.0.3",
            author = "官方",
            description = "柱状图，显示实时数据趋势，支持最多96个柱子",
            minAppVersion = "2.1.7",
            category = "图表"
        )
    }
}
