package com.neta.widgets.systemperformance

import com.neta.isulewtools.api.widget.WidgetSystemMetricKind
import com.neta.isulewtools.api.widget.WidgetSystemMetricReading
import com.neta.isulewtools.api.widget.WidgetSystemMetricStatus
import com.neta.isulewtools.api.widget.WidgetSystemMetricUnit
import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SystemPerformanceWidgetPluginContractTest {

    @Test
    fun `plugin metadata and spec keep dynamic widget baseline`() {
        val plugin = SystemPerformanceWidgetPlugin()
        val metadata = plugin.getMetadata()
        val spec = plugin.getSpec()

        assertEquals("widget-system-performance", metadata.id)
        assertEquals("2.1.7", metadata.minAppVersion)
        assertEquals("系统", metadata.category)
        assertEquals("system_performance_widget", spec.type)
        assertEquals(Pair(4, 1), spec.recommendedGrid)
        assertNotNull(spec.paramSchema.firstOrNull { it.key == "showCpu" })
        assertNotNull(spec.paramSchema.firstOrNull { it.key == "showMemory" })
        assertNotNull(spec.paramSchema.firstOrNull { it.key == "showDiskIo" })
        assertNotNull(spec.paramSchema.firstOrNull { it.key == "showUsbIo" })
        assertNotNull(spec.paramSchema.firstOrNull { it.key == "showNetworkLoss" })
        assertNotNull(spec.paramSchema.firstOrNull { it.key == "networkInterfaceMode" })
        assertNotNull(spec.paramSchema.firstOrNull { it.key == "networkInterfaceName" })
    }

    @Test
    fun `unavailable and stale render as status text instead of fake zero`() {
        val unavailable = WidgetSystemMetricReading(
            kind = WidgetSystemMetricKind.DiskIo,
            value = null,
            status = WidgetSystemMetricStatus.Unavailable,
            reason = "no_root_device_disk_io_unavailable",
            updatedElapsedRealtimeMs = 100L
        )
        val stale = unavailable.copy(
            kind = WidgetSystemMetricKind.UsbIo,
            status = WidgetSystemMetricStatus.Stale,
            reason = "system_metrics_reading_stale",
            staleSinceElapsedRealtimeMs = 80L
        )
        val available = unavailable.copy(
            kind = WidgetSystemMetricKind.Cpu,
            value = 9.5,
            unit = WidgetSystemMetricUnit.Percent,
            status = WidgetSystemMetricStatus.Available,
            reason = null
        )
        val diskThroughput = unavailable.copy(
            kind = WidgetSystemMetricKind.DiskIo,
            value = 1536.0,
            unit = WidgetSystemMetricUnit.BytesPerSecond,
            status = WidgetSystemMetricStatus.Available,
            reason = null
        )
        val networkLoss = unavailable.copy(
            kind = WidgetSystemMetricKind.NetworkLoss,
            value = 0.4,
            unit = WidgetSystemMetricUnit.Percent,
            status = WidgetSystemMetricStatus.Available,
            reason = null
        )

        assertEquals("N/A", unavailable.displayText())
        assertEquals("旧", stale.displayText())
        assertEquals("9.5%", available.displayText())
        assertEquals("1.5KB/s", diskThroughput.displayText())
        assertEquals("0.4%", networkLoss.displayText())
        assertFalse(unavailable.displayText().contains("0"))
        assertFalse(stale.displayText().contains("0"))
    }

    @Test
    fun `source keeps runtime only widget boundary`() {
        val sourceDir = File("src/main/java/com/neta/widgets/systemperformance")
        val sources = sourceDir.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .joinToString("\n") { it.readText() }

        assertTrue(sources.contains("height(SystemPerformanceWidgetSpec.P.HEIGHT * scale)"))
        assertTrue(sources.contains("wrapContentWidth"))
        assertTrue(sources.contains("WidgetRuntimeKeys.SYSTEM_METRICS"))
        assertTrue(sources.contains("networkInterfaces"))
        assertTrue(sources.contains("NETWORK_INTERFACE_MANUAL"))
        assertFalse(sources.contains("getDataSourceFloat"))
        listOf(
            "com.neta.isulewtools.core",
            "com.neta.isulewtools.systemmetrics",
            "com.neta.isulewtools.privilege",
            "/proc",
            "/sys",
            "IPrivilegeService",
            "Runtime.getRuntime",
            "ProcessBuilder",
            "android.os.Binder"
        ).forEach { forbidden ->
            assertFalse("systemperformance plugin must not contain $forbidden", sources.contains(forbidden))
        }
    }
}
