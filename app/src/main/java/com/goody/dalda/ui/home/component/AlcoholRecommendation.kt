package com.goody.dalda.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R

@Composable
fun AlcoholRecommendation(
    modifier: Modifier = Modifier,
    recommendImg: List<String> = emptyList(),
    onActionClick: () -> Unit = {},
    onContentsClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ContentsTitle(
            title = stringResource(id = R.string.text_alcohol_recommendation),
            actionText = stringResource(id = R.string.text_more_contents),
            onActionClick = { onActionClick() }
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(recommendImg.size) { idx ->
                AsyncImage(
                    model = recommendImg[idx],
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    modifier = Modifier
                        .size(120.dp)
                        .clickable { onContentsClick() }
                )
            }
        }
    }
}

@Preview
@Composable
private fun AlcoholRecommendationPreview() {
    AlcoholRecommendation(
        recommendImg = listOf(
            "https://picsum.photos/id/217/100/100",
            "https://picsum.photos/id/2/100/100",
            "https://picsum.photos/id/237/100/100"
        )
    )
}
