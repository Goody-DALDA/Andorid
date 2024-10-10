package com.goody.dalda.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.data.RecommendAlcohol
import com.goody.dalda.ui.component.AutoResizedText

@Composable
fun AlcoholRecommendation(
    modifier: Modifier = Modifier,
    recommendAlcoholList: List<RecommendAlcohol> = emptyList(),
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
            items(recommendAlcoholList.size) { idx ->
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                ) {
                    AsyncImage(
                        model = recommendAlcoholList[idx].imgRes,
                        contentDescription = "",
                        modifier = Modifier
                            .width(240.dp)
                            .height(176.dp)
                            .clickable { onContentsClick() },
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.FillWidth,
                    )

                    AutoResizedText(
                        text = recommendAlcoholList[idx].comment,
                        modifier = Modifier
                            .padding(
                                start = 12.dp,
                                bottom = 12.dp
                            )
                            .align(Alignment.BottomStart)
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlcoholRecommendationPreview() {
    AlcoholRecommendation(
        recommendAlcoholList = listOf(
            RecommendAlcohol(
                imgRes = "https://picsum.photos/id/217/100/100",
                comment = "이건 무슨 맛이래유?"
            ),
            RecommendAlcohol(
                imgRes = "https://picsum.photos/id/2/100/100",
                comment = "첫번째 행입니다.\n두번째 행입니다."
            ),
            RecommendAlcohol(
                imgRes = "https://picsum.photos/id/237/100/100",
                comment = "이건 어때요?"
            )
        )
    )
}
