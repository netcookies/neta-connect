package com.neta.widgets.batterydemo

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 电池示例小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 * 用于演示插件开发的示例
 */
class BatteryDemoWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return BatteryDemoWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-battery-demo",
            version = "1.1.6",
            author = "官方",
            description = "显示电池电量的小组件(示例)",
            minAppVersion = "1.8.9",
            category = "示例"
        )
    }
}
