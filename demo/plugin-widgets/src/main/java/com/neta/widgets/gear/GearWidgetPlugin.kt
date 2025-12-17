package com.neta.widgets.gear

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 档位显示小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 */
class GearWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return GearWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-gear",
            version = "1.0.2",
            author = "官方",
            description = "横向显示汽车档位(P/R/N/D/S)，当前档位高亮显示",
            minAppVersion = "1.8.9",
            category = "车辆信息"
        )
    }
}
