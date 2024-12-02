package com.goody.dalda.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.RecommendAlcohol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _alcoholInfoList = MutableStateFlow(emptyList<AlcoholInfo>())
    val alcoholInfoList: StateFlow<List<AlcoholInfo>> = _alcoholInfoList

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


    fun setAlcoholInfoList(alcoholInfoList: List<AlcoholInfo>) {
        _alcoholInfoList.value = alcoholInfoList
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
}
