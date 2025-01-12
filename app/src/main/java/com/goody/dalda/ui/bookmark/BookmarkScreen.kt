package com.goody.dalda.ui.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.component.SearchBarComponent
import com.goody.dalda.ui.home.component.IconPack
import com.goody.dalda.ui.home.component.iconpack.IcCamera
import com.goody.dalda.ui.search.SearchResult

@Composable
fun BookmarkScreen(
    onClickCard: (AlcoholData) -> Unit = {},
    onClickFooter: (AlcoholType) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = viewModel()
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val searchResult by viewModel.searchResultList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getBookmarkList()
    }

    BookmarkScreen(
        query = query,
        alcoholDataList = searchResult,
        onQueryChange = {
            viewModel.setQuery(it)
        },
        onClickCard = onClickCard,
        onClickFooter = {
            AlcoholType.entries.forEach { alcoholType ->
                if (alcoholType.alcoholName == it) {
                    onClickFooter(alcoholType)
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun BookmarkScreen(
    query: String,
    alcoholDataList: List<AlcoholData>,
    onQueryChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onClickCard: (AlcoholData) -> Unit = {},
    onClickFooter: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.padding(vertical = 8.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            SearchBarComponent(
                query = query,
                placeholder = "",
                leadingIcon = Icons.Outlined.Search,
                trailingIcon = IconPack.IcCamera,
                onClickLeadingIcon = { onSearch(it) },
                onValueChange = onQueryChange,
                onClickTrailingIcon = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            SearchResult(
                modifier = Modifier,
                alcoholDataList = alcoholDataList,
                onClickCard = onClickCard,
                onClickFooter = onClickFooter
            )
        }
    }
}


@Preview
@Composable
private fun BookMarkScreenPrev() {
    val alcoholDataList = listOf(
        AlcoholData.Wisky(
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
        AlcoholData.Beer(
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
            country = "독일"
        ),
        AlcoholData.Sake(
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
        AlcoholData.Soju(
            id = 0,
            name = "소주",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_soju,
            volume = "360ml",
            abv = "17%",
            price = 5000,
            comment = "맛있어요"
        )
    )

    BookmarkScreen(
        query = "query",
        alcoholDataList = alcoholDataList,
    )
}