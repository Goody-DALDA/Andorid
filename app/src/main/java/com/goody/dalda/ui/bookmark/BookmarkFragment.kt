package com.goody.dalda.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>() {
    private val viewModel: BookmarkViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBookmarkBinding
        get() = FragmentBookmarkBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentBookmarkComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                BookmarkScreen(
                    onClickNavigation = {
                        findNavController().popBackStack()
                    },
                    onClickCard = { alcoholData ->
                        findNavController().navigate(
                            BookmarkFragmentDirections.actionBookmarkFragmentToLiquorDetailsFragment(
                                alcoholData,
                            ),
                        )
                    },
                    modifier = Modifier,
                    viewModel = viewModel,
                )
            }
        }
    }
}
