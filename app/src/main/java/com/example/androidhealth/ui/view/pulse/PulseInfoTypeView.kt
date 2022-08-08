package com.example.androidhealth.ui.view.pulse

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.androidhealth.domain.PulseInfoType

class PulseInfoTypeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val weightLayoutParams = LayoutParams(
        LayoutParams.WRAP_CONTENT,
        LayoutParams.WRAP_CONTENT,
        1.0f
    )

    init {
        orientation = VERTICAL
        render()
    }

    private fun render() {
        prepareData().forEach { (first, second) ->
            addView(
                LinearLayout(context).apply {
                    orientation = HORIZONTAL
                    addView(
                        PulseInfoItemView(context).apply {
                            render(tint = first.colorResId, title = first.titleResId)
                        },
                        weightLayoutParams
                    )
                    //todo check second
                    addView(
                        PulseInfoItemView(context).apply {
                            render(tint = second!!.colorResId, title = second!!.titleResId)
                        },
                        weightLayoutParams
                    )
                }
            )
        }
    }

    private fun prepareData(): MutableList<Pair<PulseInfoType, PulseInfoType?>> {
        val list = PulseInfoType.values().toList()
        val data: MutableList<Pair<PulseInfoType, PulseInfoType?>> =
            list.zipWithNext { first, second ->
                if (list.indexOf(first) % 2 == 0) {
                    first to second
                } else {
                    null
                }
            }.filterNotNull().toMutableList()
        if (list.size % 2 != 0) {
            data.add(data.lastIndex + 1, list.last() to null)
        }
        return data
    }
}