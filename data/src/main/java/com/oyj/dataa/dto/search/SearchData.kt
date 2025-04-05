package com.oyj.dataa.dto.search

import com.oyj.dataa.dto.home.Beer
import com.oyj.dataa.dto.home.Sake
import com.oyj.dataa.dto.home.Soju
import com.oyj.dataa.dto.home.TraditionalLiquor
import com.oyj.dataa.dto.home.Whisky
import com.oyj.dataa.dto.home.Wine

data class SearchData(
    val beer: List<Beer>,
    val sake: List<Sake>,
    val soju: List<Soju>,
    val traditionalLiquor: List<TraditionalLiquor>,
    val wine: List<Wine>,
    val whisky: List<Whisky>,
)
