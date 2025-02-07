package com.goody.dalda.ui.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun ResentSearch(
    recentSearchWordList: List<String>,
    onClickSearchWord: (String) -> Unit = {},
    onClickClear: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.text_recent_search_work),
                style = DaldaTextStyle.label1,
            )
            Icon(
                imageVector = Icons.Outlined.Clear,
                contentDescription = null,
                modifier =
                    Modifier
                        .alpha(if (recentSearchWordList.isEmpty()) 0f else 1f)
                        .clickable { onClickClear() },
            )
        }

        AlcoholChipGrid(
            recentSearchWordList = recentSearchWordList,
            onClickWord = onClickSearchWord,
        )
    }
}
