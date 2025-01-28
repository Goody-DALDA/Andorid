package com.goody.dalda.data.dto.search

import com.goody.dalda.data.dto.home.*

data class SearchData(
    val beer: List<Beer>,
    val sake: List<Sake>,
    val soju: List<Soju>,
    val traditionalLiquor: List<TraditionalLiquor>,
    val wine: List<Wine>,
    val whisky: List<whisky>,
)
