package com.example.androidhealth.utils

import android.view.View
import dev.chrisbanes.insetter.applyInsetter

fun View.statusBarInsets() {
    applyInsetter {
        type(statusBars = true) {
            margin()
        }
    }
}

fun <T : Any> View.performIfChanged(data: T?, action: () -> Unit) {
    actionIfChanged(data) { _ -> action() }
}

fun <T1 : Any, T2 : Any> View.performIfChanged(data1: T1?, data2: T2?, action: () -> Unit) {
    actionIfChanged(data1, data2) { _, _ -> action() }
}

fun <T1 : Any, T2 : Any, T3 : Any> View.performIfChanged(
    data1: T1?,
    data2: T2?,
    data3: T3?,
    action: () -> Unit
) {
    actionIfChanged(data1, data2, data3) { _, _, _ -> action() }
}

fun <T : View, R> T.actionIfChanged(data: R?, action: T.(data: R?) -> Unit) {
    this.actionIfChanged(data, null, null, null) { d, _, _, _ -> action(d) }
}

fun <T : View, R1, R2> T.actionIfChanged(data1: R1?, data2: R2?, action: T.(R1?, R2?) -> Unit) {
    this.actionIfChanged(data1, data2, null, null) { d1, d2, _, _ -> action(d1, d2) }
}

fun <T : View, R1, R2, R3> T.actionIfChanged(
    data1: R1?,
    data2: R2?,
    data3: R3? = null,
    action: T.(R1?, R2?, R3?) -> Unit
) {
    this.actionIfChanged(data1, data2, data3, null) { d1, d2, d3, _ -> action(d1, d2, d3) }
}

fun <T : View, R1, R2, R3, R4> T.actionIfChanged(
    data1: R1?,
    data2: R2? = null,
    data3: R3? = null,
    data4: R4? = null,
    action: T.(data1: R1?, data2: R2?, data3: R3?, data4: R4?) -> Unit
) {
    val hash = data1?.hashCode()
        ?.plus(data2?.hashCode() ?: 0)
        ?.plus(data3?.hashCode() ?: 0)
        ?.plus(data4?.hashCode() ?: 0)
    if (this.tag != hash) {
        action(data1, data2, data3, data4)
        this.tag = hash
    }
}