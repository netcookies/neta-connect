package com.neta.isulewtools.api.widget

import java.io.File
import org.junit.Assert.assertTrue
import org.junit.Test

class WidgetRuntimeProguardRulesTest {

    @Test
    fun `consumer rules keep runtime DTO and plugin classloader contract stable`() {
        val rules = File("consumer-rules.pro").readText()

        assertTrue(
            rules.contains("-keep public class com.neta.isulewtools.api.widget.** { *; }")
        )
        assertTrue(
            rules.contains("-keep public interface com.neta.isulewtools.api.widget.** { *; }")
        )
        assertTrue(
            rules.contains("-keep public enum com.neta.isulewtools.api.widget.** { *; }")
        )
        assertTrue(rules.contains("-keeppackagenames com.neta.isulewtools.api.widget.**"))
        assertTrue(rules.contains("-keepclassmembernames class com.neta.isulewtools.api.widget.**"))
    }
}
