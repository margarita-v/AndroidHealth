package com.example.androidhealth.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var binding: FragmentDetailsBinding? = null

    private val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        this.binding = binding

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textDetails.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}