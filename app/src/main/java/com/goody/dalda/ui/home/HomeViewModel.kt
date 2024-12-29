package com.goody.dalda.ui.home

import androidx.lifecycle.ViewModel
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.RecommendAlcohol
import com.goody.dalda.data.repository.home.AlcoholRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val alcoholRepository: AlcoholRepository) :
    ViewModel() {

    private val _searchAlcoholInfoList = MutableStateFlow(emptyList<AlcoholInfo>())
    val searchAlcoholInfoList: StateFlow<List<AlcoholInfo>> = _searchAlcoholInfoList

    private val _favoriteAlcoholInfoList = MutableStateFlow(emptyList<AlcoholInfo>())
    val favoriteAlcoholInfoList: StateFlow<List<AlcoholInfo>> = _favoriteAlcoholInfoList

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
        _favoriteAlcoholInfoList.value = alcoholInfoList
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

    suspend fun searchAlcoholInfo(query: String) {
        val searchResult = alcoholRepository.getSearchedAlcoholInfo(query)
        val alcoholInfoList = mutableListOf<AlcoholInfo>()

        alcoholInfoList.addAll(searchResult.sojuList.map { it.toAlcoholInfo(AlcoholType.SOJU) })
        alcoholInfoList.addAll(searchResult.beerList.map { it.toAlcoholInfo(AlcoholType.BEER) })
        alcoholInfoList.addAll(searchResult.sakeList.map { it.toAlcoholInfo(AlcoholType.SAKE) })
        alcoholInfoList.addAll(searchResult.wineList.map { it.toAlcoholInfo(AlcoholType.WINE) })
        alcoholInfoList.addAll(searchResult.wiskyList.map { it.toAlcoholInfo(AlcoholType.WISKY) })
        alcoholInfoList.addAll(searchResult.traditionalLiquorList.map { it.toAlcoholInfo(AlcoholType.TRADITIONALLIQUOR) })

        _searchAlcoholInfoList.value = alcoholInfoList
    }

    private fun <T> T.toAlcoholInfo(type: AlcoholType): AlcoholInfo {
        return when (this) {
            is AlcoholData.Soju -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Beer -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Sake -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Wine -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Wisky -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.TraditionalLiquor -> AlcoholInfo(id, imgUrl, name, type, abv)
            else -> throw IllegalArgumentException("Unknown alcohol type")
        }
    }
}
