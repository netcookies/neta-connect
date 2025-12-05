package com.neta.widgets.infochart

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 信息卡片小组件插件
 *
 * 动态加载的插件，显示实时数据值、趋势和统计信息。
 */
class InfoChartWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return InfoChartWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-info-chart",
            version = "1.0.3",
            author = "官方",
            description = "信息卡片，显示实时数值、趋势指示器和统计信息",
            minAppVersion = "1.8.9",
            category = "图表"
        )
    }
}
