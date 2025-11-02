package com.neta.isulewtools.api.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Widget类型描述（静态元信息/注册）
 *
 * 注意：所有小组件自动强制支持 scale（缩放）和 alpha（透明度）参数
 * 如果 paramSchema 中未包含这两个参数，构造函数会自动注入
 */
open class WidgetSpec(
    val type: String,                    // 类型唯一标识
    val displayName: String,             // UI名称
    paramSchema: List<WidgetParamDesc>,  // 参数schema，定义配置表单字段
    val contentComposable: @Composable (WidgetConfig) -> Unit, // Widget内容渲染入口
    val color: Color = Color(0xFF6200EE),        // Widget类型颜色（用于卡片背景）
    val icon: ImageVector? = null                 // Widget类型图标
) {
    // 自动注入 scale 和 alpha 参数（如果不存在）
    val paramSchema: List<WidgetParamDesc> = ensureRequiredParams(paramSchema)

    companion object {
        /**
         * 确保参数列表包含必需的 scale 和 alpha 参数
         */
        private fun ensureRequiredParams(params: List<WidgetParamDesc>): List<WidgetParamDesc> {
            val hasScale = params.any { it.key == "scale" && it.type == WidgetParamType.SCALE }
            val hasAlpha = params.any { it.key == "alpha" && it.type == WidgetParamType.ALPHA }

            // 如果都存在，直接返回
            if (hasScale && hasAlpha) {
                return params
            }

            // 准备要注入的参数
            val requiredParams = mutableListOf<WidgetParamDesc>()

            if (!hasScale) {
                requiredParams.add(
                    WidgetParamDesc(
                        key = "scale",
                        label = "缩放",
                        type = WidgetParamType.SCALE,
                        defaultValue = 1f,
                        required = false,
                        description = "小组件缩放比例（自动注入）"
                    )
                )
            }

            if (!hasAlpha) {
                requiredParams.add(
                    WidgetParamDesc(
                        key = "alpha",
                        label = "透明度",
                        type = WidgetParamType.ALPHA,
                        defaultValue = 1f,
                        required = false,
                        description = "小组件透明度（自动注入）"
                    )
                )
            }

            // 将必需参数添加到列表开头
            return requiredParams + params
        }
    }
}

/**
 * Widget参数描述/schema
 */
data class WidgetParamDesc @JvmOverloads constructor(
    val key: String,                     // 参数唯一key
    val label: String,                   // 显示标签
    val type: WidgetParamType,           // 类型
    val defaultValue: Any? = null,       // 默认值
    val options: List<Any>? = null,       // 可选项（下拉枚举等）
    val required: Boolean = false, // 新增必填字段标识
    val description: String? = null,
    val visibleWhen: Pair<String, Any>? = null  // 条件显示：当指定参数等于指定值时才显示
)

enum class WidgetParamType {
    COLOR, FLOAT, INT, STRING, ENUM, DATA_SOURCE, BOOL, SCALE, ALPHA, DIVIDER, ICON, VEHICLE_PROPERTY, VHAL_PROPERTY
}

/**
 * Widget实例配置
 */
data class WidgetConfig(
    val params: Map<String, Any?> = emptyMap()
)

/**
 * VHAL 属性（包含属性ID和区域ID）
 */
data class VhalProperty(
    val propertyId: Int,
    val areaId: Int
)

enum class ContainerType {
    FLOATING_WINDOW, // 悬浮窗
    DASHBOARD // 仪表盘
}

val containerNameMap = mapOf(
    ContainerType.DASHBOARD to "仪表盘",
    ContainerType.FLOATING_WINDOW to "悬浮窗"
)

/**
 * Widget显示模式
 */
enum class WidgetVisibilityMode {
    ALWAYS_SHOW,        // 总是显示
    SHOW_ON_PACKAGES    // 按需显示（根据包名列表）
}

val visibilityModeNameMap = mapOf(
    WidgetVisibilityMode.ALWAYS_SHOW to "总是显示",
    WidgetVisibilityMode.SHOW_ON_PACKAGES to "按需显示"
)
