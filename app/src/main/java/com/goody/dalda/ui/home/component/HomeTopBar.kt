package com.goody.dalda.ui.home.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onClickMenu: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { stringResource(id = R.string.text_home_title_top_bar) },
        actions = {
            IconButton(onClick = onClickMenu) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.icon_menu),
                )
            }
        },
        colors =
        TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    HomeTopBar()
}
