package com.goody.dalda.ui.home

import com.goody.dalda.data.dto.home.AlcoholDataDto
import com.goody.dalda.data.dto.search.RecommendAlcoholDto
import com.goody.dalda.data.dto.search.SearchResultDto
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.network.RetrofitService
import retrofit2.Response

class FakeAlcoholDataRemoteDataSourceImpl(private val service: RetrofitService) : AlcoholDataRemoteDataSource {
    override suspend fun getAlcoholData(category: String): Response<AlcoholDataDto> {
        return service.getAlcoholData(category = category)
    }

    override suspend fun getSearchedAlcoholData(query: String): Response<SearchResultDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommendAlcoholList(query: String): Response<RecommendAlcoholDto> {
        TODO("Not yet implemented")
    }
}
