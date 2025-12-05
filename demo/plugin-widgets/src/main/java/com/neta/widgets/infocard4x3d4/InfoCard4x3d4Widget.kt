package com.neta.widgets.infocard4x3d4

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
import androidx.compose.material.icons.outlined.Dashboard
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
import com.neta.isulewtools.api.widget.getDataSourceFloat
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.icons.MaterialIconsProvider
import com.neta.isulewtools.api.widget.toHexString

/**
 * 信息卡片小组件 (4x3, 4个信息)
 *
 * 以2x2网格显示4个信息
 * 适合展示多个相关指标
 */
object InfoCard4x3d4WidgetSpec : WidgetSpec(
    type = "info_card_4x3d4",
    displayName = "信息卡片(4x3-4项)",
    recommendedGrid = Pair(4, 3),
    icon = Icons.Outlined.Dashboard,
    color = Color(0xFF9C27B0),

    paramSchema = WidgetParamDesc.buildParams {
        // 信息1
        group("信息1 (左上)") {
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
        group("信息2 (右上)") {
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

        // 信息3
        group("信息3 (左下)") {
            +WidgetParamDesc(
                key = P.INFO3_DATASOURCE,
                label = "数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = true
            )
            +WidgetParamDesc(
                key = P.INFO3_LABEL.key,
                label = "标签",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO3_LABEL.default
            )
            +WidgetParamDesc(
                key = P.INFO3_UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO3_UNIT.default
            )
            +WidgetParamDesc(
                key = P.INFO3_DECIMALS.key,
                label = "小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.INFO3_DECIMALS.default
            )
        }

        // 信息4
        group("信息4 (右下)") {
            +WidgetParamDesc(
                key = P.INFO4_DATASOURCE,
                label = "数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = true
            )
            +WidgetParamDesc(
                key = P.INFO4_LABEL.key,
                label = "标签",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO4_LABEL.default
            )
            +WidgetParamDesc(
                key = P.INFO4_UNIT.key,
                label = "单位",
                type = WidgetParamType.STRING,
                defaultValue = P.INFO4_UNIT.default
            )
            +WidgetParamDesc(
                key = P.INFO4_DECIMALS.key,
                label = "小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.INFO4_DECIMALS.default
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

    contentComposable = { InfoCard4x3d4WidgetContent(it) }
) {
    object P {
        // 信息1
        const val INFO1_DATASOURCE = "info1_datasource"
        val INFO1_LABEL = ParamDef("info1_label", "信息1")
        val INFO1_UNIT = ParamDef("info1_unit", "")
        val INFO1_DECIMALS = ParamDef("info1_decimals", 0)

        // 信息2
        const val INFO2_DATASOURCE = "info2_datasource"
        val INFO2_LABEL = ParamDef("info2_label", "信息2")
        val INFO2_UNIT = ParamDef("info2_unit", "")
        val INFO2_DECIMALS = ParamDef("info2_decimals", 0)

        // 信息3
        const val INFO3_DATASOURCE = "info3_datasource"
        val INFO3_LABEL = ParamDef("info3_label", "信息3")
        val INFO3_UNIT = ParamDef("info3_unit", "")
        val INFO3_DECIMALS = ParamDef("info3_decimals", 0)

        // 信息4
        const val INFO4_DATASOURCE = "info4_datasource"
        val INFO4_LABEL = ParamDef("info4_label", "信息4")
        val INFO4_UNIT = ParamDef("info4_unit", "")
        val INFO4_DECIMALS = ParamDef("info4_decimals", 0)

        // 显示设置
        val TITLE = ParamDef("title", "信息卡片")
        val ICON = ParamDef("icon", "Dashboard")

        // 样式
        val BG_COLOR = ParamDef("bgColor", Color(0xFF7B1FA2))
        val BG_COLOR2 = ParamDef("bgColor2", Color(0xFF9C27B0))
        val TEXT_COLOR = ParamDef("textColor", Color(0xFFFFFFFF))

        // 默认尺寸 (4x3)
        val WIDTH = 400.dp
        val HEIGHT = 300.dp
    }
}

@Composable
fun InfoCard4x3d4WidgetContent(config: WidgetConfig) {
    // 读取配置
    val title = config.getParam(InfoCard4x3d4WidgetSpec.P.TITLE)
    val icon = config.getParam(InfoCard4x3d4WidgetSpec.P.ICON)

    val info1Label = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO1_LABEL)
    val info1Unit = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO1_UNIT)
    val info1Decimals = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO1_DECIMALS)

    val info2Label = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO2_LABEL)
    val info2Unit = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO2_UNIT)
    val info2Decimals = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO2_DECIMALS)

    val info3Label = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO3_LABEL)
    val info3Unit = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO3_UNIT)
    val info3Decimals = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO3_DECIMALS)

    val info4Label = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO4_LABEL)
    val info4Unit = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO4_UNIT)
    val info4Decimals = config.getParam(InfoCard4x3d4WidgetSpec.P.INFO4_DECIMALS)

    val bgColor = config.getParam(InfoCard4x3d4WidgetSpec.P.BG_COLOR)
    val bgColor2 = config.getParam(InfoCard4x3d4WidgetSpec.P.BG_COLOR2)
    val textColor = config.getParam(InfoCard4x3d4WidgetSpec.P.TEXT_COLOR)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 获取数据
    val info1Value = config.getDataSourceFloat(InfoCard4x3d4WidgetSpec.P.INFO1_DATASOURCE, 0f)
    val info2Value = config.getDataSourceFloat(InfoCard4x3d4WidgetSpec.P.INFO2_DATASOURCE, 0f)
    val info3Value = config.getDataSourceFloat(InfoCard4x3d4WidgetSpec.P.INFO3_DATASOURCE, 0f)
    val info4Value = config.getDataSourceFloat(InfoCard4x3d4WidgetSpec.P.INFO4_DATASOURCE, 0f)

    // 调用独立的Display组件
    InfoCard4x3d4Display(
        title = title,
        iconName = icon,
        info1Label = info1Label,
        info1Value = info1Value,
        info1Unit = info1Unit,
        info1Decimals = info1Decimals,
        info2Label = info2Label,
        info2Value = info2Value,
        info2Unit = info2Unit,
        info2Decimals = info2Decimals,
        info3Label = info3Label,
        info3Value = info3Value,
        info3Unit = info3Unit,
        info3Decimals = info3Decimals,
        info4Label = info4Label,
        info4Value = info4Value,
        info4Unit = info4Unit,
        info4Decimals = info4Decimals,
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
fun InfoCard4x3d4Display(
    title: String,
    iconName: String,
    info1Label: String,
    info1Value: Float,
    info1Unit: String,
    info1Decimals: Int,
    info2Label: String,
    info2Value: Float,
    info2Unit: String,
    info2Decimals: Int,
    info3Label: String,
    info3Value: Float,
    info3Unit: String,
    info3Decimals: Int,
    info4Label: String,
    info4Value: Float,
    info4Unit: String,
    info4Decimals: Int,
    bgColor: Color,
    bgColor2: Color,
    textColor: Color,
    scale: Float = 1f,
    alpha: Float = 1f
) {
    // 创建渐变背景
    val background = if (bgColor2.alpha > 0f) {
        Brush.verticalGradient(listOf(bgColor, bgColor2))
    } else {
        Brush.verticalGradient(listOf(bgColor, bgColor))
    }

    Box(
        modifier = Modifier
            .width(InfoCard4x3d4WidgetSpec.P.WIDTH * scale)
            .height(InfoCard4x3d4WidgetSpec.P.HEIGHT * scale)
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
                    fontSize = (18 * scale).sp,
                    color = textColor.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Medium
                )

                val iconVector =
                    MaterialIconsProvider.getIconByName(iconName) ?: Icons.Outlined.Dashboard
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size((32 * scale).dp)
                )
            }

            // 中间：2x2网格显示4个信息
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // 第一行
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoItem(
                        info1Label, info1Value, info1Unit, info1Decimals, textColor, scale,
                        modifier = Modifier.weight(1f)
                    )
                    InfoItem(
                        info2Label, info2Value, info2Unit, info2Decimals, textColor, scale,
                        modifier = Modifier.weight(1f)
                    )
                }

                // 第二行
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoItem(
                        info3Label, info3Value, info3Unit, info3Decimals, textColor, scale,
                        modifier = Modifier.weight(1f)
                    )
                    InfoItem(
                        info4Label, info4Value, info4Unit, info4Decimals, textColor, scale,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}


@Composable
private fun InfoItem(
    label: String,
    value: Float,
    unit: String,
    decimals: Int,
    textColor: Color,
    scale: Float,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding((8 * scale).dp)
    ) {
        Text(
            text = label,
            fontSize = (14 * scale).sp,
            color = textColor.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height((8 * scale).dp))
        Text(
            text = "%.${decimals}f".format(value),
            fontSize = (48 * scale).sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
        if (unit.isNotEmpty()) {
            Text(
                text = unit,
                fontSize = (14 * scale).sp,
                color = textColor.copy(alpha = 0.9f)
            )
        }
    }
}

@Preview
@Composable
fun InfoCard4x3d4Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 直接使用Display组件
        InfoCard4x3d4Display(
            title = "电机运行",
            iconName = "Dashboard",
            info1Label = "转速",
            info1Value = 3250f,
            info1Unit = "rpm",
            info1Decimals = 0,
            info2Label = "扭距",
            info2Value = 185f,
            info2Unit = "N·m",
            info2Decimals = 0,
            info3Label = "温度",
            info3Value = 65f,
            info3Unit = "°C",
            info3Decimals = 0,
            info4Label = "功率",
            info4Value = 35.2f,
            info4Unit = "kW",
            info4Decimals = 1,
            bgColor = Color(0xFF7B1FA2),
            bgColor2 = Color(0xFF9C27B0),
            textColor = Color.White
        )
    }
}
