package com.goody.dalda.ui.announcement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.ui.model.Post

@Composable
fun AnnouncementScreen(
    onClick: (Post) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AnnouncementViewModel = viewModel()
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(viewModel.getNoticePosts()) { item ->
            NoticePost(item.title, item.updatedAt, onClick = { onClick(item) })
        }
    }
}

@Composable
fun NoticePost(
    title: String,
    date: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun AnnouncementScreenPreview() {
    MaterialTheme {
        AnnouncementScreen(
            onClick = {}
        )
    }
}

