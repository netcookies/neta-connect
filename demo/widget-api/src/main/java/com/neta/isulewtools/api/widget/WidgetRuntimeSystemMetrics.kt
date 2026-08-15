package com.neta.isulewtools.api.widget

/**
 * Runtime-only keys injected by the host before widget rendering.
 *
 * Values under this prefix are not user configuration and must not be persisted.
 */
object WidgetRuntimeKeys {
    const val PREFIX = "__runtime."
    const val SYSTEM_METRICS = "${PREFIX}systemMetrics"
    const val SYSTEM_PERFORMANCE_WIDGET_TYPE = "system_performance_widget"
    private const val DYNAMIC_SYSTEM_PERFORMANCE_WIDGET_TYPE = "dynamic_widget-system-performance"

    fun isRuntimeKey(key: String): Boolean = key.startsWith(PREFIX)

    fun requiresSystemMetrics(widgetType: String): Boolean {
        return widgetType == SYSTEM_PERFORMANCE_WIDGET_TYPE ||
            widgetType == DYNAMIC_SYSTEM_PERFORMANCE_WIDGET_TYPE
    }
}

enum class WidgetSystemMetricKind {
    Cpu,
    Memory,
    DiskIo,
    UsbIo,
    NetworkLoss
}

enum class WidgetSystemMetricUnit {
    Percent,
    BytesPerSecond
}

fun WidgetSystemMetricKind.defaultUnit(): WidgetSystemMetricUnit {
    return when (this) {
        WidgetSystemMetricKind.Cpu,
        WidgetSystemMetricKind.Memory,
        WidgetSystemMetricKind.NetworkLoss -> WidgetSystemMetricUnit.Percent
        WidgetSystemMetricKind.DiskIo,
        WidgetSystemMetricKind.UsbIo -> WidgetSystemMetricUnit.BytesPerSecond
    }
}

enum class WidgetSystemMetricStatus {
    Available,
    Unavailable,
    Stale
}

data class WidgetSystemMetricReading(
    val kind: WidgetSystemMetricKind,
    val value: Double?,
    val unit: WidgetSystemMetricUnit = kind.defaultUnit(),
    val status: WidgetSystemMetricStatus,
    val reason: String? = null,
    val staleSinceElapsedRealtimeMs: Long? = null,
    val updatedElapsedRealtimeMs: Long
) {
    val hasUsableValue: Boolean
        get() = status == WidgetSystemMetricStatus.Available && value != null
}

data class WidgetNetworkInterfaceReading(
    val interfaceName: String,
    val reading: WidgetSystemMetricReading,
    val isAutoSelected: Boolean = false
)

data class WidgetSystemMetricsSnapshot(
    val providerId: String,
    val elapsedRealtimeMs: Long,
    val cpu: WidgetSystemMetricReading,
    val memory: WidgetSystemMetricReading,
    val diskIo: WidgetSystemMetricReading,
    val usbIo: WidgetSystemMetricReading,
    val networkLoss: WidgetSystemMetricReading = WidgetSystemMetricReading(
        kind = WidgetSystemMetricKind.NetworkLoss,
        value = null,
        status = WidgetSystemMetricStatus.Unavailable,
        reason = "network_loss_unavailable",
        updatedElapsedRealtimeMs = 0L
    ),
    val networkInterfaces: List<WidgetNetworkInterfaceReading> = emptyList()
)

fun WidgetConfig.getSystemMetricsRuntimeSnapshot(): WidgetSystemMetricsSnapshot? {
    return params[WidgetRuntimeKeys.SYSTEM_METRICS] as? WidgetSystemMetricsSnapshot
}
