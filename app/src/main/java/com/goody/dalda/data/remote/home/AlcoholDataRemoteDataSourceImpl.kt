package com.goody.dalda.data.remote.home

import com.goody.dalda.data.dto.home.AlcoholDataDto
import com.goody.dalda.data.dto.search.RecommendAlcoholDto
import com.goody.dalda.data.dto.search.SearchResultDto
import com.goody.dalda.network.RetrofitService
import retrofit2.Response
import javax.inject.Inject

class AlcoholDataRemoteDataSourceImpl @Inject constructor(private val service: RetrofitService) :
    AlcoholDataRemoteDataSource {
    override suspend fun getAlcoholData(category: String): Response<AlcoholDataDto> {
        return service.getAlcoholData(category = category)
    }

    override suspend fun getSearchedAlcoholData(query: String): Response<SearchResultDto> {
        return service.getSearchedAlcoholData(name = query)
    }

    override suspend fun getRecommendAlcoholList(query: String): Response<RecommendAlcoholDto> {
        return service.getRecommendAlcohol(recommendations = query)
    }
}
