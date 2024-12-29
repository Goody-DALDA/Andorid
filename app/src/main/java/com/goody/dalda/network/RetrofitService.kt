package com.goody.dalda.network

import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.dto.ProfileDto
import com.goody.dalda.data.dto.search.SearchResultDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {
    @FormUrlEncoded
    @POST("/api/users")
    suspend fun login(
        @FieldMap body: Map<String, String>
    ): Response<LoginDto>

    @GET("/api/users/profile")
    suspend fun fetchProfile(): Response<ProfileDto>

    @Headers("Content-Type: application/json")
    @GET("/api/alcohols")
    suspend fun getAlcoholInfo(
        @Query("category") category: String
    ): Response<AlcoholInfoDto>

    @Headers("Content-Type: application/json")
    @GET("/api/search/list")
    suspend fun getSearchedAlcoholInfo(
        @Query("name") name: String
    ): Response<SearchResultDto>
}
