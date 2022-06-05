package com.takg.nbay.ui.screens.aboutus

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.R
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun AboutUsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        tint = Color.Black,
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )
                }
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = CutCornerShape(4.dp),
                        elevation = 40.dp
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row {
                                Image(painter = painterResource(id = R.drawable.nbay),
                                    contentDescription = "")
                            }
                            Row {
                                Text(text = "App Version 1.0.0",
                                style = MaterialTheme.typography.caption,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.LightGray
                                )
                            }
                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                            Row (
                                horizontalArrangement = Arrangement.SpaceBetween
                                    ){
                                Text(text = "The Premiere Classified Advertisement App Exclusive for NSBM Green University Town ",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                            Row {
                                Text(text = "Copyright 2021-2022 TAKG Corporation. All Rights Reserved",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                            }
                            
                        }
                    }
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun AboutUsScreenPreview() {
    NBayTheme() {
        AboutUsScreen(rememberNavController())
    }

}
