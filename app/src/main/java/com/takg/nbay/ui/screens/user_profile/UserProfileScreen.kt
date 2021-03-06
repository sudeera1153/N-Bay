package com.takg.nbay.ui.screens.user_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.navigation.Screen
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun UserProfileScreen(
    navController: NavController,
    viewMode: UserProfileViewModel = hiltViewModel()
) {
    Column {
        UserProfileAppBar(
            onBackPressed = {
                navController.popBackStack()
            },
            onAddListing = {
                navController.navigate(Screen.AddListing.route)
            }
        )
        UserProfileHead(viewMode.displayName.value)
        UserProfileContent(navController = navController)
    }

}


@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    NBayTheme {
        UserProfileScreen(rememberNavController())
    }
}