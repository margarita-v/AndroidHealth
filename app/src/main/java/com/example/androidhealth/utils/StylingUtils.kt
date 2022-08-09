package com.example.androidhealth.utils

import android.content.res.Resources

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toSp: Int
    get() = (this / Resources.getSystem().displayMetrics.scaledDensity).toInt()

val Float.toSp: Float
    get() = (this / Resources.getSystem().displayMetrics.scaledDensity)

val Float.toPx: Float
    get() = (this * Resources.getSystem().displayMetrics.density)