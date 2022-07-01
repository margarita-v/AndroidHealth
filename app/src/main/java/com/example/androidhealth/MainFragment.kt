package com.example.androidhealth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidhealth.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        this.binding = binding

        val navHostFragment = childFragmentManager.findFragmentById(
            R.id.nav_host_fragment_main
        ) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.tabs_nav_graph)

        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}