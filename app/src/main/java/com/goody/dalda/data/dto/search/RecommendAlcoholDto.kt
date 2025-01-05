package com.goody.dalda.data.dto.search

import com.google.gson.annotations.SerializedName

data class RecommendAlcoholDto(
    @SerializedName("data")
    val recommendAlcoholInfoList: List<RecommendAlcoholInfo>,
    val message: String,
    val status: String
)
