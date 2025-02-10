package com.goody.dalda.ui.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.R
import com.goody.dalda.ui.home.AuthState

@Composable
fun HomeBanner(
    authState: AuthState,
    userName: String,
    userImg: String,
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit = {},
) {
    when (authState) {
        AuthState.SignIn -> {
            WelcomeBanner(
                authState = authState,
                userName = userName,
                userImg = userImg,
                modifier = modifier,
            )
        }

        AuthState.SignOut -> {
            LoginBanner(
                text = stringResource(R.string.text_sign_in_banner),
                onClick = onClickLogin,
                modifier = modifier,
            )
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 120,
)
@Composable
private fun HomeBannerSignInPrev() {
    val userName = "userName"
    val userImg = "userImg"

    HomeBanner(
        authState = AuthState.SignIn,
        userName = userName,
        userImg = userImg,
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeBannerSignOutPrev() {
    val userName = "userName"
    val userImg = "userImg"

    HomeBanner(
        authState = AuthState.SignOut,
        userName = userName,
        userImg = userImg,
    )
}
