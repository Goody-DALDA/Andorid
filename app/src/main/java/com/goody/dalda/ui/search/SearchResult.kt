package com.goody.dalda.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
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

    Column {
        SearchAlcoholTab(
            modifier = Modifier,
            selectedIndex = selectedIndex.value,
            categoryCount = categoryCount,
            category = category
        )
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
    SearchResult(
        alcoholInfoList = alcoholInfoList
    )
}
