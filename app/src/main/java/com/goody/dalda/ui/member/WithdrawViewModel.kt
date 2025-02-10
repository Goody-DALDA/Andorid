package com.goody.dalda.ui.member

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.ui.state.UiState
import com.goody.dalda.util.PreferenceManager
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel
    @Inject
    constructor(
        private val repository: LoginRepository,
    ) : ViewModel() {
        companion object {
            private const val TAG = "WithdrawViewModel"
        }

        private val _uiState = MutableStateFlow<UiState<String>>(UiState.Uninitialized)
        val uiState: StateFlow<UiState<String>> get() = _uiState

        private val _checkState = MutableStateFlow(false)
        val checkState: StateFlow<Boolean> = _checkState

        fun requestWithdrawNew() {
            _uiState.value = UiState.Loading
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repository.leaveUser()

                    if (response.isSuccess()) {
                        unlinkKakao()
                        PreferenceManager.clearAccessToken()
                        _uiState.value = UiState.Success(response.message)
                    } else {
                        _uiState.value = UiState.Error()
                    }
                } catch (e: Exception) {
                    _uiState.value = UiState.Error(exception = e)
                }
            }
        }

        private fun unlinkKakao() {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Log.e(TAG, "연결 끊기 실패", error)
                } else {
                    Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                }
            }
        }

        fun checkBoxState() {
            _checkState.value = !_checkState.value
        }

        fun checkedChange(isChecked: Boolean) {
            _checkState.value = isChecked
        }
    }
