package com.takg.nbay.ui.screens.userprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.screens.auth.signup.SignUpScreen
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun UserProfileScreen()
{
    Column {
        UserProfileAppBar()
        UserProfileHead()
        UserProfileContent()
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NBayTheme {
        UserProfileScreen()
    }
}