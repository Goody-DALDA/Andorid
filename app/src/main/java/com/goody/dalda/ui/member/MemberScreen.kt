package com.goody.dalda.ui.member

import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.ui.model.Profile
import com.goody.dalda.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen(
    viewModel: MemberViewModel = viewModel(),
    onClickSeeLoginScreen: () -> Unit = {},
    onClickSeeWithdrawScreen: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    LaunchedEffect("once") {
        viewModel.fetchProfile()
    }

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
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "close",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        MemberLayout(
            viewModel,
            Modifier.padding(innerPadding),
            onClickWithdrawButton = onClickSeeWithdrawScreen,
        )
    }
    val context = LocalContext.current
    when (val state = viewModel.logout.value) {
        is UiState.Loading -> {
        }

        is UiState.Success -> {
            Toast.makeText(context, state.data, Toast.LENGTH_SHORT).show()
            onClickSeeLoginScreen()
        }

        is UiState.Error -> {
            Toast.makeText(context, state.exception?.message, Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }
}

@Composable
fun MemberLayout(
    viewModel: MemberViewModel,
    modifier: Modifier = Modifier,
    onClickWithdrawButton: () -> Unit = {},
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
                viewModel.profile.value.nickname,
            )

            MemberInformation(viewModel.profile.value)
        }

        LogoutButton(onClick = { viewModel.requestLogout() })
        WithdrawButton(onClick = onClickWithdrawButton)
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
private fun WithdrawButton(onClick: () -> Unit) {
    Text(
        text = "탈퇴하기",
        color = Color(0xFF8E8E93),
        style = MaterialTheme.typography.titleSmall,
        modifier =
        Modifier
            .padding(start = 20.dp, top = 20.dp, bottom = 60.dp)
            .clickable { onClick() },
    )
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
fun MemberScreenPreview() {
    MaterialTheme {
        MemberScreen()
    }
}
