package com.neta.widgets.minicard

import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.isulewtools.api.widget.plugin.WidgetPluginMetadata

/**
 * 迷你数值卡片小组件插件
 */
class MiniCardWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return MiniCardWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "widget-mini-card",
            version = "1.0.2",
            author = "官方",
            description = "迷你数值卡片，紧凑显示单一指标，支持图标、数值、单位和进度条",
            minAppVersion = "1.8.9",
            category = "信息"
        )
    }
}
