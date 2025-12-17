package com.neta.widgets.minicard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getDataSourceWithDisplay
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.icons.MaterialIconsProvider
import com.neta.isulewtools.api.widget.toHexString

/**
 * 迷你数值卡片小组件
 *
 * 紧凑的卡片式显示，包含图标、数值、单位和进度条
 * 适合显示单一指标
 */
object MiniCardWidgetSpec : WidgetSpec(
    type = "mini_card",
    displayName = "迷你数值卡片",
    recommendedGrid = Pair(2, 1),
    icon = Icons.Outlined.Speed,
    color = Color(0xFF2196F3),

    paramSchema = WidgetParamDesc.buildParams {
        // 数据
        group("数据") {
            +WidgetParamDesc(
                key = P.VALUE_DATASOURCE.key,
                label = "数值数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = true,
                defaultValue = P.VALUE_DATASOURCE.default
            )
            +WidgetParamDesc(
                key = P.PROGRESS_DATASOURCE.key,
                label = "进度/颜色数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = false,
                defaultValue = P.PROGRESS_DATASOURCE.default
            )
            +WidgetParamDesc(
                key = P.DECIMALS.key,
                label = "小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.DECIMALS.default
            )
            +WidgetParamDesc(
                key = P.UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.UNIT.default
            )
        }

        // 显示设置
        group("显示设置") {
            +WidgetParamDesc(
                key = P.ICON.key,
                label = "图标",
                type = WidgetParamType.ICON,
                defaultValue = P.ICON.default
            )
            +WidgetParamDesc(
                key = P.ICON_COLOR.key,
                label = "图标颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.ICON_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.SHOW_PROGRESS.key,
                label = "显示进度条",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_PROGRESS.default
            )
        }

        // 样式
        group("样式") {
            +WidgetParamDesc(
                key = P.WIDTH.key,
                label = "宽度 (dp)",
                type = WidgetParamType.INT,
                defaultValue = P.WIDTH.default
            )
            +WidgetParamDesc(
                key = P.HEIGHT.key,
                label = "高度 (dp)",
                type = WidgetParamType.INT,
                defaultValue = P.HEIGHT.default
            )
            +WidgetParamDesc(
                key = P.BG_COLOR.key,
                label = "背景颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.BG_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.CORNER_RADIUS.key,
                label = "圆角半径",
                type = WidgetParamType.INT,
                defaultValue = P.CORNER_RADIUS.default
            )
            +WidgetParamDesc(
                key = P.TEXT_COLOR.key,
                label = "文字颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.TEXT_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.UNIT_COLOR.key,
                label = "单位颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.UNIT_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.PROGRESS_COLOR.key,
                label = "进度条颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.PROGRESS_COLOR.default.toHexString()
            )
        }

        // 颜色映射
        group("颜色映射") {
            +WidgetParamDesc(
                key = P.USE_COLOR_MAP.key,
                label = "启用根据百分比自动变色",
                type = WidgetParamType.BOOL,
                defaultValue = P.USE_COLOR_MAP.default
            )
            +WidgetParamDesc(
                key = P.COLOR_LOW.key,
                label = "低值颜色 (<33%)",
                type = WidgetParamType.COLOR,
                defaultValue = P.COLOR_LOW.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.COLOR_MID.key,
                label = "中值颜色 (33%-66%)",
                type = WidgetParamType.COLOR,
                defaultValue = P.COLOR_MID.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.COLOR_HIGH.key,
                label = "高值颜色 (>66%)",
                type = WidgetParamType.COLOR,
                defaultValue = P.COLOR_HIGH.default.toHexString()
            )
        }
    },

    contentComposable = { MiniCardWidgetContent(it) }
) {
    object P {
        // 数据
        val VALUE_DATASOURCE = ParamDef("value_datasource", "557844019")
        val PROGRESS_DATASOURCE = ParamDef("progress_datasource", "559941152")
        val DECIMALS = ParamDef("decimals", 0)
        val UNIT = ParamDef("unit", "km")

        // 显示设置
        val ICON = ParamDef("icon", "Speed")
        val ICON_COLOR = ParamDef("iconColor", Color(0xFF90CAF9))
        val SHOW_PROGRESS = ParamDef("showProgress", true)

        // 样式
        val WIDTH = ParamDef("width", 200)
        val HEIGHT = ParamDef("height", 120)
        val BG_COLOR = ParamDef("bgColor", Color(0xFF1E1E1E))
        val CORNER_RADIUS = ParamDef("cornerRadius", 16)
        val TEXT_COLOR = ParamDef("textColor", Color(0xFFFFFFFF))
        val UNIT_COLOR = ParamDef("unitColor", Color(0xFFB0B0B0))
        val PROGRESS_COLOR = ParamDef("progressColor", Color(0xFF2196F3))

        // 颜色映射
        val USE_COLOR_MAP = ParamDef("useColorMap", false)
        val COLOR_LOW = ParamDef("colorLow", Color(0xFF66BB6A))
        val COLOR_MID = ParamDef("colorMid", Color(0xFFFFA726))
        val COLOR_HIGH = ParamDef("colorHigh", Color(0xFFEF5350))
    }
}

@Composable
fun MiniCardWidgetContent(config: WidgetConfig) {
    // 读取配置
    val decimals = config.getParam(MiniCardWidgetSpec.P.DECIMALS)
    val unit = config.getParam(MiniCardWidgetSpec.P.UNIT)
    val icon = config.getParam(MiniCardWidgetSpec.P.ICON)
    val iconColor = config.getParam(MiniCardWidgetSpec.P.ICON_COLOR)
    val showProgress = config.getParam(MiniCardWidgetSpec.P.SHOW_PROGRESS)

    val width = config.getParam(MiniCardWidgetSpec.P.WIDTH)
    val height = config.getParam(MiniCardWidgetSpec.P.HEIGHT)
    val bgColor = config.getParam(MiniCardWidgetSpec.P.BG_COLOR)
    val cornerRadius = config.getParam(MiniCardWidgetSpec.P.CORNER_RADIUS)
    val textColor = config.getParam(MiniCardWidgetSpec.P.TEXT_COLOR)
    val unitColor = config.getParam(MiniCardWidgetSpec.P.UNIT_COLOR)
    val progressColor = config.getParam(MiniCardWidgetSpec.P.PROGRESS_COLOR)

    val useColorMap = config.getParam(MiniCardWidgetSpec.P.USE_COLOR_MAP)
    val colorLow = config.getParam(MiniCardWidgetSpec.P.COLOR_LOW)
    val colorMid = config.getParam(MiniCardWidgetSpec.P.COLOR_MID)
    val colorHigh = config.getParam(MiniCardWidgetSpec.P.COLOR_HIGH)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 获取数值数据
    val (valueNum, valueDisplay) = config.getDataSourceWithDisplay(
        MiniCardWidgetSpec.P.VALUE_DATASOURCE.key,
        decimals = decimals,
        defaultValue = "568"
    )

    // 获取进度数据（0-100的百分比）
    val (progressNum, _) = config.getDataSourceWithDisplay(
        MiniCardWidgetSpec.P.PROGRESS_DATASOURCE.key,
        decimals = 0,
        defaultValue = "75"
    )
    val progressValue = (progressNum ?: 75f) / 100f

    // 根据百分比计算颜色
    val dynamicColor = if (useColorMap) {
        when {
            progressValue < 0.33f -> colorLow
            progressValue < 0.66f -> colorMid
            else -> colorHigh
        }
    } else {
        progressColor
    }

    // 调用独立的Display组件
    MiniCardDisplay(
        value = valueDisplay,
        unit = unit,
        iconName = icon,
        iconColor = iconColor,
        showProgress = showProgress,
        progressValue = progressValue,
        width = width,
        height = height,
        bgColor = bgColor,
        cornerRadius = cornerRadius,
        textColor = textColor,
        unitColor = unitColor,
        progressColor = dynamicColor,
        scale = scale,
        alpha = alpha
    )
}

/**
 * 迷你数值卡片显示组件（独立的 UI 组件，可复用于 Content 和 Preview）
 */
@Composable
fun MiniCardDisplay(
    value: String,
    unit: String,
    iconName: String,
    iconColor: Color,
    showProgress: Boolean,
    progressValue: Float,
    width: Int,
    height: Int,
    bgColor: Color,
    cornerRadius: Int,
    textColor: Color,
    unitColor: Color,
    progressColor: Color,
    scale: Float = 1f,
    alpha: Float = 1f
) {
    Box(
        modifier = Modifier
            .width((width * scale).dp)
            .height((height * scale).dp)
            .graphicsLayer(alpha = alpha)
            .clip(RoundedCornerShape((cornerRadius * scale).dp))
            .background(bgColor)
            .padding((16 * scale).dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 顶部：图标居左，文字和单位居右
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 左侧：图标
                val iconVector =
                    MaterialIconsProvider.getIconByName(iconName) ?: Icons.Outlined.Speed
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size((36 * scale).dp)
                )

                // 右侧：数值和单位
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = value,
                        fontSize = (36 * scale).sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        lineHeight = (36 * scale).sp
                    )

                    Spacer(modifier = Modifier.width((4 * scale).dp))

                    Text(
                        text = unit,
                        fontSize = (36 * scale).sp,
                        color = unitColor,
                        lineHeight = (36 * scale).sp
                    )
                }
            }

            // 底部：进度条
            if (showProgress) {
                LinearProgressIndicator(
                    progress = { progressValue.coerceIn(0f, 1f) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((4 * scale).dp)
                        .clip(RoundedCornerShape((2 * scale).dp)),
                    color = progressColor,
                    trackColor = progressColor.copy(alpha = 0.2f),
                    strokeCap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview
@Composable
fun MiniCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 默认样式
        MiniCardDisplay(
            value = "568",
            unit = "km",
            iconName = "Speed",
            iconColor = Color(0xFF90CAF9),
            showProgress = true,
            progressValue = 0.75f,
            width = 200,
            height = 120,
            bgColor = Color(0xFF1E1E1E),
            cornerRadius = 16,
            textColor = Color.White,
            unitColor = Color(0xFFB0B0B0),
            progressColor = Color(0xFF2196F3)
        )

        // 高进度样式（红色）
        MiniCardDisplay(
            value = "89",
            unit = "%",
            iconName = "BatteryChargingFull",
            iconColor = Color(0xFFEF5350),
            showProgress = true,
            progressValue = 0.89f,
            width = 160,
            height = 90,
            bgColor = Color(0xFF1E1E1E),
            cornerRadius = 16,
            textColor = Color.White,
            unitColor = Color(0xFFB0B0B0),
            progressColor = Color(0xFFEF5350)
        )
    }
}
