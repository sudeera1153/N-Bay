package com.takg.nbay.ui.screens.userprofile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.screens.listings.CreateListing
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun UserProfileAppBar()
{
    Surface(color = Color.White) {
        TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = Color(0xff0aa1dd)) {
            Row(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Outlined.ArrowBack,contentDescription = "")
                }
                OutlinedButton(
                    onClick = {},
                    shape = CircleShape,
                    border = BorderStroke(width = 1.dp, color = Color.White),
                ) {
                   Text("Create New Listings", color = Color.Black)
                }

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    NBayTheme {
        UserProfileAppBar();
    }
}