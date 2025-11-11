package com.neta.isulewtools.api.widget

/**
 * 小组件可用字体枚举
 *
 * 定义了主应用中可供小组件使用的字体资源。
 * 使用枚举提供 IDE 自动补全和类型安全。
 *
 * 使用示例：
 * ```
 * @Composable
 * fun MyWidget(config: WidgetConfig) {
 *     val fontFamily = rememberWidgetFontFamily(WidgetFont.MONTSERRAT_BOLD)
 *     Text("Hello", fontFamily = fontFamily)
 * }
 * ```
 */
enum class WidgetFont(val resourceName: String) {
    /**
     * Montserrat Bold 字体
     */
    MONTSERRAT_BOLD("montserrat_bold")
}
