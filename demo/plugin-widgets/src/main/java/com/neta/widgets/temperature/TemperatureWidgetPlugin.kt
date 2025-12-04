package com.neta.widgets.temperature

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 温度计小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 */
class TemperatureWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return TemperatureWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-temperature",
            version = "1.0.4",
            author = "官方",
            description = "温度计样式的数值显示小组件",
            minAppVersion = "1.8.9",
            category = "通用"
        )
    }
}
