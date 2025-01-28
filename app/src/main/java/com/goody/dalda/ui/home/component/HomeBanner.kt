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
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit = {},
) {
    when (authState) {
        AuthState.SignIn -> {
            WelcomeBanner(
                modifier = modifier,
                userName = "Dalda",
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

@Preview
@Composable
private fun HomeBannerSignInPrev() {
    HomeBanner(
        authState = AuthState.SignIn,
    )
}

@Preview
@Composable
private fun HomeBannerSignOutPrev() {
    HomeBanner(
        authState = AuthState.SignOut,
    )
}