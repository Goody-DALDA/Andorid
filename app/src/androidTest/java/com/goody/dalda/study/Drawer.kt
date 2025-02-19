package com.goody.dalda.study

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.ui.home.component.HomeTopBar
import kotlinx.coroutines.launch

data class DrawerItem(
    val title: String,
    val selectIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgeCount: Int? = null,
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    items: List<DrawerItem> = emptyList(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    modifier =
                    Modifier
                        .fillMaxHeight(),
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector =
                                    if (index == selectedItemIndex) {
                                        item.selectIcon
                                    } else {
                                        item.unSelectedIcon
                                    },
                                    contentDescription = item.title,
                                )
                            },
                            modifier =
                            Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding),
                        )
                    }
                }
            },
            drawerState = drawerState,
        ) {
            Scaffold(
                topBar = {
                    HomeTopBar(
                        onClickMenu = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                    )
                },
            ) { innerPadding ->
                Text(
                    text = "test",
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

@Preview
@Composable
private fun DrawerPreview() {
    val items =
        listOf(
            DrawerItem(
                title = "Home",
                selectIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Outlined.Home,
            ),
            DrawerItem(
                title = "Info",
                selectIcon = Icons.Filled.Info,
                unSelectedIcon = Icons.Outlined.Info,
            ),
            DrawerItem(
                title = "Setting",
                selectIcon = Icons.Filled.Settings,
                unSelectedIcon = Icons.Outlined.Settings,
            ),
        )

    Drawer(
        items = items,
    )
}
