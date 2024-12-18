package com.goody.dalda.network

import com.goody.dalda.data.dto.LoginDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @POST("/api/users")
    suspend fun login(
        @Body body: RequestBody
    ): Response<LoginDto>



}