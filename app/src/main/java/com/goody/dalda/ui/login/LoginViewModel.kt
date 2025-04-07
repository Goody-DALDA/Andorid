package com.goody.dalda.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.Profile
import com.goody.dalda.data.model.toAppModel
import com.goody.dalda.ui.state.UiState
import com.kakao.sdk.auth.model.OAuthToken
import com.oyj.domain.usecase.login.LoginUseCase
import com.oyj.domain.usecase.login.pref.GetProfileUseCase
import com.oyj.domain.usecase.login.pref.IsShowOnboardingUseCase
import com.oyj.domain.usecase.login.pref.SetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val isShowOnboardingUseCase: IsShowOnboardingUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val setProfileUseCase: SetProfileUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<UiState<Profile>>()
    val state: LiveData<UiState<Profile>> get() = _state

    fun checkLogin() {
        val profile = getProfileUseCase().toAppModel()
        if (profile.email.isNotEmpty()) {
            _state.postValue(UiState.Success(profile))
        }
    }

    fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthToken,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val domainToken = com.oyj.domain.model.OAuthTokenEntity(
                accessToken = token.accessToken,
                accessTokenExpiresAt = token.accessTokenExpiresAt,
                refreshToken = token.refreshToken,
                refreshTokenExpiresAt = token.refreshTokenExpiresAt,
                idToken = token.idToken,
                scopes = token.scopes
            )
            loginUseCase(nickname, email, profileImg, domainToken)?.let { profileDomain ->
                setProfileUseCase(profileDomain)
                _state.postValue(UiState.Success(profileDomain.toAppModel()))
            }
        }
    }

    fun isShowOnboarding(): Boolean {
        return isShowOnboardingUseCase()
    }
}
