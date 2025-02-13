package com.goody.dalda.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholCategoryStatus
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.RecommendAlcohol
import com.goody.dalda.ui.AppPaddingSize
import com.goody.dalda.ui.component.SimpleMessageDialog
import com.goody.dalda.ui.home.component.AlcoholCategory
import com.goody.dalda.ui.home.component.AlcoholRecommendation
import com.goody.dalda.ui.home.component.BookmarkAlcohol
import com.goody.dalda.ui.home.component.HomeBanner
import com.goody.dalda.ui.home.component.HomeTopBar
import com.goody.dalda.ui.home.component.navigationdrawer.HomeDrawerSheet
import com.goody.dalda.ui.home.data.Menu
import kotlinx.coroutines.launch

const val categoryRowMaxCount = 4

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onClickSearchBar: () -> Unit = {},
    onClickAlcohol: (AlcoholType) -> Unit = {},
    onClickSideMenuItem: (Menu) -> Unit = {},
    onClickSeeLoginScreen: () -> Unit = {},
    onClickCard: (AlcoholData) -> Unit = {},
    onClickBookmark: () -> Unit = {},
) {
    val bookmarkAlcoholDataList by viewModel.bookmarkAlcoholDataList.collectAsStateWithLifecycle()
    val recommendAlcoholList by viewModel.recommendAlcoholList.collectAsStateWithLifecycle()
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val userName by viewModel.userName.collectAsStateWithLifecycle()
    val userEmail by viewModel.userEmail.collectAsStateWithLifecycle()
    val userImg by viewModel.userImg.collectAsStateWithLifecycle()
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsStateWithLifecycle()
    val drawerState by viewModel.drawerState.collectAsStateWithLifecycle()
    val isDialogVisible by viewModel.isDialogVisible.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    LaunchedEffect("once") {
        viewModel.fetchProfile()
        viewModel.fetchBookmarkAlcoholList()
    }

    when (homeUiState) {
        is HomeUiState.CommonState -> {
            HomeScreen(
                modifier = modifier,
                userName = userName,
                userEmail = userEmail,
                userImg = userImg,
                bookmarkAlcoholDataList = bookmarkAlcoholDataList,
                recommendAlcoholList = recommendAlcoholList,
                authState = authState,
                drawerState = drawerState,
                isDialogVisible = isDialogVisible,
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
                },
                onClickSideMenuItem = onClickSideMenuItem,
                onClickLogin = onClickSeeLoginScreen,
                onClickCard = onClickCard,
                onClickBookmark = onClickBookmark,
                onClickDialogCancel = {
                    viewModel.setDialogVisible(false)
                },
                onClickUnServiceAlcohol = {
                    viewModel.setDialogVisible(true)
                },
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
    userImg: String,
    bookmarkAlcoholDataList: List<AlcoholData> = emptyList(),
    recommendAlcoholList: List<RecommendAlcohol> = emptyList(),
    authState: AuthState,
    drawerState: DrawerState,
    isDialogVisible: Boolean,
    selectedItemIndex: Int,
    onChangeSelectedItemIndex: (Int) -> Unit = {},
    onClickSearch: () -> Unit = {},
    onClickAlcohol: (AlcoholType) -> Unit = {},
    onChangeDrawerState: () -> Unit = {},
    onClickMenu: () -> Unit = {},
    onClickSideMenuItem: (Menu) -> Unit = {},
    onClickLogin: () -> Unit = {},
    onClickCard: (AlcoholData) -> Unit = {},
    onClickBookmark: () -> Unit = {},
    onClickDialogCancel: () -> Unit = {},
    onClickUnServiceAlcohol: () -> Unit = {},
) {
    Surface(
        color = Color.White,
        modifier = modifier.fillMaxSize(),
    ) {
        ModalNavigationDrawer(
            drawerContent = {
                HomeDrawerSheet(
                    modifier = Modifier.width(250.dp),
                    userName = if (authState == AuthState.SignIn) userName else stringResource(R.string.text_do_sign_in),
                    userEmail = if (authState == AuthState.SignIn) userEmail else stringResource(R.string.text_sign_in_recommendation),
                    authState = authState,
                    selectedItemIndex = selectedItemIndex,
                    onChangeDrawerState = onChangeDrawerState,
                    onChangeSelectedItemIndex = onChangeSelectedItemIndex,
                    onClickMenu = onClickSideMenuItem,
                )
            },
            drawerState = drawerState,
        ) {
            Scaffold(
                modifier =
                Modifier.padding(
                    horizontal = AppPaddingSize.HORIZONTAL.dp,
                ),
                topBar = {
                    HomeTopBar(
                        onClickMenu = onClickMenu,
                    )
                },
                containerColor = Color.White,
            ) { innerPadding ->
                Column(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                ) {
                    HomeBanner(
                        authState = authState,
                        userName = userName,
                        userImg = userImg,
                        modifier =
                        Modifier
                            .padding(bottom = 30.dp)
                            .fillMaxWidth()
                            .height(120.dp),
                        onClickLogin = onClickLogin,
                    )

                    Image(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clickable { onClickSearch() },
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(id = R.drawable.img_search_bar),
                        contentDescription = null,
                    )

                    AlcoholCategory(
                        modifier =
                        Modifier
                            .padding(bottom = 40.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        rowCount = categoryRowMaxCount,
                        onClickAlcohol = { alcoholType ->
                            if (alcoholType.categoryStatus == AlcoholCategoryStatus.WAITING) {
                                onClickUnServiceAlcohol()
                            } else {
                                onClickAlcohol(alcoholType)
                            }
                        },
                    )

                    BookmarkAlcohol(
                        modifier =
                        Modifier
                            .wrapContentHeight()
                            .padding(bottom = 40.dp)
                            .fillMaxWidth(),
                        bookmarkAlcoholDataList = bookmarkAlcoholDataList,
                        onActionClick = onClickBookmark,
                        onClickCard = onClickCard,
                    )

                    AlcoholRecommendation(
                        modifier =
                        Modifier
                            .padding(bottom = 40.dp)
                            .alpha(0f)
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        recommendAlcoholList = recommendAlcoholList,
                        onActionClick = { /*TODO*/ },
                        onContentsClick = { /*TODO*/ },
                    )
                }
                if (isDialogVisible) {
                    SimpleMessageDialog(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentHeight(),
                        text = stringResource(id = R.string.dialog_preparing),
                        buttonText = stringResource(id = R.string.dialog_preparing_button),
                        onClickCancel = onClickDialogCancel,
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
    val userImg = "img"
    val bookmarkAlcoholDataList =
        listOf(
            AlcoholData.Whisky(
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
                country = "독일",
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
                comment = "맛있어요",
            ),
        )
    val authState = AuthState.SignIn
    val drawerState = DrawerState(initialValue = DrawerValue.Closed)
    val selectedItemIndex = 0
    val isDialogVisible = false
    HomeScreen(
        modifier = Modifier,
        userName = userName,
        userEmail = userEmail,
        userImg = userImg,
        bookmarkAlcoholDataList = bookmarkAlcoholDataList,
        recommendAlcoholList = emptyList(),
        authState = authState,
        drawerState = drawerState,
        isDialogVisible = isDialogVisible,
        selectedItemIndex = selectedItemIndex,
        onChangeSelectedItemIndex = {},
        onClickSearch = {},
        onClickAlcohol = {},
        onChangeDrawerState = {},
        onClickMenu = {},
    )
}
