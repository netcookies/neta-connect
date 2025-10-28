package com.neta.isulewtools.widget.plugin

import com.neta.isulewtools.widget.WidgetSpec

/**
 * 小组件插件接口
 * 注意:这是为动态加载而复制的版本
 */
interface WidgetPlugin {
    fun getSpec(): WidgetSpec
    fun getMetadata(): WidgetPluginMetadata
}

data class WidgetPluginMetadata(
    val id: String,
    val version: String,
    val author: String,
    val description: String,
    val minAppVersion: String
)
