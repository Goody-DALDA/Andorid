package com.goody.dalda.network

import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.data.dto.ProfileDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    @FormUrlEncoded
    @POST("/api/users")
    suspend fun login(
        @FieldMap body: Map<String, String>
    ): Response<LoginDto>

    @GET("/api/users/profile")
    suspend fun fetchProfile(): Response<ProfileDto>

}