package com.neta.widgets.infocard4x3d3

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 信息卡片小组件插件 (4x3, 3个信息)
 */
class InfoCard4x3d3WidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return InfoCard4x3d3WidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-info-card-4x3d3",
            version = "1.0.2",
            author = "官方",
            description = "信息卡片(4x3)，显示1个大数值和2个小信息",
            minAppVersion = "1.8.9",
            category = "信息"
        )
    }
}
