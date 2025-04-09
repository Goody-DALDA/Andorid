package com.oyj.data.dto.search

import com.oyj.data.dto.home.Beer
import com.oyj.data.dto.home.Sake
import com.oyj.data.dto.home.Soju
import com.oyj.data.dto.home.TraditionalLiquor
import com.oyj.data.dto.home.Whisky
import com.oyj.data.dto.home.Wine

data class SearchData(
    val beer: List<Beer>,
    val sake: List<Sake>,
    val soju: List<Soju>,
    val traditionalLiquor: List<TraditionalLiquor>,
    val wine: List<Wine>,
    val whisky: List<Whisky>,
)
