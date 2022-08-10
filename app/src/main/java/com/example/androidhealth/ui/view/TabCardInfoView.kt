package com.example.androidhealth.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import com.example.androidhealth.R
import com.example.androidhealth.databinding.ViewTabCardInfoBinding
import com.example.androidhealth.utils.resolveColor
import com.example.androidhealth.utils.toPx

/** A CardView with unique content for each tab */
class TabCardInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private val binding = ViewTabCardInfoBinding.inflate(LayoutInflater.from(context), this)

    init {
        radius = 12f.toPx
        elevation = 0f
        setCardBackgroundColor(context.resolveColor(R.color.componentBackground))
        with(16.toPx) {
            setContentPadding(this, this, this, this)
        }
    }

    fun render(
        value: String,
        message: String,
        customView: View
    ) {
        binding.valueTv.text = value
        binding.messageTv.text = message
        binding.customViewContainer.addView(customView)
    }
}