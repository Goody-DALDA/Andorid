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
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.ui.search.getCategory

@Composable
fun SearchAlcoholTab(
    pagerState: PagerState,
    categoryCount: Map<String, Int>,
    category: List<String>,
    onClickTap: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    ScrollableTabRow(
        containerColor = Color.White,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = Color.Black,
            )
        },
        edgePadding = 0.dp,
        modifier = modifier,
    ) {
        category.forEachIndexed { index, type ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { onClickTap(index) },
                unselectedContentColor = Color.Gray,
                modifier =
                    Modifier
                        .background(Color.White)
                        .padding(16.dp),
            ) {
                Row {
                    Text(
                        text = type,
                        maxLines = 1,
                        fontSize = 18.sp,
                    )
                    Text(
                        text = " ${categoryCount[type]}",
                        maxLines = 1,
                        color = colorResource(id = R.color.buttonBackground),
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Bottom),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchAlcoholTabPrev() {
    val alcoholDataList =
        listOf(
            AlcoholData.Whisky(
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
                country = "독일",
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
                comment = "맛있어요",
            ),
        )

    val category = alcoholDataList.map { getCategory(it) }.distinct()
    val categoryCount =
        alcoholDataList
            .groupBy { getCategory(it) }
            .mapValues { it.value.size }
    val pagerState = rememberPagerState { category.size }

    SearchAlcoholTab(
        modifier = Modifier.fillMaxWidth(),
        category = category,
        pagerState = pagerState,
        categoryCount = categoryCount,
    )
}
