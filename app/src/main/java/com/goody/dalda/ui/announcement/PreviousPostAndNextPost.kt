package com.goody.dalda.ui.announcement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.model.Post
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun PreviousPostAndNextPost(
    nextPost: Post? = null,
    prevPost: Post? = null,
    onClickNext: () -> Unit = {},
    onClickPrevious: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 20.dp),
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            modifier =
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 24.dp),
        )

        if (prevPost != null) {
            PreviousPose(
                post = prevPost,
                onClick = onClickPrevious,
            )
        }

        if (nextPost != null) {
            NextPost(
                post = nextPost,
                onClick = onClickNext,
            )
        }
    }
}

@Composable
fun NextPost(
    post: Post,
    onClick: () -> Unit = {},
) {
    Row(
        modifier =
            Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 40.dp)
                .clickable { onClick() },
    ) {
        Text(
            text = "다음글",
            style = DaldaTextStyle.body3,
            color = colorResource(R.color.gray_40),
            modifier = Modifier.padding(end = 18.dp),
        )

        Text(
            text = post.title,
            style = DaldaTextStyle.body3,
            color = colorResource(id = R.color.text),
            maxLines = 1,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun PreviousPose(
    post: Post,
    onClick: () -> Unit = {},
) {
    Row(
        modifier =
            Modifier
                .padding(horizontal = 10.dp)
                .clickable { onClick() },
    ) {
        Text(
            text = "이전글",
            style = DaldaTextStyle.body3,
            color = colorResource(R.color.gray_40),
            modifier = Modifier.padding(end = 18.dp),
        )

        Text(
            text = post.title,
            style = DaldaTextStyle.body3,
            color = colorResource(id = R.color.text),
            maxLines = 1,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviousPostAndNextPostPreview() {
    val nextPost =
        Post(
            2,
            "다음글 제목",
            "다음글 입니다.",
            "2025.01.09",
            "2025.01.10",
            true,
        )

    val prevPost =
        Post(
            3,
            "이전글 제목",
            "이전글 입니다.",
            "2025.01.08",
            "2025.01.10",
            true,
        )
    MaterialTheme {
        PreviousPostAndNextPost(
            nextPost = nextPost,
            prevPost = prevPost,
        )
    }
}
