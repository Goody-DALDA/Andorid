package com.goody.dalda.data.dto.home

import com.google.gson.annotations.SerializedName

data class AlcoholDataDto(
    @SerializedName("data")
    val alcoholDataList: List<Data>,
    val message: String,
    val status: String
)
