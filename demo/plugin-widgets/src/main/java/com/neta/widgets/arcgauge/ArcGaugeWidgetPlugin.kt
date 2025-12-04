package com.neta.widgets.arcgauge

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 弧形仪表盘小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 */
class ArcGaugeWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return ArcGaugeWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-arc-gauge",
            version = "1.0.5",
            author = "官方",
            description = "弧形仪表盘样式的数值显示小组件",
            minAppVersion = "1.8.9",
            category = "车机"
        )
    }
}
