package com.neta.isulewtools.api.widget

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class WidgetRuntimeSystemMetricsTest {

    @Test
    fun `runtime keys are reserved by prefix`() {
        assertEquals("__runtime.systemMetrics", WidgetRuntimeKeys.SYSTEM_METRICS)
        assertTrue(WidgetRuntimeKeys.isRuntimeKey(WidgetRuntimeKeys.SYSTEM_METRICS))
        assertTrue(WidgetRuntimeKeys.isRuntimeKey("__runtime.anything"))
        assertFalse(WidgetRuntimeKeys.isRuntimeKey("__widgetId"))
        assertFalse(WidgetRuntimeKeys.isRuntimeKey("runtime.systemMetrics"))
        assertTrue(WidgetRuntimeKeys.requiresSystemMetrics("system_performance_widget"))
        assertTrue(WidgetRuntimeKeys.requiresSystemMetrics("dynamic_widget-system-performance"))
        assertFalse(WidgetRuntimeKeys.requiresSystemMetrics("battery_widget"))
        assertFalse(WidgetRuntimeKeys.requiresSystemMetrics("dynamic_widget-battery"))
    }

    @Test
    fun `system metrics snapshot is read from runtime params only`() {
        val snapshot = snapshot()
        val config = WidgetConfig(params = mapOf(WidgetRuntimeKeys.SYSTEM_METRICS to snapshot))

        assertSame(snapshot, config.getSystemMetricsRuntimeSnapshot())
        assertNull(WidgetConfig().getSystemMetricsRuntimeSnapshot())
    }

    @Test
    fun `availability status keeps null values explicit instead of fake zero`() {
        val unavailable = WidgetSystemMetricReading(
            kind = WidgetSystemMetricKind.DiskIo,
            value = null,
            status = WidgetSystemMetricStatus.Unavailable,
            reason = "no_root_device_disk_io_unavailable",
            updatedElapsedRealtimeMs = 200L
        )
        val stale = WidgetSystemMetricReading(
            kind = WidgetSystemMetricKind.UsbIo,
            value = null,
            status = WidgetSystemMetricStatus.Stale,
            reason = "system_metrics_reading_stale",
            staleSinceElapsedRealtimeMs = 100L,
            updatedElapsedRealtimeMs = 100L
        )

        assertFalse(unavailable.hasUsableValue)
        assertNull(unavailable.value)
        assertEquals("no_root_device_disk_io_unavailable", unavailable.reason)
        assertFalse(stale.hasUsableValue)
        assertNull(stale.value)
        assertEquals(WidgetSystemMetricUnit.BytesPerSecond, stale.unit)
        assertEquals(100L, stale.staleSinceElapsedRealtimeMs)
    }

    private fun snapshot(): WidgetSystemMetricsSnapshot {
        fun reading(kind: WidgetSystemMetricKind) = WidgetSystemMetricReading(
            kind = kind,
            value = 12.0,
            status = WidgetSystemMetricStatus.Available,
            updatedElapsedRealtimeMs = 10L
        )
        return WidgetSystemMetricsSnapshot(
            providerId = "test",
            elapsedRealtimeMs = 10L,
            cpu = reading(WidgetSystemMetricKind.Cpu),
            memory = reading(WidgetSystemMetricKind.Memory),
            diskIo = reading(WidgetSystemMetricKind.DiskIo),
            usbIo = reading(WidgetSystemMetricKind.UsbIo)
        )
    }
}
