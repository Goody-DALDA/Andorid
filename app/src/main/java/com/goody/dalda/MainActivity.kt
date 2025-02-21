package com.goody.dalda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.goody.dalda.base.BaseActivity
import com.goody.dalda.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var isReady = false

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setupPreDrawListener()
        setupNavigation()
        delaySplashScreen()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navView.visibility =
                if (isBottomNavVisible(destination.id)) View.VISIBLE else View.GONE
        }
    }

    private fun isBottomNavVisible(destinationId: Int): Boolean =
        when (destinationId) {
            R.id.navigation_home, R.id.navigation_liquor_collection -> true
            else -> false
        }

    private fun setupPreDrawListener() {
        binding.root.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean =
                    if (isReady) {
                        binding.root.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
            },
        )
    }

    private fun delaySplashScreen() {
        lifecycleScope.launch {
            delay(DELAY_SPLASH_MILLISECOND)
            isReady = true
        }
    }

    companion object {
        private const val DELAY_SPLASH_MILLISECOND = 1000L
    }
}
