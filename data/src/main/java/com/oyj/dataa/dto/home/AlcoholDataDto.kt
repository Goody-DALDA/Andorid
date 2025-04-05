package com.oyj.dataa.dto.home

import com.google.gson.annotations.SerializedName

data class AlcoholDataDto(
    @SerializedName("data")
    val alcoholDataList: List<Data>,
    val message: String,
    val status: String,
)
