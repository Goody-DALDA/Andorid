package com.goody.dalda.ui.home

import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.RecommendAlcohol
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.data.repository.home.AlcoholRepository
import com.goody.dalda.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val alcoholRepository: AlcoholRepository,
    private val profileRepository: LoginRepository
) : ViewModel() {

    private val _bookmarkAlcoholDataList = MutableStateFlow(emptyList<AlcoholData>())
    val bookmarkAlcoholDataList: StateFlow<List<AlcoholData>> = _bookmarkAlcoholDataList

    private val _recommendAlcoholList = MutableStateFlow(emptyList<RecommendAlcohol>())
    val recommendAlcoholList: StateFlow<List<RecommendAlcohol>> = _recommendAlcoholList

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.CommonState)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    private val _authState = MutableStateFlow<AuthState>(AuthState.SignOut)
    val authState: StateFlow<AuthState> = _authState

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _userEmail = MutableStateFlow("")
    val userEmail: StateFlow<String> = _userEmail

    private val _selectedItemIndex = MutableStateFlow(0)
    val selectedItemIndex: StateFlow<Int> = _selectedItemIndex

    private val _drawerState = MutableStateFlow(DrawerState(DrawerValue.Closed))
    val drawerState: StateFlow<DrawerState> = _drawerState

    fun setAlcoholDataList(alcoholDataList: List<AlcoholData>) {
        _bookmarkAlcoholDataList.value = alcoholDataList
    }

    fun setRecommendAlcoholList(recommendAlcoholList: List<RecommendAlcohol>) {
        _recommendAlcoholList.value = recommendAlcoholList
    }

    fun setUserName(userName: String) {
        _userName.value = userName
    }

    fun setUserEmail(userEmail: String) {
        _userEmail.value = userEmail
    }

    fun setHomeUiState(homeUiState: HomeUiState) {
        _homeUiState.value = homeUiState
    }

    fun setAuthState(authState: AuthState) {
        _authState.value = authState
    }

    fun setSelectedItemIndex(itemIndex: Int) {
        _selectedItemIndex.value = itemIndex
    }

    fun fetchProfile() {
        if (PreferenceManager.getAccessToken().isEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val profile = profileRepository.getProfile()
                _userName.value = profile.nickname
                _userEmail.value = profile.email
            } catch (e: Exception) {
                _homeUiState.value = HomeUiState.ErrorState
            }
        }
    }

    fun fetchBookmarkAlcoholList() {
//        if (PreferenceManager.getAccessToken().isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _bookmarkAlcoholDataList.value =
                    alcoholRepository.getBookmarkAlcoholList().reversed()
            } catch (e: Exception) {
                Log.e("TAG", "fetchBookmarkAlcoholList: ${e.message}")
                _homeUiState.value = HomeUiState.ErrorState
            }
        }
    }
}
