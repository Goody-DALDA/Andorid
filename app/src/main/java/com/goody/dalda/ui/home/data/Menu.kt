package com.goody.dalda.ui.home.data

sealed class Menu {
    data object Announcement : Menu()

    data object Event : Menu()

    data object ContactUs : Menu()

    data object TermsOfUse : Menu()

    data object PrivacyPolicy : Menu()

    data object Link : Menu()

    data object Instagram : Menu()

    data object Profile : Menu()

    data object Login : Menu()
}
