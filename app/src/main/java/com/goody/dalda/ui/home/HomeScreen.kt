package com.goody.dalda.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.RecommendAlcohol
import com.goody.dalda.ui.home.component.AlcoholCategory
import com.goody.dalda.ui.home.component.AlcoholRecommendation
import com.goody.dalda.ui.home.component.AlcoholSearchBar
import com.goody.dalda.ui.home.component.FavoriteAlcohol
import com.goody.dalda.ui.home.component.HomeTopBar
import com.goody.dalda.ui.home.component.WelcomeBanner

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    alcoholInfoList: List<AlcoholInfo> = emptyList(),
    recommendAlcoholList: List<RecommendAlcohol> = emptyList()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                onClickMenu = { /*TODO*/ },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            WelcomeBanner(
                userName = "Dalda",
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
                    .height(70.dp)
            )

            AlcoholSearchBar(
                query = "",
                onQueryChange = { /*TODO*/ },
                onSearch = { /*TODO*/ },
                active = false,
                onActiveChange = { /*TODO*/ },
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()

            )

            AlcoholCategory(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            FavoriteAlcohol(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                alcoholInfoList = alcoholInfoList,
                onActionClick = { /*TODO*/ }
            )

            AlcoholRecommendation(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                recommendAlcoholList = recommendAlcoholList,
                onActionClick = { /*TODO*/ },
                onContentsClick = { /*TODO*/ }
            )

        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.padding(horizontal = 20.dp),
        alcoholInfoList = listOf(
            AlcoholInfo(
                id = 0,
                imgUrl = "https://picsum.photos/id/217/100/100",
                name = "소주",
                type = AlcoholType.SOJU,
                abv = 20.0f,
            ),
            AlcoholInfo(
                id = 1,
                imgUrl = "https://picsum.photos/id/2/100/100",
                name = "맥주",
                type = AlcoholType.BEER,
                abv = 20.0f,
            ),
            AlcoholInfo(
                id = 2,
                imgUrl = "https://picsum.photos/id/237/100/100",
                name = "막걸리",
                type = AlcoholType.TRADITIONAL,
                abv = 20.0f,
            )
        ),
        recommendAlcoholList = listOf(
            RecommendAlcohol(
                imgRes = "https://picsum.photos/id/217/100/100",
                comment = "이건 무슨 맛이래유?"
            ),
            RecommendAlcohol(
                imgRes = "https://picsum.photos/id/2/100/100",
                comment = "첫번째 행입니다.\n두번째 행입니다."
            ),
            RecommendAlcohol(
                imgRes = "https://picsum.photos/id/237/100/100",
                comment = "이건 어때요?"
            )
        )
    )
}
