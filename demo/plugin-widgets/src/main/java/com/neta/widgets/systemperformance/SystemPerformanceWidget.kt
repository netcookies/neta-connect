package com.neta.widgets.systemperformance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.neta.isulewtools.api.widget.WidgetRuntimeKeys
import com.neta.isulewtools.api.widget.WidgetSpec
import com.neta.isulewtools.api.widget.WidgetSystemMetricKind
import com.neta.isulewtools.api.widget.WidgetSystemMetricReading
import com.neta.isulewtools.api.widget.WidgetSystemMetricStatus
import com.neta.isulewtools.api.widget.WidgetSystemMetricUnit
import com.neta.isulewtools.api.widget.WidgetSystemMetricsSnapshot
import com.neta.isulewtools.api.widget.getAlpha
import com.neta.isulewtools.api.widget.getParam
import com.neta.isulewtools.api.widget.getScale
import com.neta.isulewtools.api.widget.getSystemMetricsRuntimeSnapshot
import com.neta.isulewtools.api.widget.toHexString
import java.util.Locale

/**
 * 系统性能小组件：60dp 横向长条，按启用槽位自然扩宽。
 */
object SystemPerformanceWidgetSpec : WidgetSpec(
    type = WidgetRuntimeKeys.SYSTEM_PERFORMANCE_WIDGET_TYPE,
    displayName = "系统性能",
    recommendedGrid = Pair(4, 1),
    paramSchema = WidgetParamDesc.buildParams {
        group("指标") {
            +WidgetParamDesc(
                key = P.SHOW_CPU.key,
                label = "CPU",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_CPU.default,
                description = "显示系统 CPU 占用率"
            )
            +WidgetParamDesc(
                key = P.SHOW_MEMORY.key,
                label = "内存",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_MEMORY.default,
                description = "显示系统内存使用率"
            )
            +WidgetParamDesc(
                key = P.SHOW_DISK_IO.key,
                label = "磁盘 IO",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_DISK_IO.default,
                description = "显示全设备磁盘 IO 吞吐"
            )
            +WidgetParamDesc(
                key = P.SHOW_USB_IO.key,
                label = "U 盘 IO",
                type = WidgetParamType.BOOL,
                defaultValue = P.SHOW_USB_IO.default,
                description = "显示可移除卷 USB IO 吞吐"
            )
        }
        group("样式") {
            +WidgetParamDesc(
                key = P.BACKGROUND_COLOR.key,
                label = "背景色",
                type = WidgetParamType.COLOR,
                defaultValue = P.BACKGROUND_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.CHIP_COLOR.key,
                label = "指标底色",
                type = WidgetParamType.COLOR,
                defaultValue = P.CHIP_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.TEXT_COLOR.key,
                label = "文字颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.TEXT_COLOR.default.toHexString()
            )
            +WidgetParamDesc(
                key = P.MUTED_TEXT_COLOR.key,
                label = "状态文字颜色",
                type = WidgetParamType.COLOR,
                defaultValue = P.MUTED_TEXT_COLOR.default.toHexString()
            )
        }
    },
    contentComposable = { SystemPerformanceWidgetContent(it) },
    color = Color(0xFF0A84FF),
    icon = Icons.Default.Speed
) {
    object P {
        val SHOW_CPU = ParamDef("showCpu", true)
        val SHOW_MEMORY = ParamDef("showMemory", true)
        val SHOW_DISK_IO = ParamDef("showDiskIo", true)
        val SHOW_USB_IO = ParamDef("showUsbIo", true)
        val BACKGROUND_COLOR = ParamDef("backgroundColor", Color(0xCC111827))
        val CHIP_COLOR = ParamDef("chipColor", Color(0x1AFFFFFF))
        val TEXT_COLOR = ParamDef("textColor", Color.White)
        val MUTED_TEXT_COLOR = ParamDef("mutedTextColor", Color(0xFFB8C2CC))

        val HEIGHT = 60.dp
    }
}

@Composable
fun SystemPerformanceWidgetContent(config: WidgetConfig) {
    val showCpu = config.getParam(SystemPerformanceWidgetSpec.P.SHOW_CPU)
    val showMemory = config.getParam(SystemPerformanceWidgetSpec.P.SHOW_MEMORY)
    val showDiskIo = config.getParam(SystemPerformanceWidgetSpec.P.SHOW_DISK_IO)
    val showUsbIo = config.getParam(SystemPerformanceWidgetSpec.P.SHOW_USB_IO)
    val backgroundColor = config.getParam(SystemPerformanceWidgetSpec.P.BACKGROUND_COLOR)
    val chipColor = config.getParam(SystemPerformanceWidgetSpec.P.CHIP_COLOR)
    val textColor = config.getParam(SystemPerformanceWidgetSpec.P.TEXT_COLOR)
    val mutedTextColor = config.getParam(SystemPerformanceWidgetSpec.P.MUTED_TEXT_COLOR)
    val scale = config.getScale()
    val alpha = config.getAlpha()
    val snapshot = config.getSystemMetricsRuntimeSnapshot()

    val slots = buildList {
        if (showCpu) add(MetricSlot("CPU", snapshot?.cpu ?: missingReading(WidgetSystemMetricKind.Cpu)))
        if (showMemory) add(MetricSlot("MEM", snapshot?.memory ?: missingReading(WidgetSystemMetricKind.Memory)))
        if (showDiskIo) add(MetricSlot("DISK", snapshot?.diskIo ?: missingReading(WidgetSystemMetricKind.DiskIo)))
        if (showUsbIo) add(MetricSlot("USB", snapshot?.usbIo ?: missingReading(WidgetSystemMetricKind.UsbIo)))
    }

    SystemPerformanceStrip(
        slots = slots.ifEmpty { listOf(MetricSlot("SYS", missingReading(WidgetSystemMetricKind.Cpu))) },
        scale = scale,
        alpha = alpha,
        backgroundColor = backgroundColor,
        chipColor = chipColor,
        textColor = textColor,
        mutedTextColor = mutedTextColor
    )
}

@Composable
fun SystemPerformanceStrip(
    slots: List<MetricSlot>,
    modifier: Modifier = Modifier,
    scale: Float = 1f,
    alpha: Float = 1f,
    backgroundColor: Color = SystemPerformanceWidgetSpec.P.BACKGROUND_COLOR.default,
    chipColor: Color = SystemPerformanceWidgetSpec.P.CHIP_COLOR.default,
    textColor: Color = SystemPerformanceWidgetSpec.P.TEXT_COLOR.default,
    mutedTextColor: Color = SystemPerformanceWidgetSpec.P.MUTED_TEXT_COLOR.default
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .height(SystemPerformanceWidgetSpec.P.HEIGHT * scale)
            .graphicsLayer(alpha = alpha)
            .clip(RoundedCornerShape(16.dp * scale))
            .background(backgroundColor)
            .padding(horizontal = 8.dp * scale, vertical = 6.dp * scale),
        horizontalArrangement = Arrangement.spacedBy(6.dp * scale),
        verticalAlignment = Alignment.CenterVertically
    ) {
        slots.forEach { slot ->
            MetricChip(
                slot = slot,
                scale = scale,
                chipColor = chipColor,
                textColor = textColor,
                mutedTextColor = mutedTextColor
            )
        }
    }
}

@Composable
private fun MetricChip(
    slot: MetricSlot,
    scale: Float,
    chipColor: Color,
    textColor: Color,
    mutedTextColor: Color
) {
    val display = slot.reading.displayText()
    val valueColor = if (slot.reading.status == WidgetSystemMetricStatus.Available) {
        textColor
    } else {
        mutedTextColor
    }

    Box(
        modifier = Modifier
            .height(48.dp * scale)
            .defaultMinSize(minWidth = 58.dp * scale)
            .clip(RoundedCornerShape(12.dp * scale))
            .background(chipColor)
            .padding(horizontal = 8.dp * scale, vertical = 4.dp * scale),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = slot.label,
                color = mutedTextColor,
                fontSize = (9 * scale).sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
            Text(
                text = display,
                color = valueColor,
                fontSize = (13 * scale).sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

data class MetricSlot(
    val label: String,
    val reading: WidgetSystemMetricReading
)

fun WidgetSystemMetricReading.displayText(): String {
    return when (status) {
        WidgetSystemMetricStatus.Available -> value?.let { formatValue(it, unit) } ?: "--"
        WidgetSystemMetricStatus.Unavailable -> reason.toShortStatus(defaultValue = "N/A")
        WidgetSystemMetricStatus.Stale -> "旧"
    }
}

private fun formatValue(value: Double, unit: WidgetSystemMetricUnit): String {
    return when (unit) {
        WidgetSystemMetricUnit.Percent -> formatPercent(value)
        WidgetSystemMetricUnit.BytesPerSecond -> formatBytesPerSecond(value)
    }
}

private fun formatPercent(value: Double): String {
    val bounded = value.coerceIn(0.0, 100.0)
    return if (bounded >= 10.0 || bounded % 1.0 == 0.0) {
        "${bounded.toInt()}%"
    } else {
        String.format(Locale.US, "%.1f%%", bounded)
    }
}

private fun formatBytesPerSecond(value: Double): String {
    val bounded = value.coerceAtLeast(0.0)
    val units = listOf("B/s", "KB/s", "MB/s", "GB/s")
    var scaled = bounded
    var unitIndex = 0
    while (scaled >= 1024.0 && unitIndex < units.lastIndex) {
        scaled /= 1024.0
        unitIndex++
    }
    return if (scaled >= 10.0 || scaled % 1.0 == 0.0) {
        "${scaled.toInt()}${units[unitIndex]}"
    } else {
        String.format(Locale.US, "%.1f%s", scaled, units[unitIndex])
    }
}

private fun String?.toShortStatus(defaultValue: String): String {
    val reason = this?.takeIf { it.isNotBlank() } ?: return defaultValue
    return when {
        reason.contains("usb", ignoreCase = true) -> "N/A"
        reason.contains("disk", ignoreCase = true) -> "N/A"
        reason.contains("no_root", ignoreCase = true) -> "N/A"
        reason.contains("warmup", ignoreCase = true) -> "WAIT"
        reason.contains("unavailable", ignoreCase = true) -> "N/A"
        reason.contains("root", ignoreCase = true) -> "ROOT"
        else -> defaultValue
    }
}

private fun missingReading(kind: WidgetSystemMetricKind): WidgetSystemMetricReading {
    return WidgetSystemMetricReading(
        kind = kind,
        value = null,
        status = WidgetSystemMetricStatus.Unavailable,
        reason = "runtime_missing",
        updatedElapsedRealtimeMs = 0L
    )
}

@Suppress("unused")
private fun runtimeKeyAnchor(): String = WidgetRuntimeKeys.SYSTEM_METRICS

@Preview
@Composable
fun SystemPerformanceWidgetPreview() {
    val snapshot = WidgetSystemMetricsSnapshot(
        providerId = "preview",
        elapsedRealtimeMs = 1_000L,
        cpu = WidgetSystemMetricReading(
            kind = WidgetSystemMetricKind.Cpu,
            value = 18.4,
            status = WidgetSystemMetricStatus.Available,
            updatedElapsedRealtimeMs = 1_000L
        ),
        memory = WidgetSystemMetricReading(
            kind = WidgetSystemMetricKind.Memory,
            value = 63.0,
            status = WidgetSystemMetricStatus.Available,
            updatedElapsedRealtimeMs = 1_000L
        ),
        diskIo = WidgetSystemMetricReading(
            kind = WidgetSystemMetricKind.DiskIo,
            value = null,
            status = WidgetSystemMetricStatus.Unavailable,
            reason = "no_root_device_disk_io_unavailable",
            updatedElapsedRealtimeMs = 1_000L
        ),
        usbIo = WidgetSystemMetricReading(
            kind = WidgetSystemMetricKind.UsbIo,
            value = null,
            status = WidgetSystemMetricStatus.Stale,
            reason = "root_stream_stale",
            staleSinceElapsedRealtimeMs = 500L,
            updatedElapsedRealtimeMs = 500L
        )
    )
    SystemPerformanceWidgetContent(
        WidgetConfig(params = mapOf(WidgetRuntimeKeys.SYSTEM_METRICS to snapshot))
    )
}
