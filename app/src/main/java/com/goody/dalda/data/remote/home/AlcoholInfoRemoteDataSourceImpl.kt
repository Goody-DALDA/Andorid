package com.goody.dalda.data.remote.home

import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.dto.search.SearchResultDto
import com.goody.dalda.network.RetrofitService
import retrofit2.Response
import javax.inject.Inject

class AlcoholInfoRemoteDataSourceImpl @Inject constructor(private val service: RetrofitService) :
    AlcoholInfoRemoteDataSource {
    override suspend fun getAlcoholInfo(category: String): Response<AlcoholInfoDto> {
        return service.getAlcoholInfo(category = category)
    }

    override suspend fun getSearchedAlcoholInfo(query: String): Response<SearchResultDto> {
        return service.getSearchedAlcoholInfo(name = query)
    }
}
