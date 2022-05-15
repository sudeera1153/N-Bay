package com.takg.nbay.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.takg.nbay.ui.screens.auth.AuthScreen
import com.takg.nbay.ui.screens.auth.login.LoginScreen
import com.takg.nbay.ui.screens.auth.signup.SignUpScreen
import com.takg.nbay.ui.screens.home.HomeScreen
import com.takg.nbay.ui.screens.welcome.AnimatedSplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
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
            HomeScreen()
        }
    }
}

