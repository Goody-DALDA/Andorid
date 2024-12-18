package com.goody.dalda.ui.home

import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSource
import com.goody.dalda.network.RetrofitService
import retrofit2.Response

class FakeAlcoholInfoRemoteDataSourceImpl(private val service: RetrofitService) : AlcoholInfoRemoteDataSource {
    override suspend fun getAlcoholInfo(category: String): Response<AlcoholInfoDto> {
        return service.getAlcoholInfo(category = category)
    }
}
