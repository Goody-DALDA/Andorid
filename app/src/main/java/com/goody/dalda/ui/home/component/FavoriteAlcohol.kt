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
import com.goody.dalda.data.AlcoholData

@Composable
fun FavoriteAlcohol(
    modifier: Modifier = Modifier,
    favoriteAlcoholDataList: List<AlcoholData> = emptyList(),
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
        if (favoriteAlcoholDataList.isEmpty()) {
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
                items(favoriteAlcoholDataList.size) { idx ->
                    AlcoholCard(
                        alcoholData = favoriteAlcoholDataList[idx]
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteAlcoholPreview() {
    val alcoholDataList = listOf(
        AlcoholData.Wisky(
            id = 0,
            name = "위스키",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_whiskey,
            volume = "750ml",
            abv = "40%",
            type = "위스키",
            country = "스코틀랜드",
            price = 170000,
            taste = "써요",
            aroma = "부드러워요",
            finish = "깔끔해요",
        ),
        AlcoholData.Beer(
            id = 0,
            name = "카스",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_beer,
            volume = "355ml",
            abv = "4.5%",
            appearance = 2.28f,
            flavor = 4.4f,
            mouthfeel = 2.0f,
            aroma = 3.3f,
            type = "밀맥주",
            country = "독일"
        ),
        AlcoholData.Sake(
            id = 0,
            name = "사케",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_sake,
            volume = "750ml",
            abv = "15%",
            price = 30000,
            taste = "달아요",
            aroma = "좋아요",
            finish = "시원해요",
            country = "일본",
        ),
        AlcoholData.Soju(
            id = 0,
            name = "소주",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_soju,
            volume = "360ml",
            abv = "17%",
            price = 5000,
            comment = "맛있어요"
        )
    )
    FavoriteAlcohol(
        favoriteAlcoholDataList = alcoholDataList
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyFavoriteAlcoholPreview() {
    FavoriteAlcohol()
}
