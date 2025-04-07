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
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.AppPaddingSize
import com.goody.dalda.ui.category.component.CategoryTab
import com.goody.dalda.ui.component.SearchBarComponent
import com.goody.dalda.ui.icon.IconPack
import com.goody.dalda.ui.icon.iconpack.IcCamera
import com.goody.dalda.ui.search.component.AlcoholCardListComponent
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    alcoholType: AlcoholType,
    onClickCard: (AlcoholUIModel) -> Unit = {},
    onClickBackIcon: () -> Unit = {},
    onClickCamera: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = viewModel(),
) {
    val isFirst by viewModel.isFirst.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    val category by viewModel.category.collectAsStateWithLifecycle()
    val pagerState by viewModel.pagerState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    val alcoholDataListNew by viewModel.alcoholUIModelListMap.collectAsStateWithLifecycle()

    LaunchedEffect(
        key1 = isFirst,
    ) {
        if (isFirst) {
            pagerState.animateScrollToPage(category.indexOf(alcoholType.alcoholName))
            viewModel.setIsFirst(false)
        }
    }

    LaunchedEffect(
        key1 = pagerState,
    ) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.fetchAlcoholData(page)
        }
    }

    CategoryScreen(
        pagerState = pagerState,
        query = query,
        category = category,
        alcoholUIModelListMap = alcoholDataListNew,
        onValueChange = { viewModel.setQuery(it) },
        onClickBackIcon = onClickBackIcon,
        onClickCategory = { index ->
            coroutineScope.launch {
                pagerState.animateScrollToPage(index)
            }
        },
        onClickCard = onClickCard,
        onClickTrailingIcon = onClickCamera,
        modifier = modifier.fillMaxSize(),
    )
}

@Composable
fun CategoryScreen(
    pagerState: PagerState,
    query: String,
    category: List<String> = emptyList(),
    alcoholUIModelListMap: Map<String, List<AlcoholUIModel>> = emptyMap(),
    onValueChange: (String) -> Unit = {},
    onClickBackIcon: () -> Unit = {},
    onClickCategory: (Int) -> Unit = {},
    onClickCard: (AlcoholUIModel) -> Unit = {},
    onClickTrailingIcon: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(vertical = 8.dp),
    ) {
        SearchBarComponent(
            query = query,
            placeholder = "",
            isFocus = false,
            leadingIcon = Icons.Outlined.Search,
            trailingIcon = IconPack.IcCamera,
            onValueChange = onValueChange,
            onClickBackIcon = onClickBackIcon,
            onClickTrailingIcon = onClickTrailingIcon,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppPaddingSize.HORIZONTAL.dp),
        )

        CategoryTab(
            currentPage = pagerState.currentPage,
            category = category,
            onClickTab = { index ->
                onClickCategory(index)
            },
            modifier = Modifier,
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { currentPage ->
            AlcoholCardListComponent(
                alcoholUIModelList =
                    alcoholUIModelListMap[category[currentPage]]?.filter {
                        it.name.contains(query, ignoreCase = true)
                    } ?: emptyList(),
                onClickCard = onClickCard,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryScreenPrev() {
    val alcoholUIModelLists =
        listOf(
            AlcoholUIModel.Whisky(
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
            AlcoholUIModel.Beer(
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
            AlcoholUIModel.Sake(
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
            AlcoholUIModel.Soju(
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

    val category = listOf("소주", "맥주", "와인", "위스키", "전통주", "사케", "칵테일", "폭탄주")
    var query by rememberSaveable { mutableStateOf("") }
    val pagerState = rememberPagerState(pageCount = { category.size })

    CategoryScreen(
        query = query,
        category = category,
        pagerState = pagerState,
        onValueChange = { query = it },
    )
}
