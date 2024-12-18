package com.goody.dalda.network

import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.data.dto.ProfileDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @POST("/api/users")
    suspend fun login(
        @Body body: RequestBody
    ): Response<LoginDto>

    @GET("/api/users/profile")
    suspend fun fetchProfile(): Response<ProfileDto>

}