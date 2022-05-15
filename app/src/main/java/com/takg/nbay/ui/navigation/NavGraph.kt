package com.takg.nbay.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                navController.navigate(Screen.Home.route)
            }
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

    }
}

