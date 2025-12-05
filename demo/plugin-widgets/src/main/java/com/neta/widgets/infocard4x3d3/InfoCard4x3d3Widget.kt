package com.neta.widgets.infocard4x3d3

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
import androidx.compose.material.icons.filled.Info
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
 * 信息卡片小组件 (4x3, 3个信息)
 *
 * 显示1个大数值 + 2个小信息
 * 适合展示主要指标和相关辅助信息
 */
object InfoCard4x3d3WidgetSpec : WidgetSpec(
    type = "info_card_4x3d3",
    displayName = "信息卡片(4x3-3项)",
    recommendedGrid = Pair(4, 3),
    icon = Icons.Default.Info,
    color = Color(0xFF4CAF50),

    paramSchema = WidgetParamDesc.buildParams {
        // 主数据源
        group("主数据") {
            +WidgetParamDesc(
                key = P.DATASOURCE,
                label = "主数据源",
                type = WidgetParamType.DATA_SOURCE,
                required = true
            )
        }

        // 信息2
        group("信息2") {
            +WidgetParamDesc(
                key = P.INFO2_DATASOURCE,
                label = "信息2数据源",
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
        group("信息3") {
            +WidgetParamDesc(
                key = P.INFO3_DATASOURCE,
                label = "信息3数据源",
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
            +WidgetParamDesc(
                key = P.UNIT.key,
                label = "主数值单位",
                type = WidgetParamType.STRING,
                defaultValue = P.UNIT.default
            )
            +WidgetParamDesc(
                key = P.DECIMALS.key,
                label = "主数值小数位数",
                type = WidgetParamType.INT,
                defaultValue = P.DECIMALS.default
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
                defaultValue = P.BG_COLOR2.default.toHexString(),
                description = "留空则不使用渐变"
            )
            +WidgetParamDesc(
                key = P.TEXT_COLOR.key,
                label = "文字颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.TEXT_COLOR.default.toHexString()
            )
        }
    },

    contentComposable = { InfoCard4x3d3WidgetContent(it) }
) {
    object P {
        // 主数据源
        const val DATASOURCE = "datasource"

        // 信息2
        const val INFO2_DATASOURCE = "info2_datasource"
        val INFO2_LABEL = ParamDef("info2_label", "信息2")
        val INFO2_UNIT = ParamDef("info2_unit", "")
        val INFO2_DECIMALS = ParamDef("info2_decimals", 1)

        // 信息3
        const val INFO3_DATASOURCE = "info3_datasource"
        val INFO3_LABEL = ParamDef("info3_label", "信息3")
        val INFO3_UNIT = ParamDef("info3_unit", "")
        val INFO3_DECIMALS = ParamDef("info3_decimals", 1)

        // 显示设置
        val TITLE = ParamDef("title", "信息卡片")
        val ICON = ParamDef("icon", "Info")
        val UNIT = ParamDef("unit", "%")
        val DECIMALS = ParamDef("decimals", 0)

        // 样式
        val BG_COLOR = ParamDef("bgColor", Color(0xFF4CAF50))
        val BG_COLOR2 = ParamDef("bgColor2", Color(0xFF66BB6A))
        val TEXT_COLOR = ParamDef("textColor", Color(0xFFFFFFFF))

        // 默认尺寸 (4x3)
        val WIDTH = 400.dp
        val HEIGHT = 300.dp
    }
}

@Composable
fun InfoCard4x3d3WidgetContent(config: WidgetConfig) {
    // 读取配置
    val title = config.getParam(InfoCard4x3d3WidgetSpec.P.TITLE)
    val icon = config.getParam(InfoCard4x3d3WidgetSpec.P.ICON)
    val unit = config.getParam(InfoCard4x3d3WidgetSpec.P.UNIT)
    val decimals = config.getParam(InfoCard4x3d3WidgetSpec.P.DECIMALS)

    val info2Label = config.getParam(InfoCard4x3d3WidgetSpec.P.INFO2_LABEL)
    val info2Unit = config.getParam(InfoCard4x3d3WidgetSpec.P.INFO2_UNIT)
    val info2Decimals = config.getParam(InfoCard4x3d3WidgetSpec.P.INFO2_DECIMALS)

    val info3Label = config.getParam(InfoCard4x3d3WidgetSpec.P.INFO3_LABEL)
    val info3Unit = config.getParam(InfoCard4x3d3WidgetSpec.P.INFO3_UNIT)
    val info3Decimals = config.getParam(InfoCard4x3d3WidgetSpec.P.INFO3_DECIMALS)

    val bgColor = config.getParam(InfoCard4x3d3WidgetSpec.P.BG_COLOR)
    val bgColor2 = config.getParam(InfoCard4x3d3WidgetSpec.P.BG_COLOR2)
    val textColor = config.getParam(InfoCard4x3d3WidgetSpec.P.TEXT_COLOR)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 获取数据
    val mainValue = config.getDataSourceFloat(InfoCard4x3d3WidgetSpec.P.DATASOURCE, 0f)
    val info2Value = config.getDataSourceFloat(InfoCard4x3d3WidgetSpec.P.INFO2_DATASOURCE, 0f)
    val info3Value = config.getDataSourceFloat(InfoCard4x3d3WidgetSpec.P.INFO3_DATASOURCE, 0f)

    // 调用独立的Display组件
    InfoCard4x3d3Display(
        title = title,
        iconName = icon,
        mainValue = mainValue,
        unit = unit,
        decimals = decimals,
        info2Label = info2Label,
        info2Value = info2Value,
        info2Unit = info2Unit,
        info2Decimals = info2Decimals,
        info3Label = info3Label,
        info3Value = info3Value,
        info3Unit = info3Unit,
        info3Decimals = info3Decimals,
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
fun InfoCard4x3d3Display(
    title: String,
    iconName: String,
    mainValue: Float,
    unit: String,
    decimals: Int,
    info2Label: String,
    info2Value: Float,
    info2Unit: String,
    info2Decimals: Int,
    info3Label: String,
    info3Value: Float,
    info3Unit: String,
    info3Decimals: Int,
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
            .width(InfoCard4x3d3WidgetSpec.P.WIDTH * scale)
            .height(InfoCard4x3d3WidgetSpec.P.HEIGHT * scale)
            .graphicsLayer(alpha = alpha)
            .background(background, RoundedCornerShape((16 * scale).dp))
            .padding((20 * scale).dp)
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

                val iconVector = MaterialIconsProvider.getIconByName(iconName) ?: Icons.Default.Info
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size((32 * scale).dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "%.${decimals}f".format(mainValue),
                    fontSize = (80 * scale).sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
                if (unit.isNotEmpty()) {
                    Spacer(modifier = Modifier.width((4 * scale).dp))
                    Text(
                        text = unit,
                        fontSize = (24 * scale).sp,
                        color = textColor.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = (14 * scale).dp)
                    )
                }
            }

            // 底部：两个小信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // 信息2
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = info2Label,
                        fontSize = (14 * scale).sp,
                        color = textColor.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height((4 * scale).dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "%.${info2Decimals}f".format(info2Value),
                            fontSize = (32 * scale).sp,
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                        if (info2Unit.isNotEmpty()) {
                            Text(
                                text = info2Unit,
                                fontSize = (16 * scale).sp,
                                color = textColor.copy(alpha = 0.9f),
                                modifier = Modifier.padding(
                                    start = (4 * scale).dp,
                                    bottom = (4 * scale).dp
                                )
                            )
                        }
                    }
                }

                // 信息3
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = info3Label,
                        fontSize = (14 * scale).sp,
                        color = textColor.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height((4 * scale).dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "%.${info3Decimals}f".format(info3Value),
                            fontSize = (32 * scale).sp,
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                        if (info3Unit.isNotEmpty()) {
                            Text(
                                text = info3Unit,
                                fontSize = (16 * scale).sp,
                                color = textColor.copy(alpha = 0.9f),
                                modifier = Modifier.padding(
                                    start = (4 * scale).dp,
                                    bottom = (4 * scale).dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun InfoCard4x3d3Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 直接使用Display组件
        InfoCard4x3d3Display(
            title = "电池状态",
            iconName = "Info",
            mainValue = 78f,
            unit = "%",
            decimals = 0,
            info2Label = "SOH健康度",
            info2Value = 95f,
            info2Unit = "%",
            info2Decimals = 0,
            info3Label = "温度",
            info3Value = 28f,
            info3Unit = "°C",
            info3Decimals = 0,
            bgColor = Color(0xFF4CAF50),
            bgColor2 = Color(0xFF66BB6A),
            textColor = Color.White
        )
    }
}
