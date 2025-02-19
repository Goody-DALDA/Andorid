package com.goody.dalda.ui.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.goody.dalda.R
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentAnnouncementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {
    companion object {
        fun newInstance() = AnnouncementFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAnnouncementBinding
        get() = FragmentAnnouncementBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.fragmentAnnouncementComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    AnnouncementScreen(
                        onClick = { post ->
                            findNavController().navigate(
                                R.id.action_announcementFragment_to_postDetailFragment,
                                bundleOf(PostDetailFragment.POST_KEY to post),
                            )
                        },
                        onClickBack = {
                            findNavController().popBackStack()
                        },
                    )
                }
            }
        }

        return view
    }
}
