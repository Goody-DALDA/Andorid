package com.goody.dalda.ui.category.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.data.AlcoholType

@Composable
fun CategoryTab(
    currentPage: Int,
    category: List<String> = emptyList(),
    onClickTab: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    ScrollableTabRow(
        containerColor = Color.White,
        contentColor = Color.Black,
        selectedTabIndex = currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[currentPage]),
                color = Color.Black,
            )
        },
        edgePadding = 0.dp,
        modifier = modifier.fillMaxWidth(),
    ) {
        category.forEachIndexed { index, title ->
            Tab(
                text = { Text(text = title) },
                selected = currentPage == index,
                onClick = { onClickTab(index) },
                unselectedContentColor = Color.Gray,
            )
        }
    }
}

@Preview
@Composable
private fun CategoryTabPrev() {
    val category = AlcoholType.entries.map { it.alcoholName }
    val pagerState = rememberPagerState(pageCount = { category.size })

    CategoryTab(
        currentPage = pagerState.currentPage,
        category = category,
    )
}
