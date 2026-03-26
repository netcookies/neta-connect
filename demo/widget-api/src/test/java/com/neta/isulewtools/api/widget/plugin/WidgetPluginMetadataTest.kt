package com.neta.isulewtools.api.widget.plugin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class WidgetPluginMetadataTest {

    @Test
    fun `legacy constructors keep trust metadata optional`() {
        val fiveArgs = WidgetPluginMetadata(
            id = "demo",
            version = "1.0.0",
            author = "tester",
            description = "demo widget",
            minAppVersion = "1.0.0"
        )
        val sixArgs = WidgetPluginMetadata(
            id = "demo",
            version = "1.0.0",
            author = "tester",
            description = "demo widget",
            minAppVersion = "1.0.0",
            category = "示例"
        )

        assertEquals("车机", fiveArgs.category)
        assertNull(fiveArgs.signer)
        assertNull(fiveArgs.certificateSha256)
        assertEquals("示例", sixArgs.category)
        assertNull(sixArgs.signer)
        assertNull(sixArgs.certificateSha256)
    }

    @Test
    fun `primary constructor keeps explicit trust metadata`() {
        val metadata = WidgetPluginMetadata(
            id = "demo",
            version = "1.0.0",
            author = "tester",
            description = "demo widget",
            minAppVersion = "1.0.0",
            category = "示例",
            signer = "release-key",
            certificateSha256 = "cert-sha"
        )

        assertEquals("release-key", metadata.signer)
        assertEquals("cert-sha", metadata.certificateSha256)
    }

    @Test
    fun `primary constructor keeps declared compatibility baseline`() {
        val metadata = WidgetPluginMetadata(
            id = "demo",
            version = "1.0.0",
            author = "tester",
            description = "demo widget",
            minAppVersion = "2.1.7",
            category = "车机"
        )

        assertEquals("2.1.7", metadata.minAppVersion)
        assertEquals("车机", metadata.category)
    }
}
