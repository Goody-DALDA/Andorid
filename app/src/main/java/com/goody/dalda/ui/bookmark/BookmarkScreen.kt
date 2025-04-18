package com.goody.dalda.ui.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.ui.AppPaddingSize
import com.goody.dalda.ui.bookmark.component.BookmarkAlcoholCard
import com.goody.dalda.ui.bookmark.component.BookmarkTopBar
import com.goody.dalda.ui.error.ErrorPageScreen

@Composable
fun BookmarkScreen(
    onClickNavIcon: () -> Unit = {},
    onClickCard: (AlcoholUIModel) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = viewModel(),
) {
    val bookmarkList by viewModel.bookmarkList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getBookmarkList()
    }

    BookmarkScreen(
        alcoholUIModelList = bookmarkList,
        onClickNavIcon = onClickNavIcon,
        onClickCard = onClickCard,
        onClickBookmark = {
            viewModel.deleteBookMark(it)
        },
        modifier = modifier.padding(horizontal = AppPaddingSize.HORIZONTAL.dp),
    )
}

@Composable
fun BookmarkScreen(
    alcoholUIModelList: List<AlcoholUIModel>,
    onClickNavIcon: () -> Unit = {},
    onClickCard: (AlcoholUIModel) -> Unit = {},
    onClickBookmark: (AlcoholUIModel) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            BookmarkTopBar(
                onClickNavIcon = onClickNavIcon,
            )
        },
        containerColor = Color.White,
        modifier = modifier,
    ) { innerPadding ->
        if (alcoholUIModelList.isEmpty()) {
            ErrorPageScreen(
                modifier = Modifier.fillMaxSize(),
                errorMessage = stringResource(id = R.string.text_empty_bookmark),
                buttonTitle = stringResource(id = R.string.text_comeback_main),
                onClickButton = onClickNavIcon
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                alcoholUIModelList.forEach {
                    item {
                        BookmarkAlcoholCard(
                            alcoholUIModel = it,
                            onClickCard = onClickCard,
                            onClickBookmark = onClickBookmark,
                            modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun BookMarkScreenPrev() {
    val alcoholUIModelLists =
        listOf(
            AlcoholUIModel.Whisky(
                id = 0,
                name = "위스키",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_whiskey,
                volume = "750ml",
                abv = "40%",
                type = "위스키",
                country = "스코틀랜드",
                price = 170000,
                taste = "써요",
                aroma = "부드러워요",
                finish = "깔끔해요",
            ),
            AlcoholUIModel.Beer(
                id = 0,
                name = "카스",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_beer,
                volume = "355ml",
                abv = "4.5%",
                appearance = 2.28f,
                flavor = 4.4f,
                mouthfeel = 2.0f,
                aroma = 3.3f,
                type = "밀맥주",
                country = "독일",
            ),
            AlcoholUIModel.Sake(
                id = 0,
                name = "사케",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_sake,
                volume = "750ml",
                abv = "15%",
                price = 30000,
                taste = "달아요",
                aroma = "좋아요",
                finish = "시원해요",
                country = "일본",
            ),
            AlcoholUIModel.Soju(
                id = 0,
                name = "소주",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_soju,
                volume = "360ml",
                abv = "17%",
                price = 5000,
                comment = "맛있어요",
            ),
        )

    BookmarkScreen(
        alcoholUIModelList = alcoholUIModelLists,
        modifier = Modifier.padding(horizontal = 16.dp),
    )
}
