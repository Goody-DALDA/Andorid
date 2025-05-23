package com.goody.dalda.ui.home

import android.util.Log
import androidx.activity.compose.BackHandler
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
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.model.RecommendAlcoholUIModel
import com.goody.dalda.ui.AppPaddingSize
import com.goody.dalda.ui.component.SimpleMessageDialog
import com.goody.dalda.ui.error.ErrorPageScreen
import com.goody.dalda.ui.home.component.AlcoholCategory
import com.goody.dalda.ui.home.component.AlcoholRecommendation
import com.goody.dalda.ui.home.component.BookmarkAlcohol
import com.goody.dalda.ui.home.component.HomeBanner
import com.goody.dalda.ui.home.component.HomeTopBar
import com.goody.dalda.ui.home.component.navigationdrawer.HomeDrawerSheet
import com.goody.dalda.ui.home.data.Menu
import com.goody.dalda.ui.home.data.UserProfile
import kotlinx.coroutines.launch

private const val categoryRowMaxCount = 4
private const val DIALOG_WIDTH_RATIO = 0.85f

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onClickSearchBar: () -> Unit = {},
    onClickAlcohol: (AlcoholType) -> Unit = {},
    onClickSideMenuItem: (Menu) -> Unit = {},
    onClickSeeLoginScreen: () -> Unit = {},
    onClickCard: (AlcoholUIModel) -> Unit = {},
    onClickBookmark: () -> Unit = {},
    onClickBack:() -> Unit = {},
    onFinishActivity:() -> Unit = {},
) {
    val bookmarkList by viewModel.bookmarkList.collectAsStateWithLifecycle()
    val recommendAlcoholList by viewModel.recommendAlcoholUIModelList.collectAsStateWithLifecycle()
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsStateWithLifecycle()
    val drawerState by viewModel.drawerState.collectAsStateWithLifecycle()
    val isDialogVisible by viewModel.isDialogVisible.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchBookmarkAlcoholList()
    }

    BackHandler(
        enabled = true,
        onBack = {
            if(drawerState.isOpen) {
                scope.launch {
                    drawerState.close()
                }
            } else {
                onFinishActivity()
            }
        }
    )

    when (homeUiState) {
        is HomeUiState.CommonState -> {
            HomeScreen(
                modifier = modifier,
                userProfile = userProfile,
                bookmarkAlcoholUIModelList = bookmarkList,
                recommendAlcoholUIModelList = recommendAlcoholList,
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
            Log.e("HomeScreen", "HomeScreen: ${(homeUiState as HomeUiState.ErrorState).errorMsg}", )
            ErrorPageScreen(
                modifier = Modifier.fillMaxSize(),
                errorMessage = stringResource(id = R.string.text_occur_error),
                buttonTitle = stringResource(id = R.string.text_comeback_main),
                onClickButton = onClickBack
            )
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userProfile: UserProfile,
    bookmarkAlcoholUIModelList: List<AlcoholUIModel> = emptyList(),
    recommendAlcoholUIModelList: List<RecommendAlcoholUIModel> = emptyList(),
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
    onClickCard: (AlcoholUIModel) -> Unit = {},
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
                    userName = if (authState == AuthState.SignIn) userProfile.name else stringResource(
                        R.string.text_do_sign_in
                    ),
                    userEmail = if (authState == AuthState.SignIn) userProfile.email else stringResource(
                        R.string.text_sign_in_recommendation
                    ),
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
                        userName = userProfile.name,
                        userImg = userProfile.img,
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
                        bookmarkAlcoholUIModelList = bookmarkAlcoholUIModelList,
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
                        recommendAlcoholUIModelList = recommendAlcoholUIModelList,
                        onActionClick = { /*TODO*/ },
                        onContentsClick = { /*TODO*/ },
                    )
                }
                if (isDialogVisible) {
                    SimpleMessageDialog(
                        modifier = Modifier
                            .fillMaxWidth(DIALOG_WIDTH_RATIO)
                            .wrapContentHeight(),
                        text = stringResource(id = R.string.text_preparing),
                        buttonText = stringResource(id = R.string.text_preparing_button),
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
    val userProfile = UserProfile(
        name = "김다르다",
        email = "odh@djdj.com",
        img = "http://www.bing.com/search?q=sagittis"
    )

    val bookmarkAlcoholUIModelLists =
        listOf(
            AlcoholUIModel.Whisky(
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
            AlcoholUIModel.Beer(
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
            AlcoholUIModel.Sake(
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
            AlcoholUIModel.Soju(
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
        userProfile = userProfile,
        bookmarkAlcoholUIModelList = bookmarkAlcoholUIModelLists,
        recommendAlcoholUIModelList = emptyList(),
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