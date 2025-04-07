package com.goody.dalda.ui.member

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.Profile
import com.goody.dalda.data.model.toAppModel
import com.goody.dalda.ui.state.UiState
import com.kakao.sdk.user.UserApiClient
import com.oyj.domain.usecase.login.FetchProfileUseCase
import com.oyj.domain.usecase.login.LogoutUseCase
import com.oyj.domain.usecase.login.pref.ClearAccessTokenUseCase
import com.oyj.domain.usecase.login.pref.ClearProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val fetchProfileUseCase: FetchProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val clearAccessTokenUseCase: ClearAccessTokenUseCase,
    private val clearProfileUseCase: ClearProfileUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "MemberViewModel"
    }

    private val _profile = MutableStateFlow(Profile())
    val profile: StateFlow<Profile> = _profile

    private val _logoutState = MutableStateFlow<UiState<String>>(UiState.Uninitialized)
    val logoutState: StateFlow<UiState<String>> = _logoutState

    fun fetchProfileNew() {
        viewModelScope.launch(Dispatchers.IO) {
            _profile.value = fetchProfileUseCase().toAppModel()
        }
    }

    fun requestLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = logoutUseCase()
                if (response.isSuccess()) {
                    logoutKakao()
                    clearAccessTokenUseCase()
                    clearProfileUseCase()
                    _logoutState.value = UiState.Success(response.message)
                } else {
                    _logoutState.value = UiState.Error(exception = Exception(response.message))
                }
            } catch (e: Exception) {
                _logoutState.value = UiState.Error(exception = e)
            }
        }
    }

    private fun logoutKakao() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }
}
