package com.neta.widgets.battery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.neta.isulewtools.widget.WidgetConfig
import com.neta.isulewtools.widget.WidgetParamDesc
import com.neta.isulewtools.widget.WidgetParamType
import com.neta.isulewtools.widget.WidgetSpec

/**
 * 简化版电池小组件 - 动态加载示例
 *
 * 功能特性：
 * - ✅ 自动支持缩放和透明度（由 WidgetSpec 自动注入）
 * - ✅ 独立编译为 JAR 文件
 * - ✅ 运行时动态加载
 * - ✅ 完整的元信息定义
 *
 * 注意：不需要手动添加 scale 和 alpha 参数！
 * 这两个参数会由 WidgetSpec 构造函数自动注入到 paramSchema 中
 */
object SimpleBatteryWidgetSpec : WidgetSpec(
    type = "battery_widget",  // 系统会自动添加 "dynamic_" 前缀变为 "dynamic_battery_widget"
    displayName = "电池(示例)",
    paramSchema = listOf(
        // 只需定义小组件特有的参数
        // scale 和 alpha 会自动注入！
        WidgetParamDesc(
            key = "batteryLevel",
            label = "电量(%)",
            type = WidgetParamType.FLOAT,
            defaultValue = 75f,
            description = "显示的电池电量百分比"
        ),
        WidgetParamDesc(
            key = "color",
            label = "颜色",
            type = WidgetParamType.STRING,
            defaultValue = "#66BB6A",
            description = "电池背景颜色（十六进制）"
        )
    ),
    contentComposable = {
        SimpleBatteryWidgetContent(it)
    },
    color = Color(0xFF66BB6A),
    icon = Icons.Default.BatteryChargingFull
)

/**
 * 电池小组件内容
 *
 * 演示如何使用自动注入的 scale 和 alpha 参数：
 * - 从 config.params 中读取 scale 和 alpha
 * - 使用 graphicsLayer 应用变换效果
 */
@Composable
fun SimpleBatteryWidgetContent(config: WidgetConfig) {
    // 读取自定义参数
    val batteryLevel = (config.params["batteryLevel"] as? Number)?.toFloat() ?: 75f
    val colorStr = config.params["color"]?.toString() ?: "#66BB6A"

    // 读取自动注入的 scale 和 alpha 参数
    val scale = (config.params["scale"] as? Number)?.toFloat() ?: 1f
    val alpha = (config.params["alpha"] as? Number)?.toFloat() ?: 1f

    // 简化的颜色解析
    val color = try {
        val colorInt = colorStr.toColorInt()
        Color(colorInt)
    } catch (e: Exception) {
        Color(0xFF66BB6A)
    }

    Box(
        modifier = Modifier
            .size((100 * scale).dp, (40 * scale).dp)  // 应用缩放到尺寸
            .background(color)
            .graphicsLayer(alpha = alpha),  // 应用透明度
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${batteryLevel.toInt()}%",
            color = Color.White,
            fontSize = (16 * scale).sp  // 文字也跟随缩放
        )
    }
}
