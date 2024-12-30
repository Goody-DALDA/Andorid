package com.goody.dalda.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.RecommendAlcohol
import com.goody.dalda.ui.home.component.AlcoholCategory
import com.goody.dalda.ui.home.component.AlcoholRecommendation
import com.goody.dalda.ui.home.component.FavoriteAlcohol
import com.goody.dalda.ui.home.component.HomeTopBar
import com.goody.dalda.ui.home.component.LoginBanner
import com.goody.dalda.ui.home.component.WelcomeBanner
import com.goody.dalda.ui.home.component.navigationdrawer.HomeDrawerSheet
import com.goody.dalda.ui.search.AlcoholSearchBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onClickSearchBar: () -> Unit = {},
    onClickAlcohol: (AlcoholType) -> Unit = {}
) {
    val favoriteAlcoholDataList by viewModel.favoriteAlcoholDataList.collectAsStateWithLifecycle()
    val recommendAlcoholList by viewModel.recommendAlcoholList.collectAsStateWithLifecycle()
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val userName by viewModel.userName.collectAsStateWithLifecycle()
    val userEmail by viewModel.userEmail.collectAsStateWithLifecycle()
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(true) }
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsStateWithLifecycle()

    val searchResult by viewModel.searchAlcoholDataList.collectAsStateWithLifecycle()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    when (homeUiState) {
        is HomeUiState.CommonState -> {
            HomeScreen(
                modifier = modifier,
                userName = userName,
                userEmail = userEmail,
                favoriteAlcoholDataList = favoriteAlcoholDataList,
                recommendAlcoholList = recommendAlcoholList,
                authState = authState,
                drawerState = drawerState,
                selectedItemIndex = selectedItemIndex,
                onChangeSelectedItemIndex = { viewModel.setSelectedItemIndex(it) },
                onClickAlcohol = onClickAlcohol,
                onClickSearch = onClickSearchBar,
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
                searchResultList = searchResult,
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
    userName: String,
    userEmail: String,
    favoriteAlcoholDataList: List<AlcoholData> = emptyList(),
    recommendAlcoholList: List<RecommendAlcohol> = emptyList(),
    authState: AuthState,
    drawerState: DrawerState,
    selectedItemIndex: Int,
    onChangeSelectedItemIndex: (Int) -> Unit = {},
    onClickSearch: () -> Unit = {},
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

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clickable { onClickSearch() },
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(id = R.drawable.img_search_bar),
                        contentDescription = null,
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
                        favoriteAlcoholDataList = favoriteAlcoholDataList,
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
    val userName = "Dalda"
    val userEmail = "nei@gmail.com"
    val favoriteAlcoholDataList = listOf(
        AlcoholData.Wisky(
            id = 0,
            name = "위스키",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_whiskey,
            volume = "750ml",
            abv = "40%",
            type = "위스키",
            country = "스코틀랜드",
            price = 170000,
            taste = "써요",
            aroma = "부드러워요",
            finish = "깔끔해요",
        ),
        AlcoholData.Beer(
            id = 0,
            name = "카스",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_beer,
            volume = "355ml",
            abv = "4.5%",
            appearance = 2.28f,
            flavor = 4.4f,
            mouthfeel = 2.0f,
            aroma = 3.3f,
            type = "밀맥주",
            country = "독일"
        ),
        AlcoholData.Sake(
            id = 0,
            name = "사케",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_sake,
            volume = "750ml",
            abv = "15%",
            price = 30000,
            taste = "달아요",
            aroma = "좋아요",
            finish = "시원해요",
            country = "일본",
        ),
        AlcoholData.Soju(
            id = 0,
            name = "소주",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_soju,
            volume = "360ml",
            abv = "17%",
            price = 5000,
            comment = "맛있어요"
        )
    )
    val authState = AuthState.SignIn
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItemIndex = 0
    
    HomeScreen(
        modifier = Modifier,
        userName = userName,
        userEmail = userEmail,
        favoriteAlcoholDataList = favoriteAlcoholDataList,
        recommendAlcoholList = emptyList(),
        authState = authState,
        drawerState = drawerState,
        selectedItemIndex = selectedItemIndex,
        onChangeSelectedItemIndex = {},
        onClickAlcohol = {},
        onClickSearch = {},
        onChangeDrawerState = {},
        onClickMenu = {}
    )
}
