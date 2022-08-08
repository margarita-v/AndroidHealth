package com.example.androidhealth.ui.pulse

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentPulseBinding
import kotlinx.coroutines.launch

class PulseFragment : Fragment(R.layout.fragment_pulse) {

    private var binding: FragmentPulseBinding? = null

    private val viewModel by viewModels<PulseViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPulseBinding.bind(view)
        this.binding = binding

        lifecycleScope.launch {
            viewModel.pulseData.collect {
                binding.pulseCv.data = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}