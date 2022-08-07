package com.example.androidhealth.ui.sleep

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentSleepBinding

class SleepFragment : Fragment(R.layout.fragment_sleep) {

    private var binding: FragmentSleepBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSleepBinding.bind(view)
        this.binding = binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}