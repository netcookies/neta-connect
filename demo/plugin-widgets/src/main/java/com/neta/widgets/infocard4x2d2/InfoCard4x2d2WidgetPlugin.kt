package com.neta.widgets.infocard4x2d2

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 信息卡片小组件插件 (4x2, 2个信息)
 */
class InfoCard4x2d2WidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return InfoCard4x2d2WidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-info-card-4x2d2",
            version = "1.0.1",
            author = "官方",
            description = "信息卡片(4x2)，横向显示2个大数值",
            minAppVersion = "1.8.9",
            category = "信息"
        )
    }
}
