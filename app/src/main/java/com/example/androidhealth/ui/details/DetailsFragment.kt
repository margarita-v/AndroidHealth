package com.example.androidhealth.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentDetailsBinding
import com.example.androidhealth.ui.details.pager.DemoCollectionAdapter
import com.example.androidhealth.utils.statusBarInsets
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var binding: FragmentDetailsBinding? = null
    private val pagerAdapter by lazy { DemoCollectionAdapter(this) }

    private val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        this.binding = binding
        binding.toolbar.statusBarInsets()

        binding.detailsVp.adapter = pagerAdapter
        binding.detailsVp.isUserInputEnabled = false
        TabLayoutMediator(binding.detailsTl, binding.detailsVp) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()

        binding.toolbar.setOnBackClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}