package com.goody.dalda.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                MaterialTheme {
                    HomeScreen(
                        modifier = Modifier,
                        viewModel = viewModel,
                        onClickAlcohol = {
                            findNavController().navigate(
                                HomeFragmentDirections.actionNavigationHomeToCategoryFragment(it)
                            )
                        },
                        onClickSearchBar = {
                            findNavController().navigate(
                                HomeFragmentDirections.actionNavigationHomeToSearchFragment()
                            )
                        }
                    )
                }
            }
        }
    }
}
