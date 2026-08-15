package com.neta.isulewtools.probe.contract

import android.content.Context

interface ProbeContext {
    val probeId: String
    val appContext: Context
    val workingDirectory: String
    val uid: Int
    val pid: Int
    val seLinuxContext: String
}
