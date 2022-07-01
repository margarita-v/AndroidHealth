package com.example.androidhealth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidhealth.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splash.splashContainer.applyInsetter {
            type(navigationBars = true) {
                margin()
            }
        }

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_activity_main
        ) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.mobile_navigation)

        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)

        lifecycleScope.launch {
            viewModel.isReady.collect { isReady ->
                if (isReady) {
                    binding.splash.splashContainer.clearAnimation()
                    binding.splash.splashContainer.animate()
                        .alpha(0f)
                        .setDuration(200L)
                        .withEndAction { binding.container.isVisible = true }
                        .start()
                }
            }
        }
    }
}