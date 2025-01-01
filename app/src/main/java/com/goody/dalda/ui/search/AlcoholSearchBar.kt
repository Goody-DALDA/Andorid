package com.goody.dalda.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.ui.home.component.IconPack
import com.goody.dalda.ui.home.component.iconpack.IcCamera
import com.goody.dalda.ui.search.component.AlcoholChipGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlcoholSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    searchResultList: List<AlcoholData> = emptyList(),
    recentSearchWordList: List<String> = emptyList(),
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit = {},
    onClickFooter: (String) -> Unit = {}
) {
    Box(modifier = modifier
        .fillMaxSize()
        .semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = {
                        onExpandedChange(false)
                        onSearch(it)
                    },
                    expanded = expanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.text_search_alcohol)
                        )
                    },
                    leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                    trailingIcon = { Icon(IconPack.IcCamera, contentDescription = null) },
                )
            },
            expanded = expanded,
            onExpandedChange = onExpandedChange,
        ) {
            if (query.isEmpty()) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.text_recent_search_work)
                        )
                        Icon(Icons.Outlined.Clear, contentDescription = null)
                    }

                    AlcoholChipGrid(
                        recentSearchWordList = recentSearchWordList, onClickWord = onQueryChange
                    )
                }
            } else {
                SearchResult(
                    modifier = Modifier,
                    alcoholDataList = searchResultList,
                    onClickFooter = onClickFooter
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun AlcoholSearchBarPreview() {
//    val searchResultList = listOf(
//        AlcoholData.Wisky(
//            id = 0,
//            name = "위스키",
//            imgUrl = "http://www.bing.com/search?q=sagittis",
//            tag = R.drawable.tag_whiskey,
//            volume = "750ml",
//            abv = "40%",
//            type = "위스키",
//            country = "스코틀랜드",
//            price = 170000,
//            taste = "써요",
//            aroma = "부드러워요",
//            finish = "깔끔해요",
//        ),
//        AlcoholData.Beer(
//            id = 0,
//            name = "카스",
//            imgUrl = "http://www.bing.com/search?q=sagittis",
//            tag = R.drawable.tag_beer,
//            volume = "355ml",
//            abv = "4.5%",
//            appearance = 2.28f,
//            flavor = 4.4f,
//            mouthfeel = 2.0f,
//            aroma = 3.3f,
//            type = "밀맥주",
//            country = "독일"
//        ),
//        AlcoholData.Sake(
//            id = 0,
//            name = "사케",
//            imgUrl = "http://www.bing.com/search?q=sagittis",
//            tag = R.drawable.tag_sake,
//            volume = "750ml",
//            abv = "15%",
//            price = 30000,
//            taste = "달아요",
//            aroma = "좋아요", country = "Mali", finish = "doming",
//        )
//    )
//
//    AlcoholSearchBar(
//        modifier = Modifier,
//        query = "",
//        searchResultList = searchResultList,
//        recentSearchWordList = listOf("소주", "맥주", "와인", "위스키", "소맥"),
//        expanded = false,
//        onQueryChange = {},
//        onExpandedChange = {},
//        onSearch = {}
//    )
//}
