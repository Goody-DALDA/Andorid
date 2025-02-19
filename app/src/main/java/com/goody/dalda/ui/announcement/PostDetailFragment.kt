package com.goody.dalda.ui.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.findNavController
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentPostDetailBinding
import com.goody.dalda.extention.getParcelableCompat
import com.goody.dalda.ui.model.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {
    companion object {
        const val POST_KEY = "post"

        fun newInstance() = PostDetailFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostDetailBinding
        get() = FragmentPostDetailBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val post = arguments?.getParcelableCompat(POST_KEY, Post::class.java) ?: return view
        binding.fragmentPostDetailComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    PostDetailScreen(
                        post = post,
                        onClose = { findNavController().popBackStack() },
                    )
                }
            }
        }

        return view
    }
}
