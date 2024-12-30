package com.goody.dalda.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.category.component.CategoryTab
import com.goody.dalda.ui.component.SearchBarComponent
import com.goody.dalda.ui.home.component.IconPack
import com.goody.dalda.ui.home.component.iconpack.IcCamera
import com.goody.dalda.ui.search.component.AlcoholCardListComponent
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = viewModel(),
    alcoholType: AlcoholType
) {
    val isFirst = remember { mutableStateOf(false) }
    val query by viewModel.query.collectAsStateWithLifecycle()
    val alcoholDataListWithQuery by viewModel.alcoholDataListWithQuery.collectAsStateWithLifecycle()
    val category by viewModel.category.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(pageCount = { category.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(
        key1 = isFirst.value
    ) {
        coroutineScope.launch {
            viewModel.fetchAlcoholData(alcoholType.toString())
            pagerState.animateScrollToPage(category.indexOf(alcoholType.alcoholName))
        }
        isFirst.value = false
    }

    CategoryScreen(
        modifier = modifier.fillMaxSize(),
        query = query,
        category = category,
        alcoholDataList = alcoholDataListWithQuery,
        pagerState = pagerState,
        onValueChange = { viewModel.setQuery(it) },
        onClickCategory = { index ->
            coroutineScope.launch {
                pagerState.animateScrollToPage(index)
            }
        },
        updateAlcoholData = { index ->
            val newAlcoholType =
                AlcoholType.entries.filter { it.alcoholName == category[index] }[0]
            viewModel.fetchAlcoholData(newAlcoholType.toString())
        }
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    query: String,
    category: List<String> = emptyList(),
    alcoholDataList: List<AlcoholData> = emptyList(),
    pagerState: PagerState,
    onValueChange: (String) -> Unit = {},
    onClickCategory: (Int) -> Unit = {},
    updateAlcoholData: (Int) -> Unit = {}
) {
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            updateAlcoholData(page)
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBarComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            query = query,
            placeholder = "",
            leadingIcon = Icons.Outlined.Search,
            trailingIcon = IconPack.IcCamera,
            onValueChange = onValueChange
        )

        CategoryTab(
            modifier = Modifier,
            pagerState = pagerState,
            category = category,
            onClickTab = { index ->
                onClickCategory(index)
            }
        )

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) {
            AlcoholCardListComponent(
                modifier = Modifier
                    .fillMaxSize(),
                alcoholDataList = alcoholDataList
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryScreenPrev() {
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

    val category = listOf("소주", "맥주", "와인", "위스키", "전통주", "사케", "칵테일", "폭탄주")
    var query by rememberSaveable { mutableStateOf("") }
    val pagerState = rememberPagerState(pageCount = { category.size })

    CategoryScreen(
        query = query,
        category = category,
        alcoholDataList = alcoholDataList,
        pagerState = pagerState,
        onValueChange = { query = it }
    )
}
