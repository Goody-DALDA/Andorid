package com.goody.dalda.ui.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.ui.announcement.AnnouncementViewModel

@Composable
fun AnnouncementScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnouncementViewModel = viewModel()
) {
    Column (modifier = modifier.fillMaxSize()) {
        val posts = viewModel.getNoticePosts()
        for (item in posts) {
            NoticePost(item.title, item.date)
        }
    }
}

@Composable
private fun NoticePost(
    title: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Column(modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 20.dp)
        .clickable {  }
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
        AnnouncementScreen()
    }
}

