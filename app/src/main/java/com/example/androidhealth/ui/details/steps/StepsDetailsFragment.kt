package com.example.androidhealth.ui.details.steps

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentStepsDetailsBinding

class StepsDetailsFragment : Fragment(R.layout.fragment_steps_details) {

    private var binding: FragmentStepsDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStepsDetailsBinding.bind(view)
        this.binding = binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}