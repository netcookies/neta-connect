package com.neta.isulewtools.widget.plugin

import com.neta.isulewtools.widget.WidgetSpec

/**
 * 小组件插件接口
 * 所有动态加载的小组件都需要实现此接口
 */
interface WidgetPlugin {
    /**
     * 获取小组件规格定义
     */
    fun getSpec(): WidgetSpec

    /**
     * 插件元信息
     */
    fun getMetadata(): WidgetPluginMetadata
}

/**
 * 插件元信息
 */
data class WidgetPluginMetadata(
    val id: String,              // 唯一标识,如 "battery_widget"
    val version: String,         // 版本号,如 "1.0.0"
    val author: String,          // 作者
    val description: String,     // 描述
    val minAppVersion: String    // 最低支持的 App 版本
)

/**
 * 插件加载来源
 */
enum class WidgetSource {
    BUILTIN,    // 内置(编译在 APK 中)
    DYNAMIC     // 动态加载(从 JAR 加载)
}
