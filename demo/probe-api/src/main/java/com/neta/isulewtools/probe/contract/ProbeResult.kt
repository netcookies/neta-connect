package com.neta.isulewtools.probe.contract

data class ProbeResult(
    val schemaVersion: Int = SCHEMA_VERSION,
    val probeId: String,
    val success: Boolean,
    val summary: String,
    val details: Map<String, String> = emptyMap(),
    val error: ProbeError? = null
) {
    data class ProbeError(
        val code: ProbeErrorCode,
        val message: String
    )

    fun toJsonString(): String {
        return try {
            buildString {
                append('{')
                appendJsonField("schemaVersion", schemaVersion.toString(), quoted = false)
                append(',')
                appendJsonField("probeId", probeId)
                append(',')
                appendJsonField("success", success.toString(), quoted = false)
                append(',')
                appendJsonField("summary", summary)
                append(',')
                append("\"details\":")
                appendJsonMap(details)
                error?.let {
                    append(',')
                    append("\"error\":{")
                    appendJsonField("code", it.code.name)
                    append(',')
                    appendJsonField("message", it.message)
                    append('}')
                }
                append('}')
            }
        } catch (throwable: Throwable) {
            val message = throwable.message ?: "serialization failed"
            """{"schemaVersion":$schemaVersion,"probeId":"${probeId.escapeJson()}","success":false,"summary":"Probe failed","details":{},"error":{"code":"${ProbeErrorCode.SERIALIZATION_FAILED.name}","message":"${message.escapeJson()}"}}"""
        }
    }

    companion object {
        const val SCHEMA_VERSION: Int = 1

        fun success(
            probeId: String,
            summary: String,
            details: Map<String, String> = emptyMap()
        ): ProbeResult {
            return ProbeResult(
                probeId = probeId,
                success = true,
                summary = summary,
                details = details
            )
        }

        fun failure(
            probeId: String,
            code: ProbeErrorCode,
            message: String,
            details: Map<String, String> = emptyMap()
        ): ProbeResult {
            return ProbeResult(
                probeId = probeId,
                success = false,
                summary = "Probe failed",
                details = details,
                error = ProbeError(
                    code = code,
                    message = message
                )
            )
        }
    }
}

private fun String.escapeJson(): String = buildString(length + 8) {
    for (ch in this@escapeJson) {
        when (ch) {
            '\\' -> append("\\\\")
            '"' -> append("\\\"")
            '\n' -> append("\\n")
            '\r' -> append("\\r")
            '\t' -> append("\\t")
            else -> append(ch)
        }
    }
}

private fun StringBuilder.appendJsonField(
    key: String,
    value: String,
    quoted: Boolean = true
) {
    append('"')
    append(key.escapeJson())
    append("\":")
    if (quoted) {
        append('"')
        append(value.escapeJson())
        append('"')
    } else {
        append(value)
    }
}

private fun StringBuilder.appendJsonMap(values: Map<String, String>) {
    append('{')
    values.entries.forEachIndexed { index, entry ->
        if (index > 0) {
            append(',')
        }
        appendJsonField(entry.key, entry.value)
    }
    append('}')
}
