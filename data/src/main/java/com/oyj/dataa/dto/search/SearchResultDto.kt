package com.oyj.dataa.dto.search

import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("data")
    val searchData: SearchData,
    val message: String,
    val status: String,
)
