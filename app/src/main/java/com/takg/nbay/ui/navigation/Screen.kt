package com.takg.nbay.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")
    object Login : Screen("email/login")
    object SignUp : Screen("email/signup")
    object Home : Screen("home")
    object UserProfile : Screen("userprofile")
    object AddListing : Screen("listing/add")
    object UserListing : Screen("listing/user")
}