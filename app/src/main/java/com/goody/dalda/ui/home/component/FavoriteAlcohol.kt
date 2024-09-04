package com.goody.dalda.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType

@Composable
fun FavoriteAlcohol(
    alcoholInfoList: List<AlcoholInfo> = emptyList(),
    onActionClick: () -> Unit = {}
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ContentsTitle(
            title = stringResource(id = R.string.text_my_favorite_alcohol),
            actionText = stringResource(id = R.string.text_whole_view),
            onActionClick = { onActionClick() }
        )

        LazyRow {
            items(alcoholInfoList.size) { idx ->
                AlcoholCard(
                    name = alcoholInfoList[idx].name,
                    category = alcoholInfoList[idx].type.alcoholName,
                    alcohol = alcoholInfoList[idx].abv.toString(),
                    star = alcoholInfoList[idx].starScore.toString()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteAlcoholPreview() {
    val alcoholInfoList = listOf(
        AlcoholInfo(
            id = 0,
            name = "소주",
            type = AlcoholType.SOJU,
            abv = 20.0f,
            starScore = 4.5f
        ),
        AlcoholInfo(
            id = 1,
            name = "맥주",
            type = AlcoholType.BEER,
            abv = 4.5f,
            starScore = 4.0f
        ),
        AlcoholInfo(
            id = 2,
            name = "와인",
            type = AlcoholType.WINE,
            abv = 13.0f,
            starScore = 4.0f
        ),
        AlcoholInfo(
            id = 3,
            name = "위스키",
            type = AlcoholType.WHISKEY,
            abv = 40.0f,
            starScore = 4.5f
        ),
        AlcoholInfo(
            id = 4,
            name = "위스키",
            type = AlcoholType.WHISKEY,
            abv = 40.0f,
            starScore = 4.0f
        )
    )
    FavoriteAlcohol(
        alcoholInfoList = alcoholInfoList
    )
}
