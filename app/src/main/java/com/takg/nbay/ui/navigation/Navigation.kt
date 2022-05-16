package com.takg.nbay.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.screens.auth.AuthScreen
import com.takg.nbay.ui.screens.auth.login.LoginScreen
import com.takg.nbay.ui.screens.auth.signup.SignUpScreen
import com.takg.nbay.ui.screens.home.HomeScreen
import com.takg.nbay.ui.screens.listings.create.AddListingScreen
import com.takg.nbay.ui.screens.userprofile.UserProfileScreen
import com.takg.nbay.ui.screens.welcome.AnimatedSplashScreen

@Composable
fun Navigation(viewModel: NavigationViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var startDestination = Screen.Auth.route

    if (viewModel.isUserAuthenticated) {
        startDestination = Screen.Home.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen() {
                navController.popBackStack()
                navController.navigate(Screen.Auth.route)
            }
        }
        composable(route = Screen.Auth.route) {
            AuthScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.UserProfile.route) {
            UserProfileScreen()
        }
        composable(route = Screen.AddListing.route) {
            AddListingScreen(navController = navController)
        }
    }
}

