package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
    isDropDownMenuExpanded: Boolean = false,
    omNavigationClick: () -> Unit = {},
    onClickMenu: (Boolean) -> Unit = {}
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
            IconButton(
                onClick = { onClickMenu(!isDropDownMenuExpanded) }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(id = R.string.icon_menu)
                )
            }

            DropdownMenu(
                expanded = isDropDownMenuExpanded,
                onDismissRequest = { onClickMenu(false) }
            ) {

                DropdownMenuItem(
                    text = {
                        Text(
                            stringResource(id = R.string.text_drop_down_menu_request_info)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = stringResource(id = R.string.icon_menu)
                        )
                    },
                    onClick = {
                        // TODO: Handle "정보 수정 요청하기" action
                        onClickMenu(false)
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text(
                            stringResource(id = R.string.text_drop_down_menu_share)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = stringResource(id = R.string.icon_menu)
                        )
                    },
                    onClick = {
                        // TODO: Handle "공유하기" action
                        onClickMenu(false)
                    }
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
