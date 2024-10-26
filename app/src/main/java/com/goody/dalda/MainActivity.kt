package com.goody.dalda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.goody.dalda.base.BaseActivity
import com.goody.dalda.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navView.visibility =
                if (isBottomNavVisible(destination.id)) View.VISIBLE else View.GONE
        }
    }

    private fun isBottomNavVisible(destinationId: Int): Boolean {
        return when (destinationId) {
            R.id.navigation_home, R.id.navigation_spirits_collection, R.id.navigation_undefined -> true
            else -> false
        }
    }
}
