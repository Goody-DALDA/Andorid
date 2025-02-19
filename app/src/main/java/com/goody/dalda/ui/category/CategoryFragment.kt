package com.goody.dalda.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    private val args: CategoryFragmentArgs by navArgs()
    private val viewModel: CategoryViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryBinding
        get() = FragmentCategoryBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    CategoryScreen(
                        alcoholType = args.category,
                        onClickCard = { alcoholData ->
                            findNavController().navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToLiquorDetailsFragment(
                                    alcoholData,
                                ),
                            )
                        },
                        onClickBackIcon = {
                            findNavController().popBackStack()
                        },
                        onClickCamera = {
                            findNavController().navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToLabelSearchActivity(),
                            )
                        },
                        modifier = Modifier,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }

    companion object {
        fun newInstance() = CategoryFragment()
    }
}
