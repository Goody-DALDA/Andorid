package com.goody.dalda.ui.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.goody.dalda.R
import com.goody.dalda.ui.home.AuthState

@Composable
fun HomeBanner(
    authState: AuthState,
    modifier: Modifier = Modifier,
    onClickSignIn: () -> Unit = {}
) {
    when (authState) {
        AuthState.SignIn -> {
            WelcomeBanner(
                modifier = modifier,
                userName = "Dalda"
            )
        }

        AuthState.SignOut -> {
            LoginBanner(
                modifier = modifier,
                text = stringResource(R.string.text_sign_in_banner),
                onClick = { /*TODO*/ }
            )
        }
    }
}