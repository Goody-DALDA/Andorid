package com.goody.dalda.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goody.dalda.R
import com.goody.dalda.ui.home.component.iconpack.IcCamera

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlcoholSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    searchResultList: List<String> = emptyList(),
    recentSearchWordList: List<String> = emptyList(),
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
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
                        recentSearchWordList = recentSearchWordList,
                        onClickWord = onQueryChange
                    )
                }
            } else {
                Column(
                    Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    for (searchResult in searchResultList) {
                        ListItem(
                            headlineContent = { Text(searchResult) },
                            modifier = Modifier
                                .clickable {
                                    onQueryChange(searchResult)
                                    onExpandedChange(false)
                                }
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            supportingContent = { Text("Additional info") },
                            leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlcoholChipGrid(
    modifier: Modifier = Modifier,
    recentSearchWordList: List<String>,
    onClickWord: (String) -> Unit = {}
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        recentSearchWordList.forEach { item ->
            AlcoholChip(
                text = item,
                onClickWord = onClickWord
            )
        }
    }
}

@Composable
fun AlcoholChip(
    text: String,
    onClickWord: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(
                Color(0xFFF5F5F5),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClickWord(text) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    var text by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val recentSearchWordList = listOf("소주", "맥주", "막걸리")
    val searchResultList = listOf("테스트1", "테스트2", "테스트3")

    AlcoholSearchBar(
        modifier = Modifier,
        query = text,
        searchResultList = searchResultList,
        recentSearchWordList = recentSearchWordList,
        expanded = expanded,
        onQueryChange = { text = it },
        onExpandedChange = { expanded = it },
        onSearch = {},
    )
}
