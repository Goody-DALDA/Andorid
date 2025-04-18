package com.goody.dalda.ui.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun RecommendAlcoholList(
    modifier: Modifier = Modifier,
    recommendAlcoholList: List<String>,
    onClickWord: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
    ) {
        recommendAlcoholList.forEach {
            item {
                Text(
                    text = it,
                    style = DaldaTextStyle.subtitle1,
                    modifier =
                    modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { onClickWord(it) },
                )
            }
        }
    }
}
