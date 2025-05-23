package com.goody.dalda.ui.announcement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.data.model.PostUIModel
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun AnnouncementScreen(
    onClick: (PostUIModel) -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AnnouncementViewModel = viewModel(),
) {
    val posts by viewModel.posts.collectAsStateWithLifecycle()

    AnnouncementScreen(
        postUIModelList = posts,
        onclickPost = onClick,
        onClickBack = onClickBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementScreen(
    postUIModelList: List<PostUIModel>,
    onclickPost: (PostUIModel) -> Unit = {},
    onClickBack: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground),
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "공지사항",
                        style = DaldaTextStyle.h2,
                    )
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black,
                    ),
                navigationIcon = {
                    IconButton(
                        onClick = onClickBack,
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "close",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            items(postUIModelList) { item ->
                NoticePost(
                    title = item.title,
                    date = item.updatedAt,
                    onClick = { onclickPost(item) },
                )
            }
        }
    }
}

@Composable
fun NoticePost(
    title: String,
    date: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 20.dp),
    ) {
        Text(
            text = title,
            style = DaldaTextStyle.h3,
            color = colorResource(id = R.color.text),
            modifier = Modifier.padding(bottom = 4.dp),
        )

        Text(
            text = date,
            style = DaldaTextStyle.body3,
            color = colorResource(id = R.color.gray_40),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnnouncementScreenPreview() {
    val postUIModelLists =
        listOf(
            PostUIModel(
                id = 1,
                title = "title1",
                content = "content1",
                createdAt = "2021-09-01",
                updatedAt = "2021-09-01",
                isActive = true,
            ),
            PostUIModel(
                id = 2,
                title = "title2",
                content = "content2",
                createdAt = "2021-09-02",
                updatedAt = "2021-09-02",
                isActive = true,
            ),
        )
    MaterialTheme {
        AnnouncementScreen(
            postUIModelList = postUIModelLists,
            onclickPost = {},
            onClickBack = {},
            modifier = Modifier,
        )
    }
}
