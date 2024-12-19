package com.goody.dalda.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.RecommendAlcohol
import com.goody.dalda.ui.home.component.AlcoholCategory
import com.goody.dalda.ui.home.component.AlcoholRecommendation
import com.goody.dalda.ui.home.component.AlcoholSearchBar
import com.goody.dalda.ui.home.component.FavoriteAlcohol
import com.goody.dalda.ui.home.component.HomeTopBar
import com.goody.dalda.ui.home.component.LoginBanner
import com.goody.dalda.ui.home.component.WelcomeBanner
import com.goody.dalda.ui.home.component.navigationdrawer.HomeDrawerSheet
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onClickAlcohol: (AlcoholType) -> Unit = {}
) {
    val favoriteAlcoholInfoList by viewModel.favoriteAlcoholInfoList.collectAsStateWithLifecycle()
    val recommendAlcoholList by viewModel.recommendAlcoholList.collectAsStateWithLifecycle()
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val userName by viewModel.userName.collectAsStateWithLifecycle()
    val userEmail by viewModel.userEmail.collectAsStateWithLifecycle()
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(true) }
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsStateWithLifecycle()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    when (homeUiState) {
        is HomeUiState.CommonState -> {
            HomeScreen(
                modifier = modifier,
                query = query,
                userName = userName,
                userEmail = userEmail,
                favoriteAlcoholInfoList = favoriteAlcoholInfoList,
                recommendAlcoholList = recommendAlcoholList,
                authState = authState,
                drawerState = drawerState,
                selectedItemIndex = selectedItemIndex,
                onExpandedChange = {
                    viewModel.setHomeUiState(HomeUiState.SearchState)
                },
                onQueryChange = { query = it },
                onChangeSelectedItemIndex = { viewModel.setSelectedItemIndex(it) },
                onClickAlcohol = onClickAlcohol,
                onChangeDrawerState = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                onClickMenu = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }

        is HomeUiState.SearchState -> {
            AlcoholSearchBar(
                query = query,
                expanded = expanded,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .wrapContentHeight(),
                onQueryChange = { query = it },
                onExpandedChange = {
                    expanded = it
                    if (!expanded) {
                        viewModel.setHomeUiState(HomeUiState.CommonState)
                    }
                },
                onSearch = {},
            )
        }

        is HomeUiState.ErrorState -> {
            // TODO : Error UI
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    query: String,
    userName: String,
    userEmail: String,
    favoriteAlcoholInfoList: List<AlcoholInfo> = emptyList(),
    recommendAlcoholList: List<RecommendAlcohol> = emptyList(),
    authState: AuthState,
    drawerState: DrawerState,
    selectedItemIndex: Int,
    onExpandedChange: (Boolean) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    onChangeSelectedItemIndex: (Int) -> Unit = {},
    onClickAlcohol: (AlcoholType) -> Unit = {},
    onChangeDrawerState: () -> Unit = {},
    onClickMenu: () -> Unit = {},
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ModalNavigationDrawer(
            drawerContent = {
                HomeDrawerSheet(
                    modifier = Modifier.width(250.dp),
                    userName = if (authState == AuthState.SignIn) userName else stringResource(R.string.text_do_sign_in),
                    userEmail = if (authState == AuthState.SignIn) userEmail else stringResource(R.string.text_sign_in_recommendation),
                    selectedItemIndex = selectedItemIndex,
                    onChangeDrawerState = onChangeDrawerState,
                    onChangeSelectedItemIndex = onChangeSelectedItemIndex
                )
            },
            drawerState = drawerState
        ) {
            Scaffold(
                modifier = Modifier.padding(horizontal = 16.dp),
                topBar = {
                    HomeTopBar(
                        onClickMenu = onClickMenu,
                    )
                },
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    when (authState) {
                        AuthState.SignIn -> {
                            WelcomeBanner(
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .fillMaxWidth()
                                    .height(70.dp),
                                userName = "Dalda"
                            )
                        }

                        AuthState.SignOut -> {
                            LoginBanner(
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .fillMaxWidth()
                                    .height(120.dp),
                                text = stringResource(R.string.text_sign_in_banner),
                                onClick = { /*TODO*/ }
                            )
                        }
                    }

                    AlcoholSearchBar(
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .wrapContentHeight(),
                        query = query,
                        expanded = false,
                        onQueryChange = onQueryChange,
                        onExpandedChange = onExpandedChange,
                        onSearch = {}
                    )

                    AlcoholCategory(
                        modifier = Modifier
                            .padding(bottom = 40.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        onClickAlcohol = onClickAlcohol
                    )

                    FavoriteAlcohol(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        favoriteAlcoholInfoList = favoriteAlcoholInfoList,
                        onActionClick = { /*TODO*/ }
                    )

                    AlcoholRecommendation(
                        modifier = Modifier
                            .padding(bottom = 40.dp)
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        recommendAlcoholList = recommendAlcoholList,
                        onActionClick = { /*TODO*/ },
                        onContentsClick = { /*TODO*/ }
                    )
                }

            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val favoriteAlcoholInfoList = listOf(
        AlcoholInfo(
            id = 0,
            imgUrl = "https://picsum.photos/id/217/100/100",
            name = "소주",
            type = AlcoholType.SOJU,
            abv = "20.0%",
        ),
        AlcoholInfo(
            id = 1,
            imgUrl = "https://picsum.photos/id/2/100/100",
            name = "맥주",
            type = AlcoholType.BEER,
            abv = "20.0%",
        ),
        AlcoholInfo(
            id = 2,
            imgUrl = "https://picsum.photos/id/237/100/100",
            name = "막걸리",
            type = AlcoholType.TRADITIONAL,
            abv = "20.0%",
        )
    )
    val recommendAlcoholList = listOf(
        RecommendAlcohol(
            imgRes = "https://picsum.photos/id/217/100/100",
            comment = "이건 무슨 맛이래유?"
        ),
        RecommendAlcohol(
            imgRes = "https://picsum.photos/id/2/100/100",
            comment = "첫번째 행입니다.\n두번째 행입니다."
        ),
        RecommendAlcohol(
            imgRes = "https://picsum.photos/id/237/100/100",
            comment = "이건 어때요?"
        )
    )
    val userName = "삼겹살에 소주"
    val userEmail = "oyj7677@gmail.com"
    val authState = AuthState.SignIn

    HomeScreen(
        modifier = Modifier,
        query = "",
        userName = userName,
        userEmail = userEmail,
        favoriteAlcoholInfoList = favoriteAlcoholInfoList,
        recommendAlcoholList = recommendAlcoholList,
        authState = authState,
        drawerState = rememberDrawerState(DrawerValue.Closed),
        selectedItemIndex = 0
    )
}

@Preview
@Composable
private fun AlcoholSearchBarSignOutPreview() {
    val favoriteAlcoholInfoList = listOf(
        AlcoholInfo(
            id = 0,
            imgUrl = "https://picsum.photos/id/217/100/100",
            name = "소주",
            type = AlcoholType.SOJU,
            abv = "20.0%",
        ),
        AlcoholInfo(
            id = 1,
            imgUrl = "https://picsum.photos/id/2/100/100",
            name = "맥주",
            type = AlcoholType.BEER,
            abv = "20.0%",
        ),
        AlcoholInfo(
            id = 2,
            imgUrl = "https://picsum.photos/id/237/100/100",
            name = "막걸리",
            type = AlcoholType.TRADITIONAL,
            abv = "20.0%",
        )
    )
    val recommendAlcoholList = listOf(
        RecommendAlcohol(
            imgRes = "https://picsum.photos/id/217/100/100",
            comment = "이건 무슨 맛이래유?"
        ),
        RecommendAlcohol(
            imgRes = "https://picsum.photos/id/2/100/100",
            comment = "첫번째 행입니다.\n두번째 행입니다."
        ),
        RecommendAlcohol(
            imgRes = "https://picsum.photos/id/237/100/100",
            comment = "이건 어때요?"
        )
    )
    val userName = "삼겹살에 소주"
    val userEmail = "oyj7677@gmail.com"
    val authState = AuthState.SignOut

    HomeScreen(
        modifier = Modifier,
        query = "",
        userName = userName,
        userEmail = userEmail,
        favoriteAlcoholInfoList = favoriteAlcoholInfoList,
        recommendAlcoholList = recommendAlcoholList,
        authState = authState,
        drawerState = rememberDrawerState(DrawerValue.Closed),
        selectedItemIndex = 0
    )
}
