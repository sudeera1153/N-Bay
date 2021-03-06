package com.takg.nbay.ui.screens.user_profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.navigation.Screen
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun UserProfileContent(navController: NavController) {
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                          navController.navigate(Screen.UserListing.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp, bottom = 25.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, color = Color.Black), shape = CircleShape),
                contentPadding = PaddingValues(vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                )
            ) {
                Text(text = "My Listings")
            }

            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, color = Color.Black), shape = CircleShape),
                contentPadding = PaddingValues(vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                )
            ) {
                Text(text = "Payments")
            }

            Button(
                onClick = {
                    navController.navigate(Screen.AboutUs.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, bottom = 25.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, color = Color.Black), shape = CircleShape),
                contentPadding = PaddingValues(vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                )
            ) {
                Text(text = "About")
            }

            Button(
                onClick = {
                    navController.navigate(Screen.ContactUs.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, color = Color.Black), shape = CircleShape),
                contentPadding = PaddingValues(vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                )
            ) {
                Text(text = "Contact Us")
            }


        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
    NBayTheme {
        UserProfileContent(rememberNavController())
    }
}