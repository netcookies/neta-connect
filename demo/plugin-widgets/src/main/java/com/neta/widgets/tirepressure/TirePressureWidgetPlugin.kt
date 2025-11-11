package com.neta.widgets.tirepressure

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 胎压温度监测小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 */
class TirePressureWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return TirePressureWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-tire-pressure",
            version = "1.0.7",
            author = "官方",
            description = "显示四轮胎压和温度的监测小组件",
            minAppVersion = "1.8.9",
            category = "车机"
        )
    }
}