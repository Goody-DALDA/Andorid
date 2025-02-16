package com.goody.dalda.ui.home

sealed class HomeUiState {
    data object CommonState : HomeUiState()

    data class ErrorState(val errorMsg: String) : HomeUiState()
}
