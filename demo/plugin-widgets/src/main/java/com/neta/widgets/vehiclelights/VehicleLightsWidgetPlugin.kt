package com.neta.widgets.vehiclelights

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 车辆灯光小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 */
class VehicleLightsWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return VehicleLightsWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-vehicle-lights",
            version = "1.0.3",
            author = "官方",
            description = "显示车辆各种灯光状态的小组件",
            minAppVersion = "2.1.7",
            category = "车辆"
        )
    }
}
