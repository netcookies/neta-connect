package com.neta.widgets.battery

import com.neta.isulewtools.api.widget.plugin.WidgetPlugin
import com.neta.widgets.arcgauge.ArcGaugeWidgetPlugin
import com.neta.widgets.barchart.BarChartWidgetPlugin
import com.neta.widgets.batterydemo.BatteryDemoWidgetPlugin
import com.neta.widgets.gear.GearWidgetPlugin
import com.neta.widgets.infochart.InfoChartWidgetPlugin
import com.neta.widgets.infocard4x2d2.InfoCard4x2d2WidgetPlugin
import com.neta.widgets.infocard4x3d3.InfoCard4x3d3WidgetPlugin
import com.neta.widgets.infocard4x3d4.InfoCard4x3d4WidgetPlugin
import com.neta.widgets.linechart.LineChartWidgetPlugin
import com.neta.widgets.minicard.MiniCardWidgetPlugin
import com.neta.widgets.progressbar.ProgressBarWidgetPlugin
import com.neta.widgets.temperature.TemperatureWidgetPlugin
import com.neta.widgets.tirepressure.TirePressureWidgetPlugin
import com.neta.widgets.vehiclelights.VehicleLightsWidgetPlugin
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class BatteryWidgetPluginContractTest {

    @Test
    fun `battery plugin metadata stays backward compatible when trust metadata omitted`() {
        val metadata = BatteryWidgetPlugin().getMetadata()

        assertEquals("widget-battery", metadata.id)
        assertEquals("1.0.7", metadata.version)
        assertEquals("通用", metadata.category)
        assertNull(metadata.signer)
        assertNull(metadata.certificateSha256)
    }

    @Test
    fun `all sample plugins should align metadata baseline`() {
        allPlugins().forEach { plugin ->
            val metadata = plugin.getMetadata()

            assertEquals("2.1.7", metadata.minAppVersion)
            assertFalse(metadata.category.isBlank())
            assertNull(metadata.signer)
            assertNull(metadata.certificateSha256)
        }
    }

    private fun allPlugins(): List<WidgetPlugin> = listOf(
        BatteryWidgetPlugin(),
        ProgressBarWidgetPlugin(),
        ArcGaugeWidgetPlugin(),
        BarChartWidgetPlugin(),
        BatteryDemoWidgetPlugin(),
        GearWidgetPlugin(),
        InfoChartWidgetPlugin(),
        InfoCard4x2d2WidgetPlugin(),
        InfoCard4x3d3WidgetPlugin(),
        InfoCard4x3d4WidgetPlugin(),
        LineChartWidgetPlugin(),
        MiniCardWidgetPlugin(),
        TemperatureWidgetPlugin(),
        TirePressureWidgetPlugin(),
        VehicleLightsWidgetPlugin(),
    )
}
