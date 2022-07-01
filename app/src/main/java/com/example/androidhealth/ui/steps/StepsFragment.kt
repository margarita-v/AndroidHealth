package com.example.androidhealth.ui.steps

import android.app.UiModeManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidhealth.R
import com.example.androidhealth.RootNavGraphDirections
import com.example.androidhealth.databinding.FragmentStepsBinding
import com.example.androidhealth.utils.statusBarInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StepsFragment : Fragment(R.layout.fragment_steps) {

    private var binding: FragmentStepsBinding? = null

    private val viewModel by viewModels<StepsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStepsBinding.bind(view)
        this.binding = binding
        binding.detailsBtn.statusBarInsets()

        lifecycleScope.launch {
            viewModel.nightMode.collect { nightMode ->
                binding.themeSwitch.isChecked = nightMode == UiModeManager.MODE_NIGHT_YES
            }
        }
        binding.themeSwitch.setOnClickListener {
            viewModel.toggleNightMode(binding.themeSwitch.isChecked)
        }
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleNightMode(isChecked)
        }
        binding.detailsBtn.setOnClickListener {
            findNavController().navigate(RootNavGraphDirections.toDetails())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}