package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.LiquorDetailsSideMenuItem
import com.goody.dalda.R
import com.goody.dalda.RequestInfoModify

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLiquorTopBar(
    isDropDownMenuExpanded: Boolean = false,
    omNavigationClick: () -> Unit = {},
    onClickSideMenu: (Boolean) -> Unit = {},
    onClickMenu: (LiquorDetailsSideMenuItem) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = omNavigationClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.description_back_icon),
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onClickSideMenu(!isDropDownMenuExpanded) },
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(id = R.string.icon_menu),
                )
            }

            DropdownMenu(
                expanded = isDropDownMenuExpanded,
                onDismissRequest = { onClickSideMenu(false) },
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            stringResource(id = R.string.text_drop_down_menu_request_info),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = stringResource(id = R.string.icon_menu),
                        )
                    },
                    onClick = {
                        onClickMenu(RequestInfoModify)
                        onClickSideMenu(false)
                    },
                )
            }
        },
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
            ),
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
private fun DetailLiquorTopBarPrev() {
    DetailLiquorTopBar()
}
