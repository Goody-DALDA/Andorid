package com.goody.dalda.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
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
class BookmarkViewModel @Inject constructor(
    private val alcoholRepository: AlcoholRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _searchResultList = MutableStateFlow(emptyList<AlcoholData>())

    @OptIn(FlowPreview::class)
    val searchResultList: StateFlow<List<AlcoholData>> =
        _searchResultList.combine(query) { list, query ->
            list.filter { it.name.contains(query) }
        }.debounce(500L)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = emptyList()
            )

    fun setQuery(query: String) {
        _query.value = query
    }

    fun getBookmarkList() {
        viewModelScope.launch(Dispatchers.IO) {
            _searchResultList.value = alcoholRepository.getBookmarkAlcoholList()
        }
    }
}