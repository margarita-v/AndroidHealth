package com.example.androidhealth.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhealth.databinding.ViewPulseInfoItemBinding
import com.example.androidhealth.domain.PulseInfoType
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class PulseInfoTypeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    init {
        overScrollMode = OVER_SCROLL_NEVER
        layoutManager = GridLayoutManager(context, 2)
        adapter = PulseAdapter().apply { items = PulseInfoType.values().toList() }
    }
}

private class PulseAdapter : ListDelegationAdapter<List<PulseInfoType>>(pulseInfoDelegate())

private fun pulseInfoDelegate() =
    adapterDelegateViewBinding<PulseInfoType, PulseInfoType, ViewPulseInfoItemBinding>(
        { layoutInflater, root -> ViewPulseInfoItemBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.iconView.backgroundTintList =
                ContextCompat.getColorStateList(context, item.colorResId)
            binding.titleTv.setText(item.titleResId)
        }
    }