package com.goody.dalda.ui.home

sealed class AuthState {
    data object SignIn : AuthState()
    data object SignOut : AuthState()
}
