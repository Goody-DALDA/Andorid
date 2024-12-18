package com.goody.dalda.ui.category

import androidx.lifecycle.ViewModel
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.repository.home.AlcoholRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val alcoholRepository: AlcoholRepository) :
    ViewModel() {

    // 쿼리
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    // 주류 정보 호출 로직AlcoholInfo
    private val _alcoholInfoList = MutableStateFlow(emptyList<AlcoholInfo>())
    val alcoholInfo: StateFlow<List<AlcoholInfo>> = _alcoholInfoList

    // 카테고리
    private val _category =
        MutableStateFlow(listOf("소주", "맥주", "와인", "위스키", "전통주", "사케", "칵테일", "폭탄주"))
    val category: StateFlow<List<String>> = _category

    suspend fun fetchAlcoholInfo(category: String) {
        _alcoholInfoList.value = alcoholRepository.getAlcoholInfo(category).map {
            AlcoholInfo(
                id = it.id,
                name = it.name,
                imgUrl = it.imgUrl,
                type = AlcoholType.valueOf(category.uppercase()),
                abv = it.abv
            )
        }
    }

    fun setQuery(query: String) {
        _query.value = query
    }
}
