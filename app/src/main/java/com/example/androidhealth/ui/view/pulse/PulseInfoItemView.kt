package com.example.androidhealth.ui.view.pulse

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.androidhealth.R
import com.example.androidhealth.databinding.ViewPulseInfoItemBinding

class PulseInfoItemView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ViewPulseInfoItemBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = HORIZONTAL
        applyAttrs(context)
    }

    private fun applyAttrs(context: Context) {
        context.withStyledAttributes(attrs, R.styleable.PulseInfoItemView) {
            val iconTint = getResourceId(
                R.styleable.PulseInfoItemView_pulseIconTint,
                R.color.accent
            )
            val title = getResourceId(
                R.styleable.PulseInfoItemView_pulseTitle,
                -1
            )
            binding.iconView.backgroundTintList = ContextCompat.getColorStateList(context, iconTint)
            binding.titleTv.setText(title)
        }
    }
}