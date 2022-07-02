package com.example.androidhealth.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import com.example.androidhealth.databinding.ViewToolbarBinding

class AppToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : Toolbar(context, attrs) {

    private val binding = ViewToolbarBinding.inflate(LayoutInflater.from(context), this)

    fun setOnBackClickListener(listener: () -> Unit) {
        binding.backBtn.setOnClickListener { listener() }
    }
}