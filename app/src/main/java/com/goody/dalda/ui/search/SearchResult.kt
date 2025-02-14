package com.goody.dalda.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.search.component.AlcoholCardListComponent
import com.goody.dalda.ui.search.component.OtherAlcoholRecommend
import com.goody.dalda.ui.search.component.RequestAdditional
import com.goody.dalda.ui.search.component.SearchAlcoholTab
import kotlinx.coroutines.launch

@Composable
fun SearchResult(
    alcoholDataList: List<AlcoholData> = emptyList(),
    onClickRequest: () -> Unit = {},
    onClickCard: (AlcoholData) -> Unit = {},
    onClickFooter: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val category = alcoholDataList.map { getCategory(it) }.distinct()
    val categoryCount =
        alcoholDataList
            .groupBy { getCategory(it) }
            .mapValues { it.value.size }
    val pagerState = rememberPagerState(pageCount = { category.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        if (alcoholDataList.isEmpty()) {
            RequestAdditional(
                onClickRequest = onClickRequest,
            )
        } else {
            SearchAlcoholTab(
                pagerState = pagerState,
                categoryCount = categoryCount,
                category = category,
                onClickTap = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxHeight(),
            ) {
                AlcoholCardListComponent(
                    alcoholDataList =
                        alcoholDataList
                            .filter { getCategory(it) == category[pagerState.currentPage] },
                    footer = {
                        OtherAlcoholRecommend(
                            category = category[pagerState.currentPage],
                            onClick = onClickFooter,
                        )
                    },
                    onClickCard = onClickCard,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

fun getCategory(alcoholData: AlcoholData): String =
    when (alcoholData) {
        is AlcoholData.Soju -> AlcoholType.SOJU.alcoholName
        is AlcoholData.Beer -> AlcoholType.BEER.alcoholName
        is AlcoholData.Sake -> AlcoholType.SAKE.alcoholName
        is AlcoholData.Wine -> AlcoholType.WINE.alcoholName
        is AlcoholData.Whisky -> AlcoholType.WHISKY.alcoholName
        is AlcoholData.TraditionalLiquor -> AlcoholType.TRADITIONALLIQUOR.alcoholName
    }

@Preview(showBackground = true)
@Composable
private fun SearchResultPreview() {
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
    SearchResult(
        alcoholDataList = alcoholDataList,
    )
}
