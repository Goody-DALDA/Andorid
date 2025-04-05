package com.oyj.dataa.dto.search

import com.google.gson.annotations.SerializedName
import com.oyj.dataa.dto.search.RecommendAlcoholInfo

data class RecommendAlcoholDto(
    @SerializedName("data")
    val recommendAlcoholInfoList: List<RecommendAlcoholInfo>,
    val message: String,
    val status: String,
)
