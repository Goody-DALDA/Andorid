package com.goody.dalda.ui.liquor_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentLiquorCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiquorCollectionFragment : BaseFragment<FragmentLiquorCollectionBinding>() {
    private val viewModel: LiquorCollectionViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLiquorCollectionBinding
        get() = FragmentLiquorCollectionBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                LiquorCollectionScreen(
                    modifier = Modifier,
                    onClickButton = {
                        findNavController().popBackStack()
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}
