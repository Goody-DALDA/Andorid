package com.goody.dalda.ui.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.ui.AppPaddingSize
import com.goody.dalda.ui.home.component.AlcoholCard

@Composable
fun AlcoholCardListComponent(
    alcoholUIModelList: List<AlcoholUIModel>,
    onClickCard: (AlcoholUIModel) -> Unit = {},
    modifier: Modifier = Modifier,
    footer: @Composable () -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = AppPaddingSize.HORIZONTAL.dp),
        modifier = modifier.padding(vertical = 16.dp),
    ) {
        items(alcoholUIModelList) { alcoholData ->
            AlcoholCard(
                alcoholUIModel = alcoholData,
                onClick = onClickCard,
                modifier = Modifier,
            )
        }
        item(span = { GridItemSpan(2) }) {
            footer()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlcoholCardListComponentPreview() {
    val alcoholUIModelLists =
        listOf(
            AlcoholUIModel.Whisky(
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
            AlcoholUIModel.Beer(
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
                country = "독일",
            ),
            AlcoholUIModel.Sake(
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
            AlcoholUIModel.Soju(
                id = 0,
                name = "소주",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_soju,
                volume = "360ml",
                abv = "17%",
                price = 5000,
                comment = "맛있어요",
            ),
            AlcoholUIModel.Wine(
                id = 0,
                name = "와인",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_wine,
                volume = "750ml",
                abv = "",
                country = "프랑스",
                ingredient = "",
                mouthfeel = 0.0f,
                sugar = 0.0f,
                acid = 0.0f,
                type = "",
                comment = "",
                pairingFood = "",
                winery = "",
            ),
        )

    AlcoholCardListComponent(
        alcoholUIModelList = alcoholUIModelLists,
    )
}
