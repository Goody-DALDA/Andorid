package com.goody.dalda.ui.search

import com.goody.dalda.data.model.AlcoholUIModel

sealed class SearchSideEffect {
    data object Default : SearchSideEffect()
    data class NavigateToLiquorDetail(val alcoholUIModel: AlcoholUIModel) : SearchSideEffect()
}