package com.oyj.data.source.remote.home

import com.oyj.data.dto.home.AlcoholDataDto
import com.oyj.data.dto.search.RecommendAlcoholDto
import com.oyj.data.dto.search.SearchResultDto
import retrofit2.Response

interface AlcoholDataRemoteDataSource {
    suspend fun getAlcoholData(category: String): Response<AlcoholDataDto>

    suspend fun getSearchedAlcoholData(query: String): Response<SearchResultDto>

    suspend fun getRecommendAlcoholList(query: String): Response<RecommendAlcoholDto>
}
