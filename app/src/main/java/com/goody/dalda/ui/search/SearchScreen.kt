package com.goody.dalda.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.component.SearchBarComponent
import com.goody.dalda.ui.home.component.IconPack
import com.goody.dalda.ui.home.component.iconpack.IcCamera
import com.goody.dalda.ui.search.component.RecommendAlcoholList
import com.goody.dalda.ui.search.component.ResentSearch

@Composable
fun SearchScreen(
    modifier: Modifier,
    viewModel: SearchViewModel = viewModel(),
    onClickCard: (AlcoholData) -> Unit = {},
    onClickFooter: (AlcoholType) -> Unit = {},
    onClickCamera: () -> Unit = {}
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
            viewModel.searchAlcoholData(it)
            viewModel.setUiState(SearchUiState.SearchResult)
        },
        onClickCamera = onClickCamera,
        onClickCard = onClickCard,
        onClickFooter = {
            AlcoholType.entries.forEach { alcoholType ->
                if (alcoholType.alcoholName == it) {
                    onClickFooter(alcoholType)
                }
            }
        }
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    query: String,
    searchResultList: List<AlcoholData> = emptyList(),
    recentSearchWordList: List<String> = emptyList(),
    recommendAlcoholList: List<String> = emptyList(),
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit = {},
    onClickCamera: () -> Unit = {},
    onClickCard: (AlcoholData) -> Unit = {},
    onClickFooter: (String) -> Unit = {}
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
                onValueChange = onQueryChange,
                onClickTrailingIcon = onClickCamera,
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
                        alcoholDataList = searchResultList,
                        onClickCard = onClickCard,
                        onClickFooter = onClickFooter
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    TODO()
}
