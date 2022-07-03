package com.example.androidhealth.ui.steps

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhealth.R
import com.example.androidhealth.RootNavGraphDirections
import com.example.androidhealth.databinding.FragmentStepsBinding
import com.example.androidhealth.utils.openFullScreen

class StepsFragment : Fragment(R.layout.fragment_steps) {

    private var binding: FragmentStepsBinding? = null

    private val viewModel by viewModels<StepsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStepsBinding.bind(view)
        this.binding = binding

        binding.detailsBtn.setOnClickListener {
            openFullScreen(RootNavGraphDirections.toDetails())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}