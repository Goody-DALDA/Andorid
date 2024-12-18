package com.goody.dalda.data.remote

import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.network.RetrofitService
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val service: RetrofitService): UserRemoteDataSource {
    override suspend fun login(
        nickname: String,
        email: String,
        profileImg: String
    ): Response<LoginDto> {
        val json = JsonObject()
        json.addProperty("nickname", nickname)
        json.addProperty("email", email)
        json.addProperty("profileImg", profileImg)

        return service.login(json.toString().toRequestBody("application/json".toMediaType()))
    }
}