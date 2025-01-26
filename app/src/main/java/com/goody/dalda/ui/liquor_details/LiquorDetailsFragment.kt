package com.goody.dalda.ui.liquor_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentLiquorDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiquorDetailsFragment : BaseFragment<FragmentLiquorDetailsBinding>() {
    private val args: LiquorDetailsFragmentArgs by navArgs()
    private val viewModel: LiquorDetailsViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLiquorDetailsBinding
        get() = FragmentLiquorDetailsBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                LiquorDetailsScreen(
                    alcoholData = args.alcoholData,
                    onClickBlog = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        startActivity(intent)
                    },
                    modifier = Modifier,
                    viewModel = viewModel,
                )
            }
        }
    }
}
