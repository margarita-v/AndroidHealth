package com.example.androidhealth.ui.details.pager

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidhealth.R

class DemoObjectFragment : Fragment(R.layout.fragment_demo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("test") }?.apply {
            val textView: TextView = view.findViewById(R.id.text_demo)
            textView.text = getInt("test").toString()
        }
    }
}
