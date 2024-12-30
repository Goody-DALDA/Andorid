package com.goody.dalda.data.remote.home

import com.goody.dalda.data.dto.home.AlcoholDataDto
import com.goody.dalda.data.dto.search.RecommendAlcoholDto
import com.goody.dalda.data.dto.search.SearchResultDto
import retrofit2.Response

interface AlcoholDataRemoteDataSource {
    suspend fun getAlcoholData(category: String): Response<AlcoholDataDto>

    suspend fun getSearchedAlcoholData(query: String): Response<SearchResultDto>

    suspend fun getRecommendAlcoholList(query: String): Response<RecommendAlcoholDto>
}
