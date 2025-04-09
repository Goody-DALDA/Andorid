package com.goody.dalda.ui.home

import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.data.model.RecommendAlcoholUIModel
import com.goody.dalda.data.model.toUIModelList
import com.goody.dalda.ui.home.data.UserProfile
import com.goody.dalda.data.model.toUIModel
import com.oyj.domain.usecase.bookmark.GetBookmarkAlcoholListUseCase
import com.oyj.domain.usecase.login.pref.GetOAuthTokenUseCase
import com.oyj.domain.usecase.login.FetchProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBookmarkAlcoholListUseCase: GetBookmarkAlcoholListUseCase,
    private val fetchProfileUseCase: FetchProfileUseCase,
    private val getOAuthTokenUseCase: GetOAuthTokenUseCase
) : ViewModel() {
    private val _bookmarkAlcoholUIModelList = MutableStateFlow(emptyList<AlcoholUIModel>())
    val bookmarkList: StateFlow<List<AlcoholUIModel>> = _bookmarkAlcoholUIModelList

    private val _recommendAlcoholUIModelList = MutableStateFlow(emptyList<RecommendAlcoholUIModel>())
    val recommendAlcoholUIModelList: StateFlow<List<RecommendAlcoholUIModel>> = _recommendAlcoholUIModelList

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.CommonState)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    private val _authState = MutableStateFlow(determineInitialAuthState())
    val authState: StateFlow<AuthState> = _authState

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile

    private val _selectedItemIndex = MutableStateFlow(0)
    val selectedItemIndex: StateFlow<Int> = _selectedItemIndex

    private val _drawerState = MutableStateFlow(DrawerState(DrawerValue.Closed))
    val drawerState: StateFlow<DrawerState> = _drawerState

    init {
        fetchProfile()
    }

    /**
     * 다이얼로그의 표시 여부를 관리하는 상태
     */
    private val _isDialogVisible = MutableStateFlow(false)
    val isDialogVisible: StateFlow<Boolean> = _isDialogVisible

    fun setSelectedItemIndex(itemIndex: Int) {
        _selectedItemIndex.value = itemIndex
    }

    fun fetchProfile() {
        viewModelScope.launch {
            runCatching {
                fetchProfileUseCase().toUIModel()
            }.onSuccess { profile ->
                Log.d(TAG, "fetchProfile: $profile")
                _userProfile.value = UserProfile(
                    profile.nickname,
                    profile.email,
                    profile.profileImg
                )
            }.onFailure { e ->
                when (e) {
                    is IOException -> handleError(e, "네트워크 오류가 발생했습니다.")
                    is HttpException -> handleError(e, "서버 오류가 발생했습니다.")
                    else -> handleError(e, "알 수 없는 오류가 발생했습니다.")
                }
            }
        }
    }

    fun fetchBookmarkAlcoholList() {
        viewModelScope.launch {
            runCatching {
                getBookmarkAlcoholListUseCase().collect { alcoholList ->
                    _bookmarkAlcoholUIModelList.value = alcoholList.toUIModelList().asReversed()
                }
            }.onFailure { e ->
                handleError(e, e.message?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    /**
     * 다이얼로그의 표시 상태를 설정합니다.
     * @param isVisible true일 경우 다이얼로그를 표시하고, false일 경우 숨깁니다.
     */
    fun setDialogVisible(isVisible: Boolean) {
        _isDialogVisible.value = isVisible
    }

    private fun handleError(e: Throwable, errorMessage: String) {
        Log.e(TAG, "$errorMessage: $e")
        _homeUiState.value = HomeUiState.ErrorState(errorMessage)
    }

    private fun determineInitialAuthState(): AuthState {
        return if (getOAuthTokenUseCase().isEmpty()) {
            AuthState.SignOut
        } else {
            AuthState.SignIn
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
