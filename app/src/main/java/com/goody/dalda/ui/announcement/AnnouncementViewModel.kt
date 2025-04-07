package com.goody.dalda.ui.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.PostUIModel
import com.goody.dalda.data.model.toAppModel
import com.oyj.domain.usecase.FetchNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val fetchNoticeUseCase: FetchNoticeUseCase
) : ViewModel() {
    private val _posts = MutableStateFlow<List<PostUIModel>>(emptyList())
    val posts: StateFlow<List<PostUIModel>> = _posts

    init {
        fetchNoticePost()
    }

    private fun fetchNoticePost() {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.value = fetchNoticeUseCase().toAppModel()
        }
    }
}
