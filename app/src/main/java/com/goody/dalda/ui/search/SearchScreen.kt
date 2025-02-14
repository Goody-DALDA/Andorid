package com.goody.dalda.ui.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.AppPaddingSize
import com.goody.dalda.ui.component.SearchBarComponent
import com.goody.dalda.ui.icon.IconPack
import com.goody.dalda.ui.icon.iconpack.IcCamera
import com.goody.dalda.ui.search.component.RecommendAlcoholList
import com.goody.dalda.ui.search.component.ResentSearch

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onClickBack: () -> Unit = {},
    onClickCard: (AlcoholData) -> Unit = {},
    onClickFooter: (AlcoholType) -> Unit = {},
    onClickCamera: () -> Unit = {},
    onClickRequest: () -> Unit = {},
    modifier: Modifier,
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val searchResult by viewModel.searchResultList.collectAsStateWithLifecycle()
    val recentSearchWordList by viewModel.recentSearchWordList.collectAsStateWithLifecycle()
    val recommendAlcoholList by viewModel.recommendAlcoholList.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle()

    LaunchedEffect(
        key1 = "once",
    ) {
        viewModel.fetchRecentSearchWordList(true)
    }

    LaunchedEffect(
        key1 = sideEffect
    ) {
        when (sideEffect) {
            is SearchSideEffect.Default -> {
                // noting to do
            }

            is SearchSideEffect.NavigateToLiquorDetail -> {
                onClickCard((sideEffect as SearchSideEffect.NavigateToLiquorDetail).alcoholData)
                viewModel.setSideEffect(SearchSideEffect.Default)
            }
        }
    }

    SearchScreen(
        query = query,
        uiState = uiState,
        searchResultList = searchResult,
        recentSearchWordList = recentSearchWordList,
        recommendAlcoholList = recommendAlcoholList,
        onQueryChange = {
            viewModel.queryChanged(it)
        },
        onClickBack = onClickBack,
        onSearch = {
            viewModel.search(it)
        },
        onClickCamera = onClickCamera,
        onClickRequest = onClickRequest,
        onClickRecommend = {
            viewModel.onClickRecommend(it)
        },
        onClickCard = onClickCard,
        onClickClear = {
            viewModel.clearRecentSearchWord()
        },
        onClickFooter = {
            AlcoholType.entries.forEach { alcoholType ->
                if (alcoholType.alcoholName == it) {
                    onClickFooter(alcoholType)
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    query: String,
    searchResultList: List<AlcoholData> = emptyList(),
    recentSearchWordList: List<String> = emptyList(),
    recommendAlcoholList: List<String> = emptyList(),
    onQueryChange: (String) -> Unit,
    onClickBack: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    onClickCamera: () -> Unit = {},
    onClickRequest: () -> Unit = {},
    onClickRecommend: (String) -> Unit = {},
    onClickCard: (AlcoholData) -> Unit = {},
    onClickClear: () -> Unit = {},
    onClickFooter: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.padding(vertical = 8.dp, horizontal = AppPaddingSize.HORIZONTAL.dp),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            SearchBarComponent(
                query = query,
                placeholder = "",
                isFocus = true,
                leadingIcon = Icons.Outlined.Search,
                trailingIcon = IconPack.IcCamera,
                onValueChange = onQueryChange,
                onClickBackIcon = onClickBack,
                onClickLeadingIcon = onSearch,
                onClickTrailingIcon = onClickCamera,
                modifier =
                Modifier
                    .fillMaxWidth(),
            )

            when (uiState) {
                SearchUiState.RecentSearch -> {
                    ResentSearch(
                        recentSearchWordList = recentSearchWordList,
                        onClickSearchWord = {
                            onQueryChange(it)
                            onSearch(it)
                        },
                        onClickClear = onClickClear,
                    )
                }

                SearchUiState.Recommendation -> {
                    RecommendAlcoholList(
                        modifier = Modifier.fillMaxWidth(),
                        recommendAlcoholList = recommendAlcoholList,
                        onClickWord = onClickRecommend,
                    )
                }

                SearchUiState.SearchResult -> {
                    SearchResult(
                        modifier = Modifier,
                        alcoholDataList = searchResultList,
                        onClickRequest = onClickRequest,
                        onClickCard = onClickCard,
                        onClickFooter = onClickFooter,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        uiState = SearchUiState.RecentSearch,
        query = "",
        searchResultList = emptyList(),
        recentSearchWordList = listOf("소주", "맥주", "막걸리"),
        recommendAlcoholList = listOf("소주", "맥주", "막걸리"),
        onQueryChange = {},
        onClickCard = {},
        onClickFooter = {},
        onClickCamera = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun SearchScreenSearchResultPreview() {

    val searchResultList = listOf(
        AlcoholData.Soju(
            id = 2316,
            name = "Justine Long",
            imgUrl = "https://duckduckgo.com/?q=dicunt",
            country = "Macedonia",
            volume = "quidam",
            abv = "montes",
            price = 1000,
            comment = "코멘트",
        )
    )

    SearchScreen(
        uiState = SearchUiState.SearchResult,
        query = "",
        searchResultList = searchResultList,
        recentSearchWordList = listOf("소주", "맥주", "막걸리"),
        recommendAlcoholList = listOf("소주", "맥주", "막걸리"),
        onQueryChange = {},
        onClickCard = {},
        onClickFooter = {},
        onClickCamera = {},
        modifier = Modifier,
    )
}

