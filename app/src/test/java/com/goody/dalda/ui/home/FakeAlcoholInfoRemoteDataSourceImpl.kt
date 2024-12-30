package com.goody.dalda.ui.home

import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.dto.search.RecommendAlcoholDto
import com.goody.dalda.data.dto.search.SearchResultDto
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSource
import com.goody.dalda.network.RetrofitService
import retrofit2.Response

class FakeAlcoholInfoRemoteDataSourceImpl(private val service: RetrofitService) : AlcoholInfoRemoteDataSource {
    override suspend fun getAlcoholInfo(category: String): Response<AlcoholInfoDto> {
        return service.getAlcoholInfo(category = category)
    }

    override suspend fun getSearchedAlcoholInfo(query: String): Response<SearchResultDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommendAlcoholList(query: String): Response<RecommendAlcoholDto> {
        TODO("Not yet implemented")
    }
}
