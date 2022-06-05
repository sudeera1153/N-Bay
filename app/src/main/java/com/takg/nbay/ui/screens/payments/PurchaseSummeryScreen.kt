package com.takg.nbay.ui.screens.payments

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.theme.NBayTheme


@Composable
fun ExternalListingPaymentScreen(navController: NavController) {
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
                text = "Listing Billing",
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = CutCornerShape(4.dp),
                        elevation = 40.dp
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Item Description",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )
                            Column(modifier = Modifier.padding(start = 8.dp, top = 5.dp)) {
                                Row() {
                                    Text(
                                        text = "Item Name -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "Sample Item",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                }
                                Row() {
                                    Text(
                                        text = "Item Price -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "Rs2500",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                }
                                Row() {
                                    Text(
                                        text = "Item Category -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "Apparel",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                }
                                Row() {
                                    Text(
                                        text = "Item Condition -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "Brand New",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                            Divider(
                                color = Color.LightGray,
                                thickness = 2.dp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            )
                            Text(
                                text = "Seller's Description",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )
                            Column(modifier = Modifier.padding(start = 8.dp, top = 5.dp)) {
                                Row() {
                                    Text(
                                        text = "Seller Name -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "Sudeera Wijenayake",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                }
                                Row() {
                                    Text(
                                        text = "Seller Email -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "abs@gmail.com",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                            Divider(
                                color = Color.LightGray,
                                thickness = 2.dp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            )
                            Text(
                                text = "Purchase Details",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )
                            Column(modifier = Modifier.padding(start = 8.dp, top = 5.dp)) {
                                Row() {
                                    Text(
                                        text = "Per Day Rate -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "Rs150",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                }
                                Row() {
                                    Text(
                                        text = "Publishing Day Count -",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    //need to add number picker button
                                }
                            }
                            Divider(
                                color = Color.LightGray,
                                thickness = 2.dp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            )

                            Column(modifier = Modifier.padding(start = 8.dp, top = 5.dp)) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = "Total Amount = ",
                                        style = MaterialTheme.typography.h5,
                                        fontWeight = FontWeight.ExtraBold, /*fontSize = 18.sp*/
                                    )
                                    Text(
                                        text = "Rs150",
                                        style = MaterialTheme.typography.h5,
                                        fontWeight = FontWeight.ExtraBold, /*fontSize = 18.sp*/
                                    )
                                }
                            }


                        }

                    }

                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 45.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        contentPadding = PaddingValues(vertical = 15.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff0aa1dd),
                        )
                    ) {
                        Text(
                            text = "Proceed to Card Payment",
                            color = Color.White
                        )

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
        ExternalListingPaymentScreen(rememberNavController())
    }

}



