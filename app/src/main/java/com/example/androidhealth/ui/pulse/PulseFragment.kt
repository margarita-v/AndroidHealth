package com.example.androidhealth.ui.pulse

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentPulseBinding
import com.example.androidhealth.ui.view.PulseChartView
import kotlinx.coroutines.launch

class PulseFragment : Fragment(R.layout.fragment_pulse) {

    private var binding: FragmentPulseBinding? = null

    private val viewModel by viewModels<PulseViewModel>()

    private val chartView by lazy { PulseChartView(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPulseBinding.bind(view)
        this.binding = binding

        lifecycleScope.launch {
            launch {
                viewModel.pulseData.collect {
                    binding.pulseCv.data = it
                }
            }
            launch {
                viewModel.pulseValues.collect {
                    chartView.data = it
                    binding.pulseCardView.render(
                        value = viewModel.pulseAverageValue.toString(),
                        message = getString(R.string.title_tab_pulse_message),
                        customView = chartView
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}