package com.goody.dalda.ui.announcement

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor() : ViewModel() {


    private val detail = PostDetail(
        "국가원로자문회의",
        "2024.11.12",
        "내용영역입니다. 이하 내용은 더미텍스트입니다. 국가원로자문회의의 조직·직무범위 기타 필요한 사항은 법률로 정한다. 대통령은 내우·외환·천재·지변 또는 중대한 재정·경제상의 위기에 있어서 국가의 안전보장 또는 공공의 안녕질서를 유지하기 위하여 긴급한 조치가 필요하고 국회의 집회를 기다릴 여유가 없을 때에 한하여 최소한으로 필요한 재정·경제상의 처분을 하거나 이에 관하여 법률의 효력을 가지는 명령을 발할 수 있다.\n" +
                "\n" +
                "새로운 회계연도가 개시될 때까지 예산안이 의결되지 못한 때에는 정부는 국회에서 예산안이 의결될 때까지 다음의 목적을 위한 경비는 전년도 예산에 준하여 집행할 수 있다. 국회는 헌법 또는 법률에 특별한 규정이 없는 한 재적의원 과반수의 출석과 출석의원 과반수의 찬성으로 의결한다. 가부동수인 때에는 부결된 것으로 본다."
    )

    fun getTitle() = detail.title
    fun getDate() = detail.date
    fun getContent() = detail.content
}

data class PostDetail(
    val title: String,
    val date: String,
    val content: String
)
