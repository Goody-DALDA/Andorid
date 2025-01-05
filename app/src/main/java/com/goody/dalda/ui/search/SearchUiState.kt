package com.goody.dalda.ui.search

sealed class SearchUiState {
    data object RecentSearch : SearchUiState()
    data object Recommendation : SearchUiState()
    data object SearchResult : SearchUiState()
}

