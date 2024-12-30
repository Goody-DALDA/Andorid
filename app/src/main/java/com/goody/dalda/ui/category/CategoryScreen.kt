package com.goody.dalda.ui.category

import android.util.Log
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
import com.goody.dalda.data.AlcoholInfo
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
    val alcoholInfoListWithQuery by viewModel.alcoholInfoListWithQuery.collectAsStateWithLifecycle()
    val category by viewModel.category.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(pageCount = { category.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(
        key1 = isFirst.value
    ) {
        coroutineScope.launch {
            viewModel.fetchAlcoholInfo(alcoholType.toString())
            pagerState.animateScrollToPage(category.indexOf(alcoholType.alcoholName))
        }
        isFirst.value = false
    }

    CategoryScreen(
        modifier = modifier.fillMaxSize(),
        query = query,
        category = category,
        alcoholInfoList = alcoholInfoListWithQuery,
        pagerState = pagerState,
        onValueChange = { viewModel.setQuery(it) },
        onClickCategory = { index ->
            coroutineScope.launch {
                pagerState.animateScrollToPage(index)
            }
        },
        updateAlcoholInfo = { index ->
            val newAlcoholType =
                AlcoholType.entries.filter { it.alcoholName == category[index] }[0]
            viewModel.fetchAlcoholInfo(newAlcoholType.toString())
        }
    )
}
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    query: String,
    category: List<String> = emptyList(),
    alcoholInfoList: List<AlcoholInfo> = emptyList(),
    pagerState: PagerState,
    onValueChange: (String) -> Unit = {},
    onClickCategory: (Int) -> Unit = {},
    updateAlcoholInfo: (Int) -> Unit = {}
) {
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            updateAlcoholInfo(page)
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
                alcoholInfoList = alcoholInfoList
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryScreenPrev() {
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
    val category = listOf("소주", "맥주", "와인", "위스키", "전통주", "사케", "칵테일", "폭탄주")
    var query by rememberSaveable { mutableStateOf("") }
    val pagerState = rememberPagerState(pageCount = { category.size })

    CategoryScreen(
        query = query,
        category = category,
        alcoholInfoList = alcoholInfoList,
        pagerState = pagerState,
        onValueChange = { query = it }
    )
}
