package com.goody.dalda.ui.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.home.component.AlcoholCard

@Composable
fun AlcoholCardListComponent(
    modifier: Modifier = Modifier,
    alcoholInfoList: List<AlcoholInfo> = emptyList(),
    footer: @Composable () -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(alcoholInfoList) { alcoholInfo ->
            AlcoholCard(
                modifier = Modifier,
                imgUrl = alcoholInfo.imgUrl,
                name = alcoholInfo.name,
                category = alcoholInfo.type.alcoholName,
                alcohol = alcoholInfo.abv.toString()
            )
        }
        item(span = { GridItemSpan(2) }) {
            footer()
        }
    }
}


@Preview
@Composable
private fun AlcoholCardListComponentPrev() {
    val alcoholInfoList = listOf(
        AlcoholInfo(
            id = 1,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "SOJU_1",
            type = AlcoholType.SOJU,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 2,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "WHISKEY_1",
            type = AlcoholType.WHISKEY,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 3,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "WHISKEY_2",
            type = AlcoholType.WHISKEY,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_1",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_2",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
    )

    AlcoholCardListComponent(
        alcoholInfoList = alcoholInfoList
    )
}
