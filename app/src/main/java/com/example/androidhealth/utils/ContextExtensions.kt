package com.example.androidhealth.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.resolveColor(@ColorRes colorRes: Int): Int =
    ContextCompat.getColor(this, colorRes)

@ColorInt
fun Context.resolveAttrColor(@AttrRes attrResId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrResId, typedValue, true)
    return ContextCompat.getColor(this, typedValue.resourceId)
}

@AttrRes
fun Context.resolveAttr(@AttrRes attrResId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrResId, typedValue, true)
    return typedValue.resourceId
}