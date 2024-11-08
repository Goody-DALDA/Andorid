package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiquorDetailTopBar(
    modifier: Modifier = Modifier,
    omNavigationClick: () -> Unit = {},
    onClickMenu: () -> Unit = {}
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = omNavigationClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.icon_menu)
                )
            }
        },
        actions = {
            IconButton(onClick = onClickMenu) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(id = R.string.icon_menu)
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun LiquorDetailTopBarPrev() {
    LiquorDetailTopBar()
}
