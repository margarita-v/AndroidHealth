package com.example.androidhealth.ui.sleep

import android.os.Bundle
import android.text.SpannableString
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentSleepBinding
import com.example.androidhealth.utils.applyColor
import com.example.androidhealth.utils.resolveColor

class SleepFragment : Fragment(R.layout.fragment_sleep) {

    private var binding: FragmentSleepBinding? = null

    private val decorationView by lazy {
        TextView(context).apply {
            setTextAppearance(R.style.Text_Medium_20)
            setTextColor(context.resolveColor(R.color.sleepFirstDecorationColor))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 60f)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            val decoration = getString(R.string.title_sleep_decoration)
            val decorationSpannable = SpannableString(decoration)
                .applyColor(
                    context.resolveColor(R.color.sleepSecondDecorationColor),
                    2,
                    decoration.length
                )
            text = decorationSpannable
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSleepBinding.bind(view)
        this.binding = binding
        binding.sleepCardView.render(
            value = "67",
            message = getString(R.string.title_tab_sleep_message),
            customView = decorationView
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}