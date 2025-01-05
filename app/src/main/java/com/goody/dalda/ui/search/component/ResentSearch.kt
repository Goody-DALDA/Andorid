package com.goody.dalda.ui.search.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.goody.dalda.R

@Composable
fun ResentSearch(
    modifier: Modifier = Modifier,
    recentSearchWordList: List<String>,
    onQueryChange: (String) -> Unit
) {
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
}
