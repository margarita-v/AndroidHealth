package com.example.androidhealth.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.androidhealth.R
import com.example.androidhealth.databinding.ViewToolbarBinding

class AppToolbar @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : Toolbar(context, attrs) {

    private val binding = ViewToolbarBinding.inflate(LayoutInflater.from(context), this)

    init {
        applyAttrs(context)
    }

    private fun applyAttrs(context: Context) {
        context.withStyledAttributes(attrs, R.styleable.AppToolbar) {
            val toolbarBg = getResourceId(
                R.styleable.AppToolbar_toolbarBg,
                R.color.componentBackground
            )
            val btnSelectorBg = getResourceId(
                R.styleable.AppToolbar_btnSelectorBg,
                R.drawable.selector_button_main_bg
            )
            binding.root.setBackgroundResource(toolbarBg)
            binding.backBtn.setBackgroundResource(btnSelectorBg)
        }
    }

    fun setOnBackClickListener(listener: () -> Unit) {
        binding.backBtn.setOnClickListener { listener() }
    }
}