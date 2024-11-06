package com.goody.dalda.ui.search.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.home.component.AlcoholCard

@Composable
fun AlcoholCardListComponent(
    modifier: Modifier = Modifier,
    alcoholInfoList: List<AlcoholInfo> = emptyList()
) {
    LazyVerticalGrid(
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
            abv = 2.3f
        ),
        AlcoholInfo(
            id = 2,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "WHISKEY_1",
            type = AlcoholType.WHISKEY,
            abv = 2.3f
        ),
        AlcoholInfo(
            id = 3,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "WHISKEY_2",
            type = AlcoholType.WHISKEY,
            abv = 2.3f
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_1",
            type = AlcoholType.BEER,
            abv = 2.3f
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_2",
            type = AlcoholType.BEER,
            abv = 2.3f
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = 2.3f
        ),
    )

    AlcoholCardListComponent(
        alcoholInfoList = alcoholInfoList
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun test() {
    val itemsList = (0..5).toList()
    val itemsIndexedList = listOf("A", "B", "C")
    val itemModifier = Modifier
        .border(1.dp, Color.Blue)
        .height(80.dp)
        .wrapContentSize()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(itemsList) {
            Text(
                "Item is $it",
                itemModifier
            )
        }
        item {
            Text(
                "Single item",
                itemModifier
            )
        }
        itemsIndexed(itemsIndexedList) { index, item ->
            Text(
                "Item at index $index is $item",
                itemModifier
            )
        }
    }
}
