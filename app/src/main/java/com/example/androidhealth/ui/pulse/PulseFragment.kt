package com.example.androidhealth.ui.pulse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhealth.databinding.FragmentPulseBinding

class PulseFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var binding: FragmentPulseBinding? = null

    private val viewModel by viewModels<PulseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPulseBinding.inflate(inflater, container, false)
        this.binding = binding

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textPulse.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}