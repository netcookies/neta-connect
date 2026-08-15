package com.neta.widgets.systemperformance

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 系统性能小组件插件。
 */
class SystemPerformanceWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return SystemPerformanceWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-system-performance",
            version = "1.0.1",
            author = "官方",
            description = "60dp 横向系统性能长条，显示 CPU、内存、磁盘 IO 和 U 盘 IO",
            minAppVersion = "2.1.7",
            category = "系统"
        )
    }
}
