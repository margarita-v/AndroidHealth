package com.example.androidhealth.ui.profile

import android.app.UiModeManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentProfileBinding
import com.example.androidhealth.utils.statusBarInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
        this.binding = binding

        binding.toolbar.statusBarInsets()
        binding.toolbar.setOnBackClickListener {
            findNavController().popBackStack()
        }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}