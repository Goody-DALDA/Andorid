package com.goody.dalda.ui.search

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
import com.goody.dalda.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    SearchScreen(
                        modifier = Modifier,
                        viewModel = viewModel,
                        onClickCard = { alcoholData ->
                            findNavController().navigate(
                                SearchFragmentDirections.actionSearchFragmentToLiquorDetailsFragment(
                                    alcoholData
                                )
                            )
                        },
                        onClickFooter = { alcoholType ->
                            findNavController().navigate(
                                SearchFragmentDirections.actionSearchFragmentToCategoryFragment(
                                    alcoholType
                                )
                            )
                        },
                        onClickCamera = {
                            findNavController().navigate(
                                SearchFragmentDirections.actionSearchFragmentToLabelSearchActivity()
                            )
                        }
                    )
                }
            }
        }
    }
}
