package com.neta.widgets.progressbar

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 进度条小组件插件
 * 这是一个独立编译的插件模块，可以被动态加载
 */
class ProgressBarWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return ProgressBarWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-progress-bar",
            version = "1.0.2",
            author = "官方",
            description = "可横向或纵向显示的进度条小组件",
            minAppVersion = "1.8.9",
            category = "通用"
        )
    }
}
