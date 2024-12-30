package com.goody.dalda.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType

@Composable
fun SearchAlcoholTab(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    categoryCount: Map<AlcoholType, Int>,
    category: List<AlcoholType>,
    onClickTap: (Int) -> Unit = {}
) {
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = Color.Black
            )
        },
        edgePadding = 0.dp
    ) {
        category.forEachIndexed { index, type ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { onClickTap(index) },
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp),
                unselectedContentColor = Color.Gray
            ) {
                Row {
                    Text(
                        text = type.alcoholName,
                        maxLines = 1,
                        fontSize = 18.sp
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Bottom),
                        text = " ${categoryCount[type]}",
                        maxLines = 1,
                        color = colorResource(id = R.color.buttonBackground),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchAlcoholTabPrev() {
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
            type = AlcoholType.WISKY,
            abv = "2.3%"
        ),
        AlcoholInfo(
            id = 3,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "WHISKEY_2",
            type = AlcoholType.WISKY,
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
            id = 5,
            imgUrl = "https://duckduckgo.com/?q=fames",
            name = "BEER_3",
            type = AlcoholType.WINE,
            abv = "2.3%"
        ),
    )

    val category = alcoholInfoList.map { it.type }.distinct()
    val categoryCount = alcoholInfoList.groupBy { it.type }
        .mapValues { it.value.size }
    val pagerState = rememberPagerState { category.size }

    SearchAlcoholTab(
        modifier = Modifier.fillMaxWidth(),
        category = category,
        pagerState = pagerState,
        categoryCount = categoryCount
    )
}
