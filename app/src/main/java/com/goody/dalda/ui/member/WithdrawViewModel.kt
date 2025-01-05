package com.goody.dalda.ui.member

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.ui.state.UiState
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(private val repository: LoginRepository): ViewModel() {

    companion object {
        private const val TAG = "WithdrawViewModel"
    }

    private val _state = mutableStateOf<UiState<String>>(UiState.Uninitialized)
    val state: State<UiState<String>> get() = _state

    fun requestWithdraw() {
        _state.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO){
            try {
                val reseponse = repository.leaveUser()

                if (reseponse.isSuccess()) {
                    unlinkKakao()
                    _state.value = UiState.Success(reseponse.message)
                } else {
                    _state.value = UiState.Error()
                }
            } catch (e: Exception) {
                _state.value = UiState.Error(exception = e)
            }
        }
    }

    private fun unlinkKakao() {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(TAG, "연결 끊기 실패", error)
            }
            else {
                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }
}
