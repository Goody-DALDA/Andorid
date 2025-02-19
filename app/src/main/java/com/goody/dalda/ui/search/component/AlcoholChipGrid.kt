package com.goody.dalda.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goody.dalda.ui.theme.DaldaTextStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlcoholChipGrid(
    recentSearchWordList: List<String>,
    onClickWord: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        recentSearchWordList.forEach { item ->
            AlcoholChip(
                text = item,
                onClickWord = onClickWord,
            )
        }
    }
}

@Composable
fun AlcoholChip(
    text: String,
    onClickWord: (String) -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
        Modifier
            .background(
                Color(0xFFF5F5F5),
                shape = RoundedCornerShape(16.dp),
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClickWord(text) },
    ) {
        Text(
            text = text,
            style = DaldaTextStyle.label2,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
    }
}
