package com.neta.widgets.infocard4x3d4

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 信息卡片小组件插件 (4x3, 4个信息)
 */
class InfoCard4x3d4WidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return InfoCard4x3d4WidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-info-card-4x3d4",
            version = "1.0.3",
            author = "官方",
            description = "信息卡片(4x3)，以2x2网格显示4个信息",
            minAppVersion = "1.8.9",
            category = "信息"
        )
    }
}
