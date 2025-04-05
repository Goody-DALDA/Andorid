package com.oyj.dataa.source.remote.home

import com.oyj.dataa.dto.home.AlcoholDataDto
import com.oyj.dataa.dto.search.RecommendAlcoholDto
import com.oyj.dataa.dto.search.SearchResultDto
import retrofit2.Response

interface AlcoholDataRemoteDataSource {
    suspend fun getAlcoholData(category: String): Response<AlcoholDataDto>

    suspend fun getSearchedAlcoholData(query: String): Response<SearchResultDto>

    suspend fun getRecommendAlcoholList(query: String): Response<RecommendAlcoholDto>
}
