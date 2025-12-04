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
enum class WidgetFont(val resourceName: String, val displayName: String) {
    /**
     * Montserrat Regular 字体 - 英文/数字 Normal 字重
     */
    MONTSERRAT_REGULAR("montserrat_regular", "Montserrat Regular"),

    /**
     * Montserrat Medium 字体 - 英文/数字 Medium 字重
     */
    MONTSERRAT_MEDIUM("montserrat_medium", "Montserrat Medium"),

    /**
     * Montserrat Bold 字体 - 英文/数字 Bold 字重
     */
    MONTSERRAT_BOLD("montserrat_bold", "Montserrat Bold"),

    /**
     * 纳米萌文仿宋 - 中文字体
     */
    NANO_MYONG_WEN_FANGSONG("nano_myong_wen_fangsong", "纳米萌文仿宋")
}
