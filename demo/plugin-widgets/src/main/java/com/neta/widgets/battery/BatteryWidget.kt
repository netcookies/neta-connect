package com.neta.widgets.battery

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetFont
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getDataSourceFloat
import com.neta.isulewtools.api.widget.getDataSourceInt
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.rememberWidgetFontFamily
import com.neta.isulewtools.api.widget.toHexString

/**
 * BatteryWidget 的 WidgetSpec 注册示例
 */
object BatteryWidgetSpec : WidgetSpec(
    type = "battery_widget",
    displayName = "电池",
    recommendedGrid = Pair(1, 1),
    paramSchema = WidgetParamDesc.buildParams {
        +WidgetParamDesc(
            key = P.COLOR.key,
            label = "电池填充色",
            type = WidgetParamType.COLOR,
            defaultValue = P.COLOR.default.toHexString(),
            description = "电量填充部分的颜色"
        )
        // scale 和 alpha 会自动注入，无需手动定义
        +WidgetParamDesc(
            key = P.BACKGROUND_COLOR.key,
            label = "背景色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BACKGROUND_COLOR.default.toHexString(),
            description = "电池外壳的背景颜色"
        )
        +WidgetParamDesc(
            key = P.TEXT_COLOR.key,
            label = "字体颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.TEXT_COLOR.default.toHexString(),
            description = "电量数值的文字颜色"
        )
        +WidgetParamDesc(
            key = P.SHOW_TEXT.key,
            label = "显示数值",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_TEXT.default,
            description = "显示电量百分比数值"
        )
        +WidgetParamDesc(
            key = P.FIXED_FILL_COLOR.key,
            label = "固定填充色",
            type = WidgetParamType.BOOL,
            defaultValue = P.FIXED_FILL_COLOR.default,
            description = "关闭后电量低时自动变为黄色/红色"
        )
        +WidgetParamDesc(
            key = P.HEIGHT.key,
            label = "高度",
            type = WidgetParamType.FLOAT,
            defaultValue = P.HEIGHT.default,
            description = "电池指示器的高度 (单位: dp)"
        )
        +WidgetParamDesc(
            key = P.DATASOURCE.key,
            label = "属性数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.DATASOURCE.default,
            options = emptyList(), // 后续动态赋值
            required = true,
            description = "电池电量的数据来源(0-100)"
        )
        +WidgetParamDesc(
            key = P.CHARGING_STATUS.key,
            label = "充电状态数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.CHARGING_STATUS.default,
            options = emptyList(),
            required = false,
            description = "充电状态 (1=慢充, 2=快充, 4=边充边加热)"
        )
        +WidgetParamDesc(
            key = P.RANGE_EXTENDER_MODE.key,
            label = "增程模式数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = P.RANGE_EXTENDER_MODE.default,
            options = emptyList(),
            required = false,
            description = "发动机转速 (>0 表示增程器已启动)"
        )
    },
    contentComposable = {
        BatteryWidgetContent(it)
    },
    color = Color(0xFF66BB6A),
    icon = Icons.Default.BatteryChargingFull
) {
    /**
     * 参数定义对象（使用 ParamDef）
     */
    object P {
        val COLOR = ParamDef("color", Color(0xFF53CC59))
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color(0xFFABAAAA))
        val TEXT_COLOR = ParamDef("textColor", Color(0xFFFFFFFF))
        val SHOW_TEXT = ParamDef("showText", true)
        val FIXED_FILL_COLOR = ParamDef("fixedFillColor", false)
        val HEIGHT = ParamDef("height", 21f)

        // 数据源参数（使用 ParamDef，default 为默认属性名）
        val DATASOURCE = ParamDef("datasource", "HZ_STATE_CHARGE")
        val CHARGING_STATUS = ParamDef("chargingStatus", "HZ_CHARGE_STATE")
        val RANGE_EXTENDER_MODE = ParamDef("rangeExtenderMode", "HZ_ENGINE_SPEED")
    }
}

@Composable
fun BatteryWidgetContent(config: WidgetConfig) {
    // 使用 getParam 方法读取参数
    val fillColor = config.getParam(BatteryWidgetSpec.P.COLOR)
    val backgroundColor = config.getParam(BatteryWidgetSpec.P.BACKGROUND_COLOR)
    val textColor = config.getParam(BatteryWidgetSpec.P.TEXT_COLOR)
    val showText = config.getParam(BatteryWidgetSpec.P.SHOW_TEXT)
    val fixedFillColor = config.getParam(BatteryWidgetSpec.P.FIXED_FILL_COLOR)
    val height = config.getParam(BatteryWidgetSpec.P.HEIGHT)

    // 使用辅助函数获取自动注入的参数
    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 从注入的数据中读取电池电量
    val batteryLevel = config.getDataSourceFloat(BatteryWidgetSpec.P.DATASOURCE.key, 0f)

    // 充电状态枚举：0=正常, 1=慢速充电, 2=快速充电, 3=加热, 4=边充边加热, 5=保温, 6=充电停止, 7=充电器故障, 8=充电完成
    // 只有 1(慢速)、2(快速)、4(边充边加热) 时显示充电图标
    val chargingStatus = config.getDataSourceInt(BatteryWidgetSpec.P.CHARGING_STATUS.key, 0)
    val isCharging = chargingStatus in listOf(1, 2, 4)

    val isRangeExtenderMode = config.getDataSourceFloat(BatteryWidgetSpec.P.RANGE_EXTENDER_MODE.key, 0f) > 0f

    AppleStyleBatteryIndicator(
        batteryLevel = batteryLevel,
        scale = scale,
        alpha = alpha,
        showText = showText,
        fillColor = fillColor,
        backgroundColor = backgroundColor,
        textColor = textColor,
        fixedFillColor = fixedFillColor,
        height = height.dp,
        isCharging = isCharging,
        isRangeExtenderMode = isRangeExtenderMode
    )
}

@Composable
fun AppleStyleBatteryIndicator(
    batteryLevel: Float, // 0~100
    modifier: Modifier = Modifier,
    scale: Float = 1f,
    alpha: Float = 1f,
    showText: Boolean = true,
    fillColor: Color = BatteryWidgetSpec.P.COLOR.default, // iOS 绿色
    backgroundColor: Color = BatteryWidgetSpec.P.BACKGROUND_COLOR.default,
    textColor: Color = BatteryWidgetSpec.P.TEXT_COLOR.default,
    fixedFillColor: Boolean = false,
    width: Dp = 49.dp, // 更接近苹果比例
    height: Dp = 21.dp,
    isCharging: Boolean = false,
    isRangeExtenderMode: Boolean = false
) {
    val fillPercent = batteryLevel.coerceIn(0f, 100f) / 100f

    // iOS 原生配色
    val adaptiveColor = if (fixedFillColor) fillColor else when {
        batteryLevel > 20f -> BatteryWidgetSpec.P.COLOR.default // iOS 绿色
        batteryLevel > 10f -> Color(0xFFFFCC00) // iOS 黄色
        else -> Color(0xFFFF3B30)              // iOS 红色
    }

    // 加载字体
    val fontFamily = rememberWidgetFontFamily(WidgetFont.MONTSERRAT_BOLD, FontWeight.Bold)

    Box(
        modifier = modifier
            .width(width * scale)
            .height(height * scale)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = alpha)
        ) {
            // 电池主体区域（减去电池头的宽度）
            val tipWidth = (2.25.dp.toPx()) * scale // 减小到原来的一半
            val tipGap = (0.75.dp.toPx()) * scale // 相应减小间隙
            val bodyWidth = size.width - tipWidth - tipGap
            val bodyRect = Rect(0f, 0f, bodyWidth, size.height)

            // 主体圆角半径
            val bodyCornerRadius = (4.5.dp.toPx()) * scale

            // 电量填充（无内边距，苹果最新风格）
            if (fillPercent > 0) {
                if (fillPercent <= 0.15f) {
                    // 低电量反转模式：先绘制满电填充，再从右侧覆盖背景色
                    // 使用 <= 0.15f (15%) 以确保 1-15% 都保持左侧完整圆角
                    drawBatteryFill(
                        bodyRect = bodyRect,
                        fillPercent = 1.0f,
                        fillColor = adaptiveColor,
                        cornerRadius = bodyCornerRadius,
                        fromLeft = true
                    )
                    drawBatteryFill(
                        bodyRect = bodyRect,
                        fillPercent = 1.0f - fillPercent,
                        fillColor = backgroundColor,
                        cornerRadius = bodyCornerRadius,
                        fromLeft = false
                    )
                } else {
                    // 正常模式：先绘制背景，再从左侧填充
                    drawBatteryFill(
                        bodyRect = bodyRect,
                        fillPercent = 1.0f,
                        fillColor = backgroundColor,
                        cornerRadius = bodyCornerRadius,
                        fromLeft = true
                    )
                    drawBatteryFill(
                        bodyRect = bodyRect,
                        fillPercent = fillPercent,
                        fillColor = adaptiveColor,
                        cornerRadius = bodyCornerRadius,
                        fromLeft = true
                    )
                }
            }

            // 电池凸起（头部）- 采用苹果最新风格，使用背景色
            val tipHeight = size.height * 0.4f // 约 40% 高度
            val tipTop = (size.height - tipHeight) / 2
            val tipCornerRadius = (1.5.dp.toPx()) * scale

            drawRoundRect(
                color = backgroundColor, // 使用半透明背景色，更接近苹果风格
                topLeft = Offset(bodyWidth + tipGap, tipTop),
                size = Size(tipWidth, tipHeight),
                cornerRadius = CornerRadius(tipCornerRadius, tipCornerRadius)
            )

            // 增程模式指示器 - 在电池主体周围绘制一个细边框
            if (isRangeExtenderMode) {
                val borderWidth = (1.5.dp.toPx()) * scale
                drawRoundRect(
                    color = adaptiveColor,
                    topLeft = Offset(0f, 0f),
                    size = Size(bodyWidth, size.height),
                    cornerRadius = CornerRadius(bodyCornerRadius, bodyCornerRadius),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = borderWidth)
                )
            }
        }

        // 百分比文字和充电图标
        if (showText) {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = (-2).dp * scale),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${batteryLevel.toInt()}",
                    style = TextStyle(
                        color = textColor,
                        fontSize = (14 * scale).sp,
                        fontWeight = FontWeight.Medium, // iOS 使用 Medium 字重
                        fontFamily = fontFamily,
                        letterSpacing = (-0.2).sp // 更紧凑的字间距
                    )
                )
                // 显示充电图标
                if (isCharging) {
                    Text(
                        text = "⚡",
                        style = TextStyle(
                            color = textColor,
                            fontSize = (10 * scale).sp
                        ),
                        modifier = Modifier.offset(x = 1.dp * scale)
                    )
                }
            }
        }
    }
}

/**
 * 绘制电池填充
 * @param bodyRect 电池主体区域
 * @param fillPercent 填充百分比 (0.0 ~ 1.0)
 * @param fillColor 填充颜色
 * @param cornerRadius 圆角半径
 * @param fromLeft true: 从左到右填充, false: 从右到左填充
 */
private fun DrawScope.drawBatteryFill(
    bodyRect: Rect,
    fillPercent: Float,
    fillColor: Color,
    cornerRadius: Float,
    fromLeft: Boolean = true
) {
    val fillWidth = bodyRect.width * fillPercent
    val fillHeight = bodyRect.height

    if (fillWidth <= 0) return

    if (fillPercent > 0.92f) {
        // 接近满电：标准圆角矩形（左右都圆角）
        val left = if (fromLeft) bodyRect.left else bodyRect.right - fillWidth
        drawRoundRect(
            color = fillColor,
            topLeft = Offset(left, bodyRect.top),
            size = Size(fillWidth, fillHeight),
            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
        )
    } else {
        // 常规填充：单侧圆角
        // 当宽度太小时，动态限制圆角半径避免绘制异常
        val actualRadius = minOf(cornerRadius, fillWidth / 2, fillHeight / 2)

        val path = Path().apply {
            if (fromLeft) {
                // 从左到右：只有左侧圆角
                moveTo(bodyRect.left + actualRadius, bodyRect.top)
                lineTo(bodyRect.left + fillWidth, bodyRect.top)
                lineTo(bodyRect.left + fillWidth, bodyRect.bottom)
                lineTo(bodyRect.left + actualRadius, bodyRect.bottom)

                // 左下圆角
                arcTo(
                    rect = Rect(
                        left = bodyRect.left,
                        top = bodyRect.bottom - actualRadius * 2,
                        right = bodyRect.left + actualRadius * 2,
                        bottom = bodyRect.bottom
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(bodyRect.left, bodyRect.top + actualRadius)

                // 左上圆角
                arcTo(
                    rect = Rect(
                        left = bodyRect.left,
                        top = bodyRect.top,
                        right = bodyRect.left + actualRadius * 2,
                        bottom = bodyRect.top + actualRadius * 2
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
            } else {
                // 从右到左：只有右侧圆角
                moveTo(bodyRect.right - actualRadius, bodyRect.top)
                lineTo(bodyRect.right - fillWidth, bodyRect.top)
                lineTo(bodyRect.right - fillWidth, bodyRect.bottom)
                lineTo(bodyRect.right - actualRadius, bodyRect.bottom)

                // 右下圆角
                arcTo(
                    rect = Rect(
                        left = bodyRect.right - actualRadius * 2,
                        top = bodyRect.bottom - actualRadius * 2,
                        right = bodyRect.right,
                        bottom = bodyRect.bottom
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(bodyRect.right, bodyRect.top + actualRadius)

                // 右上圆角
                arcTo(
                    rect = Rect(
                        left = bodyRect.right - actualRadius * 2,
                        top = bodyRect.top,
                        right = bodyRect.right,
                        bottom = bodyRect.top + actualRadius * 2
                    ),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
            }

            close()
        }
        drawPath(path = path, color = fillColor)
    }
}

// 预览示例
@Preview
@Composable
fun BatteryPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7)) // iOS 背景色
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("不同电量状态", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        AppleStyleBatteryIndicator(batteryLevel = 100f)
        AppleStyleBatteryIndicator(batteryLevel = 94f)
        AppleStyleBatteryIndicator(batteryLevel = 75f)
        AppleStyleBatteryIndicator(batteryLevel = 50f)
        AppleStyleBatteryIndicator(batteryLevel = 25f)
        AppleStyleBatteryIndicator(batteryLevel = 19f)
        AppleStyleBatteryIndicator(batteryLevel = 15f)

        Spacer(modifier = Modifier.height(16.dp))
        Text("不同缩放", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        AppleStyleBatteryIndicator(batteryLevel = 66f, scale = 1.5f)
        AppleStyleBatteryIndicator(batteryLevel = 66f, scale = 0.8f)

        Spacer(modifier = Modifier.height(16.dp))
        Text("不显示文字", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        AppleStyleBatteryIndicator(batteryLevel = 88f, showText = false)
    }
}
