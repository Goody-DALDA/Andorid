package com.goody.dalda.ui.announcement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PostDetailViewModel = viewModel()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                text = viewModel.getTitle(),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = viewModel.getDate(),
                style = MaterialTheme.typography.bodySmall
            )
        }

        val scrollState = rememberScrollState()

        Text(
            text = viewModel.getContent(),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .weight(1f)
                .verticalScroll(scrollState)
        )
    }
}


@Preview
@Composable
fun PostDetailScreenPreview() {
    MaterialTheme {
        PostDetailScreen()
    }
}
