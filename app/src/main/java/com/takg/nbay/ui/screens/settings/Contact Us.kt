package com.takg.nbay.ui.screens.settings
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.theme.NBayTheme


@Composable
fun ContactUsScreen(navController: NavController) {
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contact Us",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = CutCornerShape(4.dp),
                        elevation = 40.dp
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Row(){
                                Text(text = "Feel free to Contact NBay",
                                    style = MaterialTheme.typography.h5,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            Spacer(modifier = Modifier.padding(vertical = 10.dp))

                            Row {
                                Icon(Icons.Filled.Mail,"", modifier = Modifier.padding(end = 5.dp))
                                Text(text = "nbaytakg@gmail.com",
                                style = MaterialTheme.typography.caption)
                            }

                            Spacer(modifier = Modifier.padding(vertical = 3.dp))

                            Row {
                                Icon(Icons.Filled.Call,"", modifier = Modifier.padding(end = 5.dp))
                                Text(text = "077-6897819",
                                    style = MaterialTheme.typography.caption)
                            }

                            Spacer(modifier = Modifier.padding(vertical = 3.dp))

                            Row {
                                Icon(Icons.Filled.Call,"", modifier = Modifier.padding(end = 5.dp))
                                Text(text = "070-2596986",
                                    style = MaterialTheme.typography.caption)
                            }

                            Spacer(modifier = Modifier.padding(vertical = 3.dp))

                            Row {
                                Icon(Icons.Filled.Call,"", modifier = Modifier.padding(end = 5.dp))
                                Text(text = "077-9934939",
                                    style = MaterialTheme.typography.caption)
                            }

                            Spacer(modifier = Modifier.padding(vertical = 3.dp))

                            Row {
                                Icon(Icons.Filled.LocationCity,"", modifier = Modifier.padding(end = 5.dp))
                                Text(text = "720/C,1, Temple Road, Kelaniya",
                                    style = MaterialTheme.typography.caption)
                            }

                            Spacer(modifier = Modifier.padding(vertical = 3.dp))

                            Row {
                                Icon(Icons.Filled.Facebook,"", modifier = Modifier.padding(end = 5.dp))
                                Text(text = "https://www.facebook.com/nbay.takg",
                                    style = MaterialTheme.typography.caption)
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
fun MyListingsScreenPreview() {
    NBayTheme() {
        ContactUsScreen(rememberNavController())
    }

}



