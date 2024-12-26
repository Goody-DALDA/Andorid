package com.goody.dalda.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType

@Composable
fun FavoriteAlcohol(
    modifier: Modifier = Modifier,
    favoriteAlcoholInfoList: List<AlcoholInfo> = emptyList(),
    onActionClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ContentsTitle(
            modifier = Modifier
                .height(30.dp),
            title = stringResource(id = R.string.text_my_favorite_alcohol),
            actionText = stringResource(id = R.string.text_whole_view),
            onActionClick = { onActionClick() }
        )
        if (favoriteAlcoholInfoList.isEmpty()) {
            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Image(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.img_alcohols),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )

                Text(
                    text = "좋아하는 술을 저장해 보세요",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }

        } else {
            LazyRow(
                modifier = Modifier
                    .height(231.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(favoriteAlcoholInfoList.size) { idx ->
                    AlcoholCard(
                        imgUrl = favoriteAlcoholInfoList[idx].imgUrl,
                        name = favoriteAlcoholInfoList[idx].name,
                        category = favoriteAlcoholInfoList[idx].type.alcoholName,
                        alcohol = favoriteAlcoholInfoList[idx].abv.toString(),
                    )
                }
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
            imgUrl = "",
            name = "소주",
            type = AlcoholType.SOJU,
            abv = "20.0%",
        ),
        AlcoholInfo(
            id = 1,
            imgUrl = "",
            name = "맥주",
            type = AlcoholType.BEER,
            abv = "4.5%",
        ),
        AlcoholInfo(
            id = 2,
            imgUrl = "",
            name = "와인",
            type = AlcoholType.WINE,
            abv = "13.0%",
        ),
        AlcoholInfo(
            id = 3,
            imgUrl = "",
            name = "위스키",
            type = AlcoholType.WISKY,
            abv = "40.0%",
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "",
            name = "위스키",
            type = AlcoholType.WISKY,
            abv = "40.0%",
        )
    )
    FavoriteAlcohol(
        favoriteAlcoholInfoList = alcoholInfoList
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyFavoriteAlcoholPreview() {
    FavoriteAlcohol()
}
