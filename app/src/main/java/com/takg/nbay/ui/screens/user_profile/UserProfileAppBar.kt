package com.takg.nbay.ui.screens.user_profile

import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun UserProfileAppBar(
    onBackPressed: () -> Unit,
    onAddListing: () -> Unit
) {
    Surface(color = Color.White) {
        TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = Color(0xff0aa1dd)) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Outlined.ArrowBack, contentDescription = "")
                }
                OutlinedButton(
                    onClick = onAddListing,
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
        val callback: () -> Unit = {}
        UserProfileAppBar(callback, callback);
    }
}