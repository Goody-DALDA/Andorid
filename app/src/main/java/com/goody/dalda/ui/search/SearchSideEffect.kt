package com.goody.dalda.ui.search

import com.goody.dalda.data.AlcoholData

sealed class SearchSideEffect {
    data object Default : SearchSideEffect()
    data class NavigateToLiquorDetail(val alcoholData: AlcoholData) : SearchSideEffect()
}