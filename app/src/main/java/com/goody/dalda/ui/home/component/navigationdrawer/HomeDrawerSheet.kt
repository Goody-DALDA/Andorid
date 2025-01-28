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
import com.goody.dalda.ui.home.AuthState
import com.goody.dalda.ui.home.data.Menu

@Composable
fun HomeDrawerSheet(
    userName: String,
    userEmail: String,
    authState: AuthState,
    selectedItemIndex: Int = 0,
    onChangeDrawerState: () -> Unit = {},
    onChangeSelectedItemIndex: (Int) -> Unit = {},
    onClickMenu: (Menu) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val items: List<DrawerItem> =
        listOf(
            DrawerItem(
                title = stringResource(id = R.string.text_notice),
            ),
            DrawerItem(
                title = stringResource(id = R.string.text_inquire),
            ),
        )

    ModalDrawerSheet(
        modifier =
        modifier
            .fillMaxHeight(),
    ) {
        NavigationHeader(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 24.dp),
            userName = if (authState == AuthState.SignIn) userName else stringResource(R.string.text_do_sign_in),
            userEmail = if (authState == AuthState.SignIn) userEmail else stringResource(R.string.text_sign_in_recommendation),
            onClickCloseIcon = { onChangeDrawerState() },
            onClickProfile = {
                if (authState == AuthState.SignIn) {
                    onClickMenu(Menu.Profile)
                } else {
                    onClickMenu(Menu.Login)
                }
            },
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp, start = 16.dp, end = 24.dp),
        )

        // 리스트
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.title)
                },
                selected = index == selectedItemIndex,
                onClick = {
                    val menu = convertTitleToMenu(item.title)
                    onChangeSelectedItemIndex(index)
                    onChangeDrawerState()
                    onClickMenu(menu)
                },
                modifier =
                Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding),
            )
        }

        // 바텀
        NavigationBottom(
            modifier = Modifier.padding(top = 100.dp, start = 16.dp, end = 24.dp),
            onClick = onClickMenu,
        )
    }
}

private fun convertTitleToMenu(title: String): Menu {
    return when (title) {
        "공지사항" -> Menu.Announcement
        "문의하기" -> Menu.ContactUs
        else -> Menu.Announcement
    }
}

@Preview
@Composable
private fun HomeDrawerSheetPreview() {
    HomeDrawerSheet(
        userName = "삼겹살에 소주",
        userEmail = "oyj7677@gmail.com",
        authState = AuthState.SignOut,
    )
}
