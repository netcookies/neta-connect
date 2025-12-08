package com.neta.widgets.battery

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 电池小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 */
class BatteryWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return BatteryWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-battery",
            version = "1.0.5",
            author = "官方",
            description = "iOS 风格的电池显示小组件",
            minAppVersion = "1.8.9",
            category = "通用"
        )
    }
}
