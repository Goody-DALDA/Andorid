package com.goody.dalda.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.search.component.AlcoholCardListComponent
import com.goody.dalda.ui.search.component.OtherAlcoholRecommend
import com.goody.dalda.ui.search.component.RequestAdditional
import com.goody.dalda.ui.search.component.SearchAlcoholTab

@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    alcoholInfoList: List<AlcoholInfo> = emptyList()
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val category = alcoholInfoList.map { it.type }.distinct()
    val categoryCount = alcoholInfoList.groupBy { it.type }
        .mapValues { it.value.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (alcoholInfoList.isEmpty()) {
            RequestAdditional()

        } else {
            SearchAlcoholTab(
                modifier = Modifier,
                selectedIndex = selectedIndex.value,
                categoryCount = categoryCount,
                category = category,
                onSelectedIndex = { selectedIndex.value = it }
            )
            AlcoholCardListComponent(
                modifier = Modifier,
                alcoholInfoList = alcoholInfoList
                    .filter { it.type == category[selectedIndex.value] },
                footer = {
                    OtherAlcoholRecommend(
                        category = category[selectedIndex.value].alcoholName
                    )
                }
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SearchResultScreenPreview() {
    val alcoholInfoList = listOf(
        AlcoholInfo(
            id = 1,
            imgUrl = "https://picsum.photos/id/237/200/300",
            name = "SOJU_1",
            type = AlcoholType.SOJU,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 2,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "WHISKEY_1",
            type = AlcoholType.WISKY,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 3,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "WHISKEY_2",
            type = AlcoholType.WISKY,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "BEER_1",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "BEER_2",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 4,
            imgUrl = "https://fastly.picsum.photos/id/237/200/300",
            name = "BEER_3",
            type = AlcoholType.BEER,
            abv = "2.3%"
        ),

        )
    SearchResult(
        alcoholInfoList = alcoholInfoList
    )
}

@Preview
@Composable
private fun EmptySearchResultScreenPreview() {
    SearchResult()
}
