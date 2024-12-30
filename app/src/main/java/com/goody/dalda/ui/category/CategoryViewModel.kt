package com.goody.dalda.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholCategoryStatus
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.repository.home.AlcoholRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val alcoholRepository: AlcoholRepository) :
    ViewModel() {

    // 쿼리
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    // 주류 정보 호출 로직AlcoholInfo
    private val _alcoholInfoList = MutableStateFlow(emptyList<AlcoholInfo>())
    val alcoholInfoList: StateFlow<List<AlcoholInfo>> = _alcoholInfoList

    @OptIn(FlowPreview::class)
    val alcoholInfoListWithQuery = query.combine(alcoholInfoList) { query, alcoholInfoList ->
        if (query.isEmpty()) {
            alcoholInfoList
        } else {
            alcoholInfoList.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }.debounce(500L)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    // 카테고리
    private val _category = MutableStateFlow(
        AlcoholType.entries.filter {
            it.categoryStatus == AlcoholCategoryStatus.RELEASE
        }.map {
            it.alcoholName
        }
    )
    val category: StateFlow<List<String>> = _category

    fun fetchAlcoholInfo(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
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

    }

    fun setQuery(query: String) {
        _query.value = query
    }
}
