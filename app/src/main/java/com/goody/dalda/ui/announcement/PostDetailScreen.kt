package com.goody.dalda.ui.announcement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.ui.announcement.component.PostDetailTopBar
import com.goody.dalda.data.model.PostUIModel
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun PostDetailScreen(
    viewModel: PostDetailViewModel = viewModel(),
    postUIModel: PostUIModel,
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
) {
    val currentPost by viewModel.currentPostUIModel.collectAsStateWithLifecycle()
    val nextPost by viewModel.nextPostUIModel.collectAsStateWithLifecycle()
    val prevPost by viewModel.prevPostUIModel.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchNoticePost(postUIModel)
    }

    PostDetailLayout(
        currentPostUIModel = currentPost ?: postUIModel,
        prevPostUIModel = prevPost ?: postUIModel,
        nextPostUIModel = nextPost ?: postUIModel,
        onClickNextPost = { viewModel.nextPost() },
        onClickPrevPost = { viewModel.prevPost() },
        onClose = onClose,
        modifier = modifier,
    )
}

@Composable
fun PostDetailLayout(
    currentPostUIModel: PostUIModel,
    prevPostUIModel: PostUIModel,
    nextPostUIModel: PostUIModel,
    onClickNextPost: () -> Unit = {},
    onClickPrevPost: () -> Unit = {},
    onClose: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground),
        topBar = {
            PostDetailTopBar(onClose)
        },
        containerColor = Color.White,
    ) { innerPadding ->
        Column(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            PostTitleContainer(
                title = currentPostUIModel.title,
                date = currentPostUIModel.createdAt,
            )

            Text(
                text = currentPostUIModel.content,
                style = DaldaTextStyle.body2,
                color = colorResource(id = R.color.text),
                modifier =
                    Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
            )

            PreviousPostAndNextPost(
                nextPostUIModel = nextPostUIModel,
                prevPostUIModel = prevPostUIModel,
                onClickNext = onClickNextPost,
                onClickPrevious = onClickPrevPost,
            )
        }
    }
}

@Composable
fun PostTitleContainer(
    title: String,
    date: String,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        Text(
            text = title,
            style = DaldaTextStyle.h2,
            color = colorResource(id = R.color.text),
        )
        Text(
            text = date,
            style = DaldaTextStyle.body3,
            color = colorResource(id = R.color.gray_40),
        )
    }
}

@Preview
@Composable
private fun PostDetailScreenNewPrev() {
    val currentPostUIModel =
        PostUIModel(
            1,
            "제목",
            "내용영역입니다. 이하 내용은 더미텍스트입니다. 국가원로자문회의의 조직·직무범위 기타 필요한 사항은 법률로 정한다. 대통령은 내우·외환·천재·지변 또는 중대한 재정·경제상의 위기에 있어서 국가의 안전보장 또는 공공의 안녕질서를 유지하기 위하여 긴급한 조치가 필요하고 국회의 집회를 기다릴 여유가 없을 때에 한하여 최소한으로 필요한 재정·경제상의 처분을 하거나 이에 관하여 법률의 효력을 가지는 명령을 발할 수 있다.\n" +
                "\n" +
                "새로운 회계연도가 개시될 때까지 예산안이 의결되지 못한 때에는 정부는 국회에서 예산안이 의결될 때까지 다음의 목적을 위한 경비는 전년도 예산에 준하여 집행할 수 있다. 국회는 헌법 또는 법률에 특별한 규정이 없는 한 재적의원 과반수의 출석과 출석의원 과반수의 찬성으로 의결한다. 가부동수인 때에는 부결된 것으로 본다.",
            "2025.01.09",
            "2025.01.10",
            true,
        )
    val nextPostUIModel =
        PostUIModel(
            2,
            "다음글 제목",
            "다음글 입니다.",
            "2025.01.09",
            "2025.01.10",
            true,
        )

    val prevPostUIModel =
        PostUIModel(
            3,
            "이전글 제목",
            "이전글 입니다.",
            "2025.01.08",
            "2025.01.10",
            true,
        )

    PostDetailLayout(
        currentPostUIModel = currentPostUIModel,
        prevPostUIModel = prevPostUIModel,
        nextPostUIModel = nextPostUIModel,
    )
}
