package com.neta.isulewtools.probe.contract

interface ProbeEntry {
    fun run(
        context: ProbeContext,
        args: List<String>
    ): ProbeResult
}
