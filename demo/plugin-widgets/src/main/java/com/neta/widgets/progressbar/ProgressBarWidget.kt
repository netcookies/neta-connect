package com.neta.widgets.progressbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LinearScale
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import com.neta.isulewtools.api.widget.getDataSourceFloat
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.toHexString

/**
 * 进度条组件
 */
object ProgressBarWidgetSpec : WidgetSpec(
    type = "progress_bar_widget",
    displayName = "进度条",
    paramSchema = WidgetParamDesc.buildParams {
        +WidgetParamDesc(
            key = P.ORIENTATION.key,
            label = "方向",
            type = WidgetParamType.ENUM,
            defaultValue = P.ORIENTATION.default,
            options = listOf("横向", "纵向")
        )
        +WidgetParamDesc(
            key = P.PROGRESS_COLOR.key,
            label = "进度颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.PROGRESS_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.BACKGROUND_COLOR.key,
            label = "背景色",
            type = WidgetParamType.COLOR,
            defaultValue = P.BACKGROUND_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.TEXT_COLOR.key,
            label = "文字颜色",
            type = WidgetParamType.COLOR,
            defaultValue = P.TEXT_COLOR.default.toHexString()
        )
        +WidgetParamDesc(
            key = P.CORNER_RADIUS.key,
            label = "圆角(dp)",
            type = WidgetParamType.FLOAT,
            defaultValue = P.CORNER_RADIUS.default
        )
        +WidgetParamDesc(
            key = P.SHOW_TEXT.key,
            label = "显示百分比",
            type = WidgetParamType.BOOL,
            defaultValue = P.SHOW_TEXT.default
        )
        +WidgetParamDesc(
            key = P.DATASOURCE,
            label = "进度数据源",
            type = WidgetParamType.DATA_SOURCE,
            defaultValue = null,
            options = emptyList(),
            required = true
        )
    },
    contentComposable = {
        ProgressBarWidgetContent(it)
    },
    color = Color(0xFF3F51B5),
    icon = Icons.Default.LinearScale
) {
    /**
     * 参数定义
     */
    object P {
        val ORIENTATION = ParamDef("orientation", "horizontal")
        val PROGRESS_COLOR = ParamDef("progressColor", Color(0xFF34C759))
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color(0xFFDDDDDD))
        val TEXT_COLOR = ParamDef("textColor", Color.White)
        val CORNER_RADIUS = ParamDef("cornerRadius", 8f)
        val SHOW_TEXT = ParamDef("showText", true)

        // 数据源参数只定义 key
        const val DATASOURCE = "datasource"

        // 非参数常量
        val WIDTH = 200.dp
        val HEIGHT = 24.dp
    }
}

@Composable
fun ProgressBarWidgetContent(config: WidgetConfig) {
    val orientation = config.getParam(ProgressBarWidgetSpec.P.ORIENTATION)
    val progressColor = config.getParam(ProgressBarWidgetSpec.P.PROGRESS_COLOR)
    val backgroundColor = config.getParam(ProgressBarWidgetSpec.P.BACKGROUND_COLOR)
    val textColor = config.getParam(ProgressBarWidgetSpec.P.TEXT_COLOR)
    val cornerRadius = config.getParam(ProgressBarWidgetSpec.P.CORNER_RADIUS)
    val showText = config.getParam(ProgressBarWidgetSpec.P.SHOW_TEXT)

    val scale = config.getScale()
    val alpha = config.getAlpha()

    // 从注入的数据中读取进度值
    val progress = config.getDataSourceFloat(ProgressBarWidgetSpec.P.DATASOURCE, 0f)

    ProgressBarIndicator(
        progress = progress,
        orientation = orientation,
        scale = scale,
        alpha = alpha,
        progressColor = progressColor,
        backgroundColor = backgroundColor,
        textColor = textColor,
        cornerRadius = cornerRadius,
        showText = showText
    )
}

@Composable
fun ProgressBarIndicator(
    modifier: Modifier = Modifier, // 0~100
    progress: Float,
    orientation: String = ProgressBarWidgetSpec.P.ORIENTATION.default,
    scale: Float = 1f,
    alpha: Float = 1f,
    progressColor: Color = ProgressBarWidgetSpec.P.PROGRESS_COLOR.default,
    backgroundColor: Color = ProgressBarWidgetSpec.P.BACKGROUND_COLOR.default,
    textColor: Color = ProgressBarWidgetSpec.P.TEXT_COLOR.default,
    cornerRadius: Float = ProgressBarWidgetSpec.P.CORNER_RADIUS.default,
    showText: Boolean = ProgressBarWidgetSpec.P.SHOW_TEXT.default,
    width: Dp = ProgressBarWidgetSpec.P.WIDTH,
    height: Dp = ProgressBarWidgetSpec.P.HEIGHT
) {
    val currentProgress = progress.coerceIn(0f, 100f)
    val fillPercent = currentProgress / 100f

    val isHorizontal = orientation == "横向"
    val finalWidth = if (isHorizontal) width else height
    val finalHeight = if (isHorizontal) height else width

    Box(
        modifier = modifier
            .width(finalWidth * scale)
            .height(finalHeight * scale),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = alpha)
        ) {
            val radius = cornerRadius.dp.toPx() * scale

            // 绘制背景
            drawRoundRect(
                color = backgroundColor,
                topLeft = Offset.Zero,
                size = size,
                cornerRadius = CornerRadius(radius, radius)
            )

            // 绘制进度
            if (fillPercent > 0) {
                val progressSize = if (isHorizontal) {
                    Size(size.width * fillPercent, size.height)
                } else {
                    Size(size.width, size.height * (1f - fillPercent))
                }

                val progressOffset = if (isHorizontal) {
                    Offset.Zero
                } else {
                    Offset(0f, size.height * fillPercent)
                }

                drawRoundRect(
                    color = progressColor,
                    topLeft = progressOffset,
                    size = progressSize,
                    cornerRadius = CornerRadius(radius, radius)
                )
            }
        }

        // 显示百分比
        if (showText && currentProgress > 10f) {
            Text(
                text = "${currentProgress.toInt()}%",
                style = TextStyle(
                    color = textColor,
                    fontSize = (12 * scale).sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview
@Composable
fun ProgressBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("水平进度条", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        ProgressBarIndicator(progress = 0f)
        ProgressBarIndicator(progress = 25f)
        ProgressBarIndicator(progress = 50f)
        ProgressBarIndicator(progress = 75f)
        ProgressBarIndicator(progress = 100f)

        Spacer(modifier = Modifier.height(16.dp))
        Text("垂直进度条", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProgressBarIndicator(progress = 25f, orientation = "vertical")
            ProgressBarIndicator(progress = 50f, orientation = "vertical")
            ProgressBarIndicator(progress = 75f, orientation = "vertical")
            ProgressBarIndicator(progress = 100f, orientation = "vertical")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("不显示文字", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        ProgressBarIndicator(progress = 66f, showText = false)
    }
}
