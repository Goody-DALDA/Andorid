package com.goody.dalda.ui.policy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyScreen(
    modifier: Modifier = Modifier,
    viewModel: PolicyViewModel = viewModel()
) {
        Scaffold(
            modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.onBackground),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(
                        text = "개인정보 처리방침",
                        style = MaterialTheme.typography.titleMedium
                    ) },
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
            PolicyLayout(
                Modifier.padding(innerPadding),
                viewModel.getText()
            )
        }
}

@Composable
fun PolicyLayout(
    modifier: Modifier = Modifier,
    text: String
) {
    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )

    }
}

@Preview(widthDp = 400, heightDp = 900)
@Composable
fun PolicyScreenPreview() {
    MaterialTheme {
        PolicyScreen()
    }
}