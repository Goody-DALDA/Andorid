package com.oyj.domain.usecase.login

import com.oyj.domain.model.OAuthTokenDomain
import com.oyj.domain.model.ProfileDomain
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthTokenDomain,
    ): ProfileDomain? {
        return repository.login(
            nickname = nickname,
            email = email,
            profileImg = profileImg,
            token = token
        )
    }
}