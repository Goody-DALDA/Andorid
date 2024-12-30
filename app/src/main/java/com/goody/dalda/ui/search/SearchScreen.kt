package com.goody.dalda.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.component.SearchBarComponent
import com.goody.dalda.ui.home.component.IconPack
import com.goody.dalda.ui.home.component.iconpack.IcCamera
import com.goody.dalda.ui.search.component.RecommendAlcoholList
import com.goody.dalda.ui.search.component.ResentSearch

@Composable
fun SearchScreen(
    modifier: Modifier,
    viewModel: SearchViewModel = viewModel()
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val searchResult by viewModel.searchResultList.collectAsStateWithLifecycle()
    val recentSearchWordList by viewModel.recentSearchWordList.collectAsStateWithLifecycle()
    val recommendAlcoholList by viewModel.recommendAlcoholList.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        query = query,
        modifier = Modifier,
        uiState = uiState,
        searchResultList = searchResult,
        recentSearchWordList = recentSearchWordList,
        recommendAlcoholList = recommendAlcoholList,
        onQueryChange = {
            viewModel.setQuery(it)
            if (it.isNotEmpty()) {
                viewModel.setUiState(SearchUiState.Recommendation)
                viewModel.updateRecommendAlcoholList()
            } else {
                viewModel.setUiState(SearchUiState.RecentSearch)
            }
        },
        onSearch = {
            viewModel.searchAlcoholInfo(it)
            viewModel.setUiState(SearchUiState.SearchResult)
        },
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    query: String,
    searchResultList: List<AlcoholInfo> = emptyList(),
    recentSearchWordList: List<String> = emptyList(),
    recommendAlcoholList: List<String> = emptyList(),
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.padding(vertical = 8.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            SearchBarComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                query = query,
                placeholder = "",
                leadingIcon = Icons.Outlined.Search,
                trailingIcon = IconPack.IcCamera,
                onClickLeadingIcon = { onSearch(it) },
                onValueChange = onQueryChange
            )

            when (uiState) {
                SearchUiState.RecentSearch -> {
                    ResentSearch(
                        recentSearchWordList = recentSearchWordList,
                        onQueryChange = onQueryChange
                    )
                }

                SearchUiState.Recommendation -> {
                    RecommendAlcoholList(
                        modifier = Modifier.fillMaxWidth(),
                        recommendAlcoholList = recommendAlcoholList,
                        onClickWord = onQueryChange
                    )
                }

                SearchUiState.SearchResult -> {
                    SearchResult(
                        modifier = Modifier,
                        alcoholInfoList = searchResultList
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun SearchScreenPreview_RecentSearch() {
    val searchResult = listOf(
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
        )
    )

    val recentSearchWordList = listOf("소주", "맥주", "막걸리")
    val recommendAlcoholList = listOf("대선 소주", "장수 막걸리", "카스")

    val uiState = SearchUiState.RecentSearch

    SearchScreen(
        query = "소주",
        modifier = Modifier,
        uiState = uiState,
        searchResultList = searchResult,
        recentSearchWordList = recentSearchWordList,
        recommendAlcoholList = recommendAlcoholList,
        onQueryChange = {},
        onSearch = {},
    )
}

@Preview
@Composable
private fun SearchScreenPreview_Recommendation() {
    val searchResult = listOf(
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
        )
    )

    val recentSearchWordList = listOf("소주", "맥주", "막걸리")
    val recommendAlcoholList = listOf("대선 소주", "장수 막걸리", "카스")

    val uiState = SearchUiState.Recommendation

    SearchScreen(
        query = "소주",
        modifier = Modifier,
        uiState = uiState,
        searchResultList = searchResult,
        recentSearchWordList = recentSearchWordList,
        recommendAlcoholList = recommendAlcoholList,
        onQueryChange = {},
        onSearch = {},
    )
}

@Preview
@Composable
private fun SearchScreenPreview_SearchResult() {
    val searchResult = listOf(
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
        )
    )

    val recentSearchWordList = listOf("소주", "맥주", "막걸리")
    val recommendAlcoholList = listOf("대선 소주", "장수 막걸리", "카스")

    val uiState = SearchUiState.SearchResult

    SearchScreen(
        query = "소주",
        modifier = Modifier,
        uiState = uiState,
        searchResultList = searchResult,
        recentSearchWordList = recentSearchWordList,
        recommendAlcoholList = recommendAlcoholList,
        onQueryChange = {},
        onSearch = {},
    )
}
