package com.example.androidhealth.ui.view.pulse

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.androidhealth.R
import com.example.androidhealth.databinding.ViewPulseInfoItemBinding
import com.example.androidhealth.utils.EMPTY_RESOURCE

class PulseInfoItemView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ViewPulseInfoItemBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = HORIZONTAL
        applyAttrs(context)
    }

    fun render(@ColorRes tint: Int, @StringRes title: Int) {
        binding.iconView.backgroundTintList = ContextCompat.getColorStateList(context, tint)
        if (title != EMPTY_RESOURCE) {
            binding.titleTv.setText(title)
        }
    }

    private fun applyAttrs(context: Context) {
        context.withStyledAttributes(attrs, R.styleable.PulseInfoItemView) {
            render(
                tint = getResourceId(
                    R.styleable.PulseInfoItemView_pulseIconTint,
                    R.color.accent
                ),
                title = getResourceId(
                    R.styleable.PulseInfoItemView_pulseTitle,
                    EMPTY_RESOURCE
                )
            )
        }
    }
}