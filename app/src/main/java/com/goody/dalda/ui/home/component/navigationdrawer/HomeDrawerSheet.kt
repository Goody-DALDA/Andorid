package com.goody.dalda.ui.home.component.navigationdrawer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R

@Composable
fun HomeDrawerSheet(
    modifier: Modifier = Modifier,
    userName: String,
    userEmail: String,
    selectedItemIndex: Int = 0,
    onChangeDrawerState: () -> Unit = {},
    onChangeSelectedItemIndex: (Int) -> Unit = {}
) {
    val items: List<DrawerItem> = listOf(
        DrawerItem(
            title = stringResource(id = R.string.text_notice)
        ),
        DrawerItem(
            title = stringResource(id = R.string.text_event)
        ),
        DrawerItem(
            title = stringResource(id = R.string.text_inquire)
        )
    )

    ModalDrawerSheet(
        modifier = modifier
            .fillMaxHeight(),
    ) {
        // 헤더
        NavigationHeader(
            modifier = Modifier.padding(start = 16.dp, end = 24.dp),
            userName = userName,
            userEmail = userEmail,
            onClickCloseIcon = { onChangeDrawerState() }
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp, start = 16.dp, end = 24.dp)
        )

        // 리스트
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.title)
                },
                selected = index == selectedItemIndex,
                onClick = {
                    onChangeSelectedItemIndex(index)
                    onChangeDrawerState()
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }

        // 바텀
        NavigationBottom(
            modifier = Modifier.padding(top = 100.dp, start = 16.dp, end = 24.dp)
        )
    }
}

@Preview
@Composable
private fun HomeDrawerSheetPreview() {
    HomeDrawerSheet(
        userName = "삼겹살에 소주",
        userEmail = "oyj7677@gmail.com"
    )
}
