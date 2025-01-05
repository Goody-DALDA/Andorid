package com.goody.dalda.ui.category

import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholCategoryStatus
import com.goody.dalda.data.AlcoholData
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

    private val _isFirst = MutableStateFlow(true)
    val isFirst: StateFlow<Boolean> = _isFirst

    // 쿼리
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    // 주류 정보 호출 로직AlcoholData
    private val _alcoholDataList = MutableStateFlow(emptyList<AlcoholData>())
    val alcoholDataList: StateFlow<List<AlcoholData>> = _alcoholDataList

    private val _pageState = MutableStateFlow(PagerState { _category.value.size })
    val pageState: StateFlow<PagerState> = _pageState

    @OptIn(FlowPreview::class)
    val alcoholDataListWithQuery = query.combine(alcoholDataList) { query, alcoholDataList ->
        if (query.isEmpty()) {
            alcoholDataList
        } else {
            alcoholDataList.filter {
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

    fun fetchAlcoholData(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _alcoholDataList.value = alcoholRepository.getAlcoholData(category)
        }
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    fun setIsFirst(isFirst: Boolean) {
        _isFirst.value = isFirst
    }
}
