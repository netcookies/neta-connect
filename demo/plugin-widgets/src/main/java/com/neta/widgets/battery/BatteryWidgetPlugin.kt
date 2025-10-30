package com.neta.widgets.battery

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 电池小组件插件
 * 这是一个独立编译的插件模块,可以被动态加载
 */
class BatteryWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return SimpleBatteryWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-battery-demo",
            version = "1.0.3",
            author = "官方",
            description = "显示电池电量的小组件(示例)",
            minAppVersion = "1.8.2"
        )
    }
}
