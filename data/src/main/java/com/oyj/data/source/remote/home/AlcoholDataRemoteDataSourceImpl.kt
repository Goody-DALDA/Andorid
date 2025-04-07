package com.oyj.data.source.remote.home

import com.oyj.data.network.RetrofitService
import com.oyj.data.dto.home.AlcoholDataDto
import com.oyj.data.dto.search.RecommendAlcoholDto
import com.oyj.data.dto.search.SearchResultDto
import retrofit2.Response
import javax.inject.Inject

class AlcoholDataRemoteDataSourceImpl @Inject constructor(
    private val service: RetrofitService
) : AlcoholDataRemoteDataSource {
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
