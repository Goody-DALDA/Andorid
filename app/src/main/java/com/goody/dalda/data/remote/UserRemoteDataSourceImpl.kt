package com.goody.dalda.data.remote

import android.util.Log
import com.goody.dalda.data.dto.LeaveDto
import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.data.dto.LogoutDto
import com.goody.dalda.data.dto.ProfileDto
import com.goody.dalda.network.RetrofitService
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val service: RetrofitService
) : UserRemoteDataSource {
    override suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
    ): Response<LoginDto> {
        val map = HashMap<String, String>()
        map["nickname"] = nickname
        map["email"] = email
        map["profileImg"] = profileImg

        return service.login(map)
    }

    override suspend fun fetchProfile(): Response<ProfileDto> {
        return service.fetchProfile()
    }

    override suspend fun logout(): Response<LogoutDto> {
        return service.logout()
    }

    override suspend fun leaveUser(): Response<LeaveDto> {
        return service.leaveUser()
    }
}
