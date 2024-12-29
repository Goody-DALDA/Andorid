package com.goody.dalda.data.dto.home

import com.google.gson.annotations.SerializedName

data class AlcoholInfoDto(
    @SerializedName("data")
    val alcoholInfoData: List<Data>,
    val message: String,
    val status: String
)
