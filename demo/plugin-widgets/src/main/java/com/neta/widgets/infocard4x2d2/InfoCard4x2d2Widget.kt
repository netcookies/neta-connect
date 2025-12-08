package com.neta.widgets.infocard4x2d2

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
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
 * 信息卡片小组件 (4x2, 2个信息)
 *
 * 横向显示2个大数值
 * 适合展示对比型指标
 */
object InfoCard4x2d2WidgetSpec : WidgetSpec(
    type = "info_card_4x2d2",
    displayName = "信息卡片(4x2-2项)",
    recommendedGrid = Pair(4, 2),
    icon = Icons.Outlined.Analytics,
    color = Color(0xFFE91E63),

    paramSchema = WidgetParamDesc.buildParams {
        // 信息1
        group("信息1 (左侧)") {
            +WidgetParamDesc(
                key = P.INFO1_DATASOURCE,
                label = "数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = true
            )
            +WidgetParamDesc(
                key = P.INFO1_LABEL.key,
                label = "标签",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO1_LABEL.default
            )
            +WidgetParamDesc(
                key = P.INFO1_UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO1_UNIT.default
            )
            +WidgetParamDesc(
                key = P.INFO1_DECIMALS.key,
                label = "小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.INFO1_DECIMALS.default
            )
        }

        // 信息2
        group("信息2 (右侧)") {
            +WidgetParamDesc(
                key = P.INFO2_DATASOURCE,
                label = "数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = true
            )
            +WidgetParamDesc(
                key = P.INFO2_LABEL.key,
                label = "标签",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO2_LABEL.default
            )
            +WidgetParamDesc(
                key = P.INFO2_UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO2_UNIT.default
            )
            +WidgetParamDesc(
                key = P.INFO2_DECIMALS.key,
                label = "小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.INFO2_DECIMALS.default
            )
        }

        // 显示设置
        group("显示设置") {
            +WidgetParamDesc(
                key = P.TITLE.key,
                label = "标题",
                type = WidgetParamType.STRING,
                defaultValue = P.TITLE.default
            )
            +WidgetParamDesc(
                key = P.ICON.key,
                label = "图标",
                type = WidgetParamType.ICON,
                defaultValue = P.ICON.default
            )
        }

        // 样式
        group("样式") {
            +WidgetParamDesc(
                key = P.BG_COLOR.key,
                label = "背景颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.BG_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.BG_COLOR2.key,
                label = "背景渐变色",
                type = WidgetParamType.COLOR,
                defaultValue = P.BG_COLOR2.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.TEXT_COLOR.key,
                label = "文字颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.TEXT_COLOR.default.toHexString()
            )
        }
    },

    contentComposable = { InfoCard4x2d2WidgetContent(it) }
) {
    object P {
        // 信息1
        const val INFO1_DATASOURCE = "info1_datasource"
        val INFO1_LABEL = ParamDef("info1_label", "信息1")
        val INFO1_UNIT = ParamDef("info1_unit", "")
        val INFO1_DECIMALS = ParamDef("info1_decimals", 1)

        // 信息2
        const val INFO2_DATASOURCE = "info2_datasource"
        val INFO2_LABEL = ParamDef("info2_label", "信息2")
        val INFO2_UNIT = ParamDef("info2_unit", "")
        val INFO2_DECIMALS = ParamDef("info2_decimals", 1)

        // 显示设置
        val TITLE = ParamDef("title", "信息卡片")
        val ICON = ParamDef("icon", "Analytics")

        // 样式
        val BG_COLOR = ParamDef("bgColor", Color(0xFFE91E63))
        val BG_COLOR2 = ParamDef("bgColor2", Color(0xFFF06292))
        val TEXT_COLOR = ParamDef("textColor", Color(0xFFFFFFFF))

        // 默认尺寸 (4x2)
        val WIDTH = 400.dp
        val HEIGHT = 200.dp
    }
}

@Composable
fun InfoCard4x2d2WidgetContent(config: WidgetConfig) {
    // 读取配置
    val title = config.getParam(InfoCard4x2d2WidgetSpec.P.TITLE)
    val icon = config.getParam(InfoCard4x2d2WidgetSpec.P.ICON)

    val info1Label = config.getParam(InfoCard4x2d2WidgetSpec.P.INFO1_LABEL)
    val info1Unit = config.getParam(InfoCard4x2d2WidgetSpec.P.INFO1_UNIT)
    val info1Decimals = config.getParam(InfoCard4x2d2WidgetSpec.P.INFO1_DECIMALS)

    val info2Label = config.getParam(InfoCard4x2d2WidgetSpec.P.INFO2_LABEL)
    val info2Unit = config.getParam(InfoCard4x2d2WidgetSpec.P.INFO2_UNIT)
    val info2Decimals = config.getParam(InfoCard4x2d2WidgetSpec.P.INFO2_DECIMALS)

    val bgColor = config.getParam(InfoCard4x2d2WidgetSpec.P.BG_COLOR)
    val bgColor2 = config.getParam(InfoCard4x2d2WidgetSpec.P.BG_COLOR2)
    val textColor = config.getParam(InfoCard4x2d2WidgetSpec.P.TEXT_COLOR)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 获取数据（支持数值和字符串类型）
    val (info1Value, info1Display) = config.getDataSourceWithDisplay(
        InfoCard4x2d2WidgetSpec.P.INFO1_DATASOURCE,
        decimals = info1Decimals,
        defaultValue = "—"
    )
    val (info2Value, info2Display) = config.getDataSourceWithDisplay(
        InfoCard4x2d2WidgetSpec.P.INFO2_DATASOURCE,
        decimals = info2Decimals,
        defaultValue = "—"
    )

    // 调用独立的Display组件
    InfoCard4x2d2Display(
        title = title,
        iconName = icon,
        info1Label = info1Label,
        info1Value = info1Value,
        info1Display = info1Display,
        info1Unit = info1Unit,
        info2Label = info2Label,
        info2Value = info2Value,
        info2Display = info2Display,
        info2Unit = info2Unit,
        bgColor = bgColor,
        bgColor2 = bgColor2,
        textColor = textColor,
        scale = scale,
        alpha = alpha
    )
}

/**
 * 信息卡片显示组件（独立的 UI 组件，可复用于 Content 和 Preview）
 */
@Composable
fun InfoCard4x2d2Display(
    title: String,
    iconName: String,
    info1Label: String,
    info1Value: Float?,         // 数值用于计算（字符串时为 null）
    info1Display: String,       // 格式化文本用于显示
    info1Unit: String,
    info2Label: String,
    info2Value: Float?,         // 数值用于计算（字符串时为 null）
    info2Display: String,       // 格式化文本用于显示
    info2Unit: String,
    bgColor: Color,
    bgColor2: Color,
    textColor: Color,
    scale: Float = 1f,
    alpha: Float = 1f
) {
    // 创建渐变背景
    val background = if (bgColor2.alpha > 0f) {
        Brush.horizontalGradient(listOf(bgColor, bgColor2))
    } else {
        Brush.horizontalGradient(listOf(bgColor, bgColor))
    }

    Box(
        modifier = Modifier
            .width(InfoCard4x2d2WidgetSpec.P.WIDTH * scale)
            .height(InfoCard4x2d2WidgetSpec.P.HEIGHT * scale)
            .graphicsLayer(alpha = alpha)
            .background(background, RoundedCornerShape((16 * scale).dp))
            .padding((16 * scale).dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 顶部：标题和图标
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = (16 * scale).sp,
                    color = textColor.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Medium
                )

                val iconVector =
                    MaterialIconsProvider.getIconByName(iconName) ?: Icons.Outlined.Analytics
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size((28 * scale).dp)
                )
            }

            Spacer(modifier = Modifier.height((8 * scale).dp))

            // 中间：两个大数值
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 信息1
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = info1Display,
                        fontSize = (48 * scale).sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height((4 * scale).dp))
                    Text(
                        text = if (info1Unit.isNotEmpty()) "$info1Label $info1Unit" else info1Label,
                        fontSize = (14 * scale).sp,
                        color = textColor.copy(alpha = 0.8f)
                    )
                }

                // 信息2
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = info2Display,
                        fontSize = (48 * scale).sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height((4 * scale).dp))
                    Text(
                        text = if (info2Unit.isNotEmpty()) "$info2Label $info2Unit" else info2Label,
                        fontSize = (14 * scale).sp,
                        color = textColor.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun InfoCard4x2d2Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 直接使用Display组件
        InfoCard4x2d2Display(
            title = "能耗数据统计",
            iconName = "Analytics",
            info1Label = "能耗",
            info1Value = 15.8f,
            info1Display = "15.8",
            info1Unit = "kWh/100km",
            info2Label = "瞬时功率",
            info2Value = 28.4f,
            info2Display = "28.4",
            info2Unit = "kW",
            bgColor = Color(0xFFE91E63),
            bgColor2 = Color(0xFFF06292),
            textColor = Color.White
        )
    }
}
