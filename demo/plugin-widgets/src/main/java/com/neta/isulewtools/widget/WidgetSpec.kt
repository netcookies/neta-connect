package com.neta.isulewtools.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Widget类型描述(静态元信息/注册)
 * 注意:这是为动态加载而复制的简化版本
 *
 * 注意：所有小组件自动强制支持 scale（缩放）和 alpha（透明度）参数
 * 如果 paramSchema 中未包含这两个参数，构造函数会自动注入
 */
open class WidgetSpec(
    val type: String,
    val displayName: String,
    paramSchema: List<WidgetParamDesc>,
    val contentComposable: @Composable (WidgetConfig) -> Unit,
    val color: Color = Color(0xFF6200EE),
    val icon: ImageVector? = null
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

data class WidgetParamDesc(
    val key: String,
    val label: String,
    val type: WidgetParamType,
    val defaultValue: Any? = null,
    val options: List<Any>? = null,
    val required: Boolean = false,
    val description: String? = null
)

enum class WidgetParamType {
    COLOR, FLOAT, INT, STRING, ENUM, DATA_SOURCE, BOOL, SCALE, ALPHA,
}

data class WidgetConfig(
    val params: Map<String, Any?> = emptyMap()
)
