package com.goody.dalda.ui.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
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


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.fragmentAnnouncementComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onBackground),
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text(text = "공지사항") },
                                navigationIcon = {
                                    IconButton(
                                        onClick = { findNavController().popBackStack() }
                                    ) {
                                        Icon(
                                            Icons.Filled.Close,
                                            contentDescription = "close"
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        AnnouncementScreen(
                            onClick = { post ->
                                findNavController().navigate(
                                    R.id.action_announcementFragment_to_postDetailFragment,
                                    bundleOf(PostDetailFragment.POST_KEY to post)
                                )
                            },
                            Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
