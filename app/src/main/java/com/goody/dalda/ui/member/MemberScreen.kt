package com.goody.dalda.ui.member

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.ui.model.Profile
import com.goody.dalda.ui.state.UiState
import kotlinx.coroutines.launch

@Composable
fun MemberScreen(
    viewModel: MemberViewModel = viewModel(),
    onClickSeeLoginScreen: () -> Unit = {},
    onClickSeeWithdrawScreen: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val logoutState by viewModel.logoutState.collectAsStateWithLifecycle()
    val profile by viewModel.profile.collectAsStateWithLifecycle()

    LaunchedEffect("once") {
        viewModel.fetchProfileNew()
    }

    MemberScreenNew(
        logoutState,
        profile,
        onClickSeeLoginScreen,
        onClickSeeWithdrawScreen,
        onClickLogout = { viewModel.requestLogoutNew() },
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreenNew(
    state: UiState<String>,
    profile: Profile,
    onClickSeeLoginScreen: () -> Unit = {},
    onClickSeeWithdrawScreen: () -> Unit = {},
    onClickLogout: () -> Unit = {},
    onClickClose: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "회원 상세",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onClickClose) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "close",
                        )
                    }
                },
            )
        },
        snackbarHost = {
            coroutineScope.launch {
                when (state) {
                    is UiState.Empty -> TODO()
                    is UiState.Error -> {
                        snackBarState.showSnackbar(state.exception?.message ?: "에러 발생")
                    }

                    is UiState.Loading -> TODO()
                    is UiState.Success -> {
                        onClickSeeLoginScreen()
                        snackBarState.showSnackbar(state.data)
                    }

                    is UiState.Uninitialized -> TODO()
                }
            }
        },
    ) { innerPadding ->
        MemberLayout(
            profile,
            Modifier.padding(innerPadding),
            onClickWithdrawButton = onClickSeeWithdrawScreen,
            onClickLogout = onClickLogout,
        )
    }
}

@Composable
fun MemberLayout(
    profile: Profile,
    modifier: Modifier = Modifier,
    onClickWithdrawButton: () -> Unit = {},
    onClickLogout: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MemberProfile(
                R.drawable.img_profile,
                profile.nickname,
            )

            MemberInformation(profile)
        }

        LogoutButton(onClick = onClickLogout)

        Text(
            text = "탈퇴하기",
            color = Color(0xFF8E8E93),
            style = MaterialTheme.typography.titleSmall,
            modifier =
                Modifier
                    .padding(start = 20.dp, top = 20.dp, bottom = 60.dp)
                    .clickable { onClickWithdrawButton() },
        )
    }
}

@Composable
private fun MemberInformation(profile: Profile) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
    ) {
        MemberAttribute(
            title = "좋아하는 주종",
            content = "소주",
        )
        MemberAttribute(
            title = "메일 주소",
            content = profile.email,
        )
    }
}

@Composable
private fun MemberProfile(
    imageRes: Int,
    nickname: String,
) {
    Column(
        modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "",
            modifier =
                Modifier
                    .width(70.dp)
                    .height(70.dp),
        )

        Text(
            text = nickname,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp),
        )
    }
}

@Composable
private fun LogoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraSmall,
        colors =
            ButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                disabledContentColor = MaterialTheme.colorScheme.onSurface,
            ),
        border = BorderStroke(1.dp, Color(0xFFDDDDDF)),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "로그아웃 하기",
            color = Color(0xFF8E8E93),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun MemberAttribute(
    title: String,
    content: String,
) {
    Row(modifier = Modifier.padding(top = 10.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = content,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Right,
            color = Color.Gray,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MemberScreenNewPreview() {
    val logoutState = UiState.Success("로그아웃 성공")
    val profile = Profile("nickname", "email")

    MemberScreenNew(
        logoutState,
        profile,
        onClickSeeLoginScreen = {},
        onClickSeeWithdrawScreen = {},
    )
}
