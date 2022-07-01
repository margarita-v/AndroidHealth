package com.example.androidhealth.ui.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhealth.databinding.FragmentSleepBinding

class SleepFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var binding: FragmentSleepBinding? = null

    private val viewModel by viewModels<SleepViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSleepBinding.inflate(inflater, container, false)
        this.binding = binding

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textSleep.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}