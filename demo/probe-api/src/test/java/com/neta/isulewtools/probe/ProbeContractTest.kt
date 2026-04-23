package com.neta.isulewtools.probe

import android.content.Context
import com.neta.isulewtools.probe.contract.ProbeContext
import com.neta.isulewtools.probe.contract.ProbeEntry
import com.neta.isulewtools.probe.contract.ProbeErrorCode
import com.neta.isulewtools.probe.contract.ProbeResult
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ProbeContractTest {

    @Test
    fun `probe entry should accept probe context and args and return probe result`() {
        val context = FakeProbeContext()
        val entry = object : ProbeEntry {
            override fun run(
                context: ProbeContext,
                args: List<String>
            ): ProbeResult {
                return ProbeResult.success(
                    probeId = context.probeId,
                    summary = "ok",
                    details = linkedMapOf(
                        "argCount" to args.size.toString(),
                        "workdir" to context.workingDirectory
                    )
                )
            }
        }

        val result = entry.run(context, listOf("--dry-run"))

        assertTrue(result.success)
        assertEquals("fake.probe", result.probeId)
        assertEquals("ok", result.summary)
        assertEquals("1", result.details["argCount"])
        assertEquals("/data/local/tmp", result.details["workdir"])
    }

    @Test
    fun `success result should serialize stable machine readable json`() {
        val result = ProbeResult.success(
            probeId = "oem.accessor",
            summary = "probe completed",
            details = linkedMapOf(
                "bindSuccess" to "true",
                "remoteReady" to "false"
            )
        )

        val json = JSONObject(result.toJsonString())

        assertEquals(1, json.getInt("schemaVersion"))
        assertEquals("oem.accessor", json.getString("probeId"))
        assertTrue(result.toJsonString().contains("\"success\":true"))
        assertEquals("probe completed", json.getString("summary"))
        assertEquals("true", json.getJSONObject("details").getString("bindSuccess"))
        assertEquals("false", json.getJSONObject("details").getString("remoteReady"))
        assertFalse(json.has("error"))
    }

    @Test
    fun `failure result should serialize code message and preserve details`() {
        val result = ProbeResult.failure(
            probeId = "oem.accessor",
            code = ProbeErrorCode.ENTRY_NOT_FOUND,
            message = "Probe entry class not found",
            details = linkedMapOf(
                "entryClass" to "demo.MissingProbe"
            )
        )

        val json = JSONObject(result.toJsonString())

        assertTrue(result.toJsonString().contains("\"success\":false"))
        assertEquals("oem.accessor", json.getString("probeId"))
        assertEquals("Probe failed", json.getString("summary"))
        assertEquals("ENTRY_NOT_FOUND", json.getJSONObject("error").getString("code"))
        assertEquals("Probe entry class not found", json.getJSONObject("error").getString("message"))
        assertEquals("demo.MissingProbe", json.getJSONObject("details").getString("entryClass"))
    }

    @Test
    fun `failure result should expose standard error codes for host lifecycle`() {
        val expected = setOf(
            "INVALID_ARGUMENT",
            "SOURCE_VALIDATION_FAILED",
            "ENTRY_NOT_FOUND",
            "ENTRY_INVOKE_FAILED",
            "JAR_LOAD_FAILED",
            "EXECUTION_FAILED",
            "SERIALIZATION_FAILED",
            "OUTPUT_WRITE_FAILED"
        )

        assertEquals(expected, ProbeErrorCode.values().map { it.name }.toSet())
    }

    private class FakeProbeContext : ProbeContext {
        override val probeId: String = "fake.probe"
        override val appContext: Context =
            RuntimeEnvironment.getApplication()
        override val workingDirectory: String = "/data/local/tmp"
        override val uid: Int = 2000
        override val pid: Int = 4321
        override val seLinuxContext: String = "u:r:shell:s0"
    }
}
