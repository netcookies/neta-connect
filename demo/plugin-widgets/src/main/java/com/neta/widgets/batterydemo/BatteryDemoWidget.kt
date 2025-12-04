package com.neta.widgets.batterydemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetFont
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.rememberWidgetFontFamily
import com.neta.isulewtools.api.widget.toHexString

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
object BatteryDemoWidgetSpec : WidgetSpec(
    type = "battery_demo_widget",  // 系统会自动添加 "dynamic_" 前缀变为 "dynamic_battery_demo_widget"
    displayName = "电池(示例)",
    recommendedGrid = Pair(2, 1),
    paramSchema = listOf(
        // 只需定义小组件特有的参数
        // scale 和 alpha 会自动注入！
        WidgetParamDesc(
            key = P.BATTERY_LEVEL.key,
            label = "电量(%)",
            type = WidgetParamType.FLOAT,
            defaultValue = P.BATTERY_LEVEL.default,
            description = "显示的电池电量百分比"
        ),
        WidgetParamDesc(
            key = P.COLOR.key,
            label = "颜色",
            type = WidgetParamType.STRING,
            defaultValue = P.COLOR.default.toHexString(),
            description = "电池背景颜色（十六进制）"
        )
    ),
    contentComposable = {
        BatteryDemoWidgetContent(it)
    },
    color = Color(0xFF66BB6A),
    icon = Icons.Default.BatteryChargingFull
) {
    /**
     * 参数定义
     */
    object P {
        val BATTERY_LEVEL = ParamDef("batteryLevel", 75f)
        val COLOR = ParamDef("color", Color(0xFF66BB6A))

        // 非参数常量
        const val TEXT_COLOR = "#FFFFFF"
        const val WIDTH = 100
        const val HEIGHT = 40
        const val FONT_SIZE = 16
    }
}

/**
 * 电池小组件内容
 *
 * 演示如何在动态小组件中使用自定义字体：
 * - 从 config.params 中读取 scale 和 alpha
 * - 使用 graphicsLayer 应用变换效果
 * - 使用 rememberWidgetFontFamily 加载主应用字体
 */
@Composable
fun BatteryDemoWidgetContent(config: WidgetConfig) {
    // 读取自定义参数
    val batteryLevel = config.getParam(BatteryDemoWidgetSpec.P.BATTERY_LEVEL)
    val color = config.getParam(BatteryDemoWidgetSpec.P.COLOR)

    // 读取自动注入的 scale 和 alpha 参数
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // ✨ 加载主应用字体（动态小组件字体使用示例）
    // 不需要手动获取 context，rememberWidgetFontFamily 会自动处理
    val fontFamily = rememberWidgetFontFamily(
        font = WidgetFont.MONTSERRAT_BOLD,
        weight = FontWeight.Bold
    )

    Box(
        modifier = Modifier
            .size(
                (BatteryDemoWidgetSpec.P.WIDTH * scale).dp,
                (BatteryDemoWidgetSpec.P.HEIGHT * scale).dp
            )  // 应用缩放到尺寸
            .background(color)
            .graphicsLayer(alpha = alpha),  // 应用透明度
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${batteryLevel.toInt()}%",
            color = Color.White,
            fontSize = (BatteryDemoWidgetSpec.P.FONT_SIZE * scale).sp,  // 文字也跟随缩放
            fontFamily = fontFamily  // 使用自定义字体
        )
    }
}

// 预览示例
@Preview
@Composable
fun BatteryDemoWidgetPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7)) // iOS 风格背景色
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("不同电量状态", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        // 不同电量
        BatteryDemoWidgetContent(
            WidgetConfig(params = mapOf("batteryLevel" to 100f, "color" to "#66BB6A"))
        )
        BatteryDemoWidgetContent(
            WidgetConfig(params = mapOf("batteryLevel" to 75f, "color" to "#66BB6A"))
        )
        BatteryDemoWidgetContent(
            WidgetConfig(params = mapOf("batteryLevel" to 50f, "color" to "#FFA726"))
        )
        BatteryDemoWidgetContent(
            WidgetConfig(params = mapOf("batteryLevel" to 25f, "color" to "#EF5350"))
        )
        BatteryDemoWidgetContent(
            WidgetConfig(params = mapOf("batteryLevel" to 10f, "color" to "#F44336"))
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("不同缩放", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        BatteryDemoWidgetContent(
            WidgetConfig(
                params = mapOf(
                    "batteryLevel" to 66f,
                    "color" to "#42A5F5",
                    "scale" to 1.5f
                )
            )
        )
        BatteryDemoWidgetContent(
            WidgetConfig(
                params = mapOf(
                    "batteryLevel" to 66f,
                    "color" to "#42A5F5",
                    "scale" to 0.8f
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("不同透明度", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        BatteryDemoWidgetContent(
            WidgetConfig(params = mapOf("batteryLevel" to 88f, "color" to "#AB47BC", "alpha" to 1f))
        )
        BatteryDemoWidgetContent(
            WidgetConfig(
                params = mapOf(
                    "batteryLevel" to 88f,
                    "color" to "#AB47BC",
                    "alpha" to 0.5f
                )
            )
        )
    }
}
