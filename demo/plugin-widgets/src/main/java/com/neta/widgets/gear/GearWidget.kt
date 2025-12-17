package com.neta.widgets.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neta.isulewtools.api.widget.ParamDef
import com.neta.isulewtools.api.widget.WidgetConfig
import com.neta.isulewtools.api.widget.WidgetParamDesc
import com.neta.isulewtools.api.widget.WidgetParamType
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getDataSourceInt
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.toHexString

/**
 * 汽车档位显示组件
 */
object GearWidgetSpec : WidgetSpec(
    type = "gear_widget",
    displayName = "档位显示",
    recommendedGrid = Pair(3, 1),
    paramSchema = WidgetParamDesc.buildParams {
        +WidgetParamDesc(
            key = P.BACKGROUND_COLOR.key,
            label = "背景色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BACKGROUND_COLOR.default.toHexString(),
            description = "档位背景颜色"
        )
        +WidgetParamDesc(
            key = P.CORNER_RADIUS.key,
            label = "圆角(dp)",
            type = WidgetParamType.FLOAT,
            defaultValue = P.CORNER_RADIUS.default,
            description = "档位显示区域的圆角大小"
        )
        +WidgetParamDesc(
            key = P.TEXT_STYLE.key,
            label = "字母样式",
            type = WidgetParamType.ENUM,
            defaultValue = P.TEXT_STYLE.default,
            options = listOf("纯色", "彩色"),
            description = "档位字母的显示样式"
        )
        +WidgetParamDesc(
            key = P.TEXT_COLOR.key,
            label = "文字颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.TEXT_COLOR.default.toHexString(),
            description = "纯色模式下字母的颜色"
        )
        +WidgetParamDesc(
            key = P.WIDTH.key,
            label = "宽度(dp)",
            type = WidgetParamType.FLOAT,
            defaultValue = P.WIDTH.default,
            description = "组件整体宽度"
        )
        +WidgetParamDesc(
            key = P.HEIGHT.key,
            label = "高度(dp)",
            type = WidgetParamType.FLOAT,
            defaultValue = P.HEIGHT.default,
            description = "组件整体高度"
        )
        +WidgetParamDesc(
            key = P.SHOW_S_GEAR.key,
            label = "显示S档",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_S_GEAR.default,
            description = "是否显示S档（运动档）"
        )
        +WidgetParamDesc(
            key = P.DATASOURCE,
            label = "档位数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = "557844012",
            options = emptyList(),
            required = true,
            description = "档位值的数据来源(1-P, 2-R, 3-N, 4-D, 5-S)"
        )
    },
    contentComposable = {
        GearWidgetContent(it)
    },
    color = Color(0xFF9C27B0),
    icon = Icons.Default.Settings
) {
    /**
     * 参数定义
     */
    object P {
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color(0xFF2C2C2E))
        val CORNER_RADIUS = ParamDef("cornerRadius", 12f)
        val TEXT_STYLE = ParamDef("textStyle", "彩色")
        val TEXT_COLOR = ParamDef("textColor", Color.White)
        val WIDTH = ParamDef("width", 200f)
        val HEIGHT = ParamDef("height", 60f)
        val SHOW_S_GEAR = ParamDef("showSGear", true)

        // 数据源参数只定义 key
        const val DATASOURCE = "datasource"

        // 档位颜色映射（彩色模式）
        val GEAR_COLORS = mapOf(
            "P" to Color(0xFFFF3B30), // 红色
            "R" to Color(0xFFFF9500), // 橙色
            "N" to Color(0xFF8E8E93), // 灰色
            "D" to Color(0xFF34C759), // 绿色
            "S" to Color(0xFF007AFF)  // 蓝色
        )
    }
}

@Composable
fun GearWidgetContent(config: WidgetConfig) {
    val backgroundColor = config.getParam(GearWidgetSpec.P.BACKGROUND_COLOR)
    val cornerRadius = config.getParam(GearWidgetSpec.P.CORNER_RADIUS)
    val textStyle = config.getParam(GearWidgetSpec.P.TEXT_STYLE)
    val textColor = config.getParam(GearWidgetSpec.P.TEXT_COLOR)
    val width = config.getParam(GearWidgetSpec.P.WIDTH)
    val height = config.getParam(GearWidgetSpec.P.HEIGHT)
    val showSGear = config.getParam(GearWidgetSpec.P.SHOW_S_GEAR)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 从数据源读取档位值
    val gearValue = config.getDataSourceInt(GearWidgetSpec.P.DATASOURCE, 0)

    GearIndicator(
        gearValue = gearValue,
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        textStyle = textStyle,
        textColor = textColor,
        width = width.dp,
        height = height.dp,
        showSGear = showSGear,
        scale = scale,
        alpha = alpha
    )
}

@Composable
fun GearIndicator(
    modifier: Modifier = Modifier,
    gearValue: Int, // 1-P, 2-R, 3-N, 4-D, 5-S
    backgroundColor: Color = GearWidgetSpec.P.BACKGROUND_COLOR.default,
    cornerRadius: Float = GearWidgetSpec.P.CORNER_RADIUS.default,
    textStyle: String = GearWidgetSpec.P.TEXT_STYLE.default,
    textColor: Color = GearWidgetSpec.P.TEXT_COLOR.default,
    width: Dp = GearWidgetSpec.P.WIDTH.default.dp,
    height: Dp = GearWidgetSpec.P.HEIGHT.default.dp,
    showSGear: Boolean = GearWidgetSpec.P.SHOW_S_GEAR.default,
    scale: Float = 1f,
    alpha: Float = 1f
) {
    val gears = if (showSGear) {
        listOf("P", "R", "N", "D", "S")
    } else {
        listOf("P", "R", "N", "D")
    }

    Box(
        modifier = modifier
            .width((width.value * scale).dp)
            .height((height.value * scale).dp)
            .graphicsLayer(alpha = alpha)
            .clip(RoundedCornerShape(cornerRadius.dp * scale))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp * scale),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            gears.forEachIndexed { index, gear ->
                val isActive = (index + 1) == gearValue

                // 确定文字颜色
                val finalTextColor = if (isActive) {
                    if (textStyle == "彩色") {
                        GearWidgetSpec.P.GEAR_COLORS[gear] ?: textColor
                    } else {
                        textColor
                    }
                } else {
                    // 非激活状态使用半透明
                    textColor.copy(alpha = 0.3f)
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = gear,
                        style = TextStyle(
                            color = finalTextColor,
                            fontSize = (height.value * 0.4f * scale).sp,
                            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GearWidgetPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("彩色模式 - 不同档位", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        // 彩色模式 - 不同档位
        GearIndicator(gearValue = 1) // P档
        GearIndicator(gearValue = 2) // R档
        GearIndicator(gearValue = 3) // N档
        GearIndicator(gearValue = 4) // D档
        GearIndicator(gearValue = 5) // S档

        Spacer(modifier = Modifier.height(16.dp))
        Text("纯色模式", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        // 纯色模式
        GearIndicator(gearValue = 4, textStyle = "纯色")

        Spacer(modifier = Modifier.height(16.dp))
        Text("不同尺寸", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        // 不同尺寸
        GearIndicator(gearValue = 4, width = 150.dp, height = 45.dp)
        GearIndicator(gearValue = 4, width = 250.dp, height = 75.dp)

        Spacer(modifier = Modifier.height(16.dp))
        Text("不同圆角", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        // 不同圆角
        GearIndicator(gearValue = 4, cornerRadius = 4f)
        GearIndicator(gearValue = 4, cornerRadius = 30f)
    }
}
