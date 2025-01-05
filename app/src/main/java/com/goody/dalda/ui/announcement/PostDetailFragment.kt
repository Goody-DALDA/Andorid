package com.goody.dalda.ui.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.goody.dalda.R
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentPostDetailBinding

class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {

    companion object {
        fun newInstance() = PostDetailFragment()
    }

    private val viewModel: PostDetailViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostDetailBinding
        get() = FragmentPostDetailBinding::inflate

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.fragmentPostDetailComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onBackground),
                        topBar = {
                            TopBar()
                        },
                        bottomBar = {
                            PreviousPostAndNextPost()
                        }
                    ) { innerPadding ->
                        PostDetailScreen(
                            Modifier.padding(innerPadding),
                            viewModel
                        )
                    }
                }
            }
        }

        return view
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun ComposeView.TopBar() {
        CenterAlignedTopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { findNavController().popBackStack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "close"
                    )
                }
            }
        )
    }

    @Composable
    private fun PreviousPostAndNextPost() {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 24.dp)
            )

            PreviousPose()
            NextPost()
        }
    }

    @Composable
    private fun NextPost() {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 40.dp)
        ) {
            Text(
                text = "이전글",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = colorResource(R.color.gray_40)
                ),
                modifier = Modifier.padding(end = 18.dp)
            )

            Text(
                text = "Second item in list",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
        }
    }

    @Composable
    private fun PreviousPose() {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "이전글",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = colorResource(R.color.gray_40)
                ),
                modifier = Modifier.padding(end = 18.dp)
            )

            Text(
                text = "First item in list",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

