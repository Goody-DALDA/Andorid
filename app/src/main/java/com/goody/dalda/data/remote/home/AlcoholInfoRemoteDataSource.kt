package com.goody.dalda.data.remote.home

import com.goody.dalda.data.dto.home.AlcoholInfoDto
import retrofit2.Response

interface AlcoholInfoRemoteDataSource {
    suspend fun getAlcoholInfo(category: String): Response<AlcoholInfoDto>
}
