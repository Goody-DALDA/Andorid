package com.goody.dalda.ui.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.PostUIModel
import com.goody.dalda.data.model.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.oyj.domain.usecase.FetchNoticeUseCase

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val fetchNoticeUseCase: FetchNoticeUseCase
) : ViewModel() {
    private val _currentPostUIModel = MutableStateFlow<PostUIModel?>(null)
    val currentPostUIModel: StateFlow<PostUIModel?> = _currentPostUIModel

    private val _nextPostUIModel = MutableStateFlow<PostUIModel?>(null)
    val nextPostUIModel: StateFlow<PostUIModel?> = _nextPostUIModel

    private val _prevPostUIModel = MutableStateFlow<PostUIModel?>(null)
    val prevPostUIModel: StateFlow<PostUIModel?> = _prevPostUIModel

    private var postUIModelList: List<PostUIModel> = emptyList()

    private var currentIndex = 0

    fun fetchNoticePost(postUIModel: PostUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            postUIModelList = fetchNoticeUseCase().toUIModel()
            setPost(postUIModel)
            setNextPost()
            setPrevPost()
        }
    }

    private fun setPost(postUIModel: PostUIModel) {
        _currentPostUIModel.value = postUIModel
        currentIndex = postUIModelList.indexOf(postUIModel)
    }

    fun nextPost() {
        if (currentIndex >= postUIModelList.size - 1) return
        val post = postUIModelList[currentIndex + 1]
        setPost(post)
        setNextPost()
        setPrevPost()
    }

    fun prevPost() {
        if (currentIndex <= 0) return

        val post = postUIModelList[currentIndex - 1]
        setPost(post)
        setNextPost()
        setPrevPost()
    }

    private fun setNextPost() {
        _nextPostUIModel.value =
            if (currentIndex >= postUIModelList.size - 1) null else postUIModelList[currentIndex + 1]
    }

    private fun setPrevPost() {
        _prevPostUIModel.value = if (currentIndex <= 0) null else postUIModelList[currentIndex - 1]
    }
}
