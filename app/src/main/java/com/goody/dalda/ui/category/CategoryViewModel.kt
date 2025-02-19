package com.goody.dalda.ui.category

import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholCategoryStatus
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.repository.alcohol.AlcoholRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
    @Inject
    constructor(
        private val alcoholRepository: AlcoholRepository,
    ) : ViewModel() {
        private val _isFirst = MutableStateFlow(true)
        val isFirst: StateFlow<Boolean> = _isFirst

        // 쿼리
        private val _query = MutableStateFlow("")
        val query: StateFlow<String> = _query

        // 주류 정보 호출 로직AlcoholData
        private val _alcoholDataListMap: MutableStateFlow<MutableMap<String, List<AlcoholData>>> =
            MutableStateFlow(
                AlcoholType.entries
                    .associate {
                        it.alcoholName to emptyList<AlcoholData>()
                    }.toMutableMap(),
            )
        val alcoholDataListMap: StateFlow<Map<String, List<AlcoholData>>> = _alcoholDataListMap

        private val _pagerState = MutableStateFlow(PagerState { _category.value.size })
        val pagerState: StateFlow<PagerState> = _pagerState

        // 카테고리
        private val _category =
            MutableStateFlow(
                AlcoholType.entries
                    .filter {
                        it.categoryStatus == AlcoholCategoryStatus.RELEASE
                    }.map {
                        it.alcoholName
                    },
            )
        val category: StateFlow<List<String>> = _category

        fun fetchAlcoholData(query: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val key = AlcoholType.entries.first { it.toString() == query }.alcoholName
                val value = alcoholRepository.getAlcoholData(query)

                _alcoholDataListMap.update { currentMap ->
                    currentMap.toMutableMap().apply {
                        this[key] = value
                    }
                }
            }
        }

        fun fetchAlcoholData(categoryIndex: Int) {
            val key = category.value[categoryIndex]

            if (_alcoholDataListMap.value[key]?.isEmpty() == true) {
                val query =
                    AlcoholType.entries
                        .first { it.alcoholName == category.value[categoryIndex] }
                        .toString()
                fetchAlcoholData(query)
            }
        }

        fun setQuery(query: String) {
            _query.value = query
        }

        fun setIsFirst(isFirst: Boolean) {
            _isFirst.value = isFirst
        }
    }
