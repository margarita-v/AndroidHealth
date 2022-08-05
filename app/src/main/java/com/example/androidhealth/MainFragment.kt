package com.example.androidhealth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidhealth.databinding.FragmentMainBinding
import com.example.androidhealth.utils.openFullScreen
import com.example.androidhealth.utils.statusBarInsets

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        this.binding = binding
        binding.profileTitleContainer.statusBarInsets()
        binding.profileIbtn.setOnClickListener {
            openFullScreen(RootNavGraphDirections.toProfile())
        }

        val navHostFragment = childFragmentManager.findFragmentById(
            R.id.nav_host_fragment_main
        ) as NavHostFragment
        binding.navView.setupWithNavController(navHostFragment.navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}