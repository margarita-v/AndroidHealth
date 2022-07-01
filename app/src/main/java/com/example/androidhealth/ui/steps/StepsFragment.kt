package com.example.androidhealth.ui.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhealth.databinding.FragmentStepsBinding

class StepsFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var binding: FragmentStepsBinding? = null

    private val viewModel by viewModels<StepsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStepsBinding.inflate(inflater, container, false)
        this.binding = binding

        viewModel.nightMode.observe(viewLifecycleOwner) {
            binding.themeSwitch.isChecked = it
        }
        binding.themeSwitch.setOnClickListener {
            viewModel.toggleNightMode(binding.themeSwitch.isChecked)
        }
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleNightMode(isChecked)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}