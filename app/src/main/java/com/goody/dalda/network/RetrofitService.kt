package com.goody.dalda.network

import com.goody.dalda.data.dto.LeaveDto
import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.data.dto.LogoutDto
import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.dto.ProfileDto
import com.goody.dalda.data.dto.home.AlcoholDataDto
import com.goody.dalda.data.dto.search.RecommendAlcoholDto
import com.goody.dalda.data.dto.search.SearchResultDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @POST("/api/users/logout")
    suspend fun logout(): Response<LogoutDto>

    @DELETE("/api/users")
    suspend fun leaveUser(): Response<LeaveDto>

    @Headers("Content-Type: application/json")
    @GET("/api/alcohols")
    suspend fun getAlcoholData(
        @Query("category") category: String
    ): Response<AlcoholDataDto>

    @Headers("Content-Type: application/json")
    @GET("/api/search/list")
    suspend fun getSearchedAlcoholData(
        @Query("name") name: String
    ): Response<SearchResultDto>

    @Headers("Content-Type: application/json")
    @GET("/api/search")
    suspend fun getRecommendAlcohol(
        @Query("recommendations") recommendations: String
    ): Response<RecommendAlcoholDto>
}
