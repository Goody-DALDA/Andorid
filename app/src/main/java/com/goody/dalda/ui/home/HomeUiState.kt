package com.goody.dalda.ui.home

sealed class HomeUiState {
    data object CommonState : HomeUiState()
    data object SearchState : HomeUiState()
    data object ErrorState : HomeUiState()
}
