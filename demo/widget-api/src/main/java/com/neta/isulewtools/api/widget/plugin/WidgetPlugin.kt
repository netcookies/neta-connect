package com.neta.isulewtools.api.widget.plugin

import com.neta.isulewtools.api.widget.WidgetSpec

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
    val minAppVersion: String,   // 最低支持的 App 版本
    val category: String = "车机", // 分类,如 "车机"、"示例" 等，默认为 "车机"
    val signer: String? = null,    // 预留：签名主体标识
    val certificateSha256: String? = null // 预留：证书指纹
) {
    /**
     * 兼容旧版本（5 参数构造函数）
     * 用于加载旧版本 JAR 文件时的向后兼容
     */
    @Deprecated("Use primary constructor with category parameter", level = DeprecationLevel.HIDDEN)
    constructor(
        id: String,
        version: String,
        author: String,
        description: String,
        minAppVersion: String
    ) : this(id, version, author, description, minAppVersion, "车机", null, null)

    /**
     * 兼容旧版本（6 参数构造函数）
     * 用于加载已带 category、但尚未声明 trust metadata 的历史插件
     */
    @Deprecated("Use primary constructor with optional trust metadata", level = DeprecationLevel.HIDDEN)
    constructor(
        id: String,
        version: String,
        author: String,
        description: String,
        minAppVersion: String,
        category: String
    ) : this(id, version, author, description, minAppVersion, category, null, null)
}

/**
 * 插件加载来源
 */
enum class WidgetSource {
    BUILTIN,    // 内置(编译在 APK 中)
    DYNAMIC     // 动态加载(从 JAR 加载)
}
