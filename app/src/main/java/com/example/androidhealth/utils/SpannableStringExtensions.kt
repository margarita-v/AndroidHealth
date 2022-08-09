package com.example.androidhealth.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes

fun SpannableString.applyTextAppearance(
    context: Context,
    @StyleRes style: Int,
    startIndex: Int = 0,
    endIndex: Int = length
): SpannableString {
    val styledSpan = TextAppearanceSpan(context, style)
    setSpan(styledSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.applyColor(
    @ColorInt colorInt: Int,
    startIndex: Int = 0,
    endIndex: Int = length
): SpannableString {
    val colorSpan = ForegroundColorSpan(colorInt)
    setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.applyOnClickAction(
    onClickAction: () -> Unit,
    startIndex: Int = 0,
    endIndex: Int = length
): SpannableString {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClickAction()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = false
        }
    }
    setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}