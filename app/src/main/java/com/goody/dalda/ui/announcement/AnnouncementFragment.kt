package com.goody.dalda.ui.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentAnnouncementBinding
import com.goody.dalda.ui.onboarding.AnnouncementScreen
import com.goody.dalda.ui.onboarding.AnnouncementScreenPreview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {

    companion object {
        fun newInstance() = AnnouncementFragment()
    }

    private val viewModel: AnnouncementViewModel by viewModels()

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
                // In Compose world
                MaterialTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onBackground),
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text(text = "공지사항") },
                                navigationIcon = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            Icons.Filled.Close,
                                            contentDescription = "close"
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        AnnouncementScreen(Modifier.padding(innerPadding))
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