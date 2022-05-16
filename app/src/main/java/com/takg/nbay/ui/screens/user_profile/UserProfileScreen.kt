package com.takg.nbay.ui.screens.user_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
fun UserProfileScreenPreview() {
    NBayTheme {
        UserProfileScreen()
    }
}