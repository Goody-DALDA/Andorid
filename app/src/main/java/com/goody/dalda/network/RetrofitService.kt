package com.goody.dalda.network

import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.dto.ProfileDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @POST("/api/users")
    suspend fun login(
        @Body body: RequestBody
    ): Response<LoginDto>

    @GET("/api/users/profile")
    suspend fun fetchProfile(): Response<ProfileDto>

    @Headers("Content-Type: application/json")
    @GET("/api/alcohols")
    suspend fun getAlcoholInfo(
        @Query("category") category: String
    ): Response<AlcoholInfoDto>
}
