package com.example.androidhealth.utils

const val STEPS_RECOMMENDED = 8000

fun formatSteps(steps: Int): String {
    val thousands = steps.div(1000)
    val mod = steps.mod(1000)
    return if (thousands > 0) {
        val modFormatted = when {
            mod < 10 -> "00$mod"
            mod < 100 -> "0$mod"
            else -> mod.toString()
        }
        "$thousands $modFormatted"
    } else {
        steps.toString()
    }
}