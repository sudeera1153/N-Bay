package com.takg.nbay.ui.screens.listings

import CategoryDropDown
import ConditionDropDown
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.PrimaryTextField
import com.takg.nbay.R
import com.takg.nbay.ui.screens.auth.SignUpScreen
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun CreateListing(navHost: NavController){
    var item_title by remember{ mutableStateOf("") }
    var item_desc by remember{ mutableStateOf("") }
    var item_price by remember{ mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        tint = Color.Black
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
            Text(text = "Create a Listing",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Image(painter = painterResource(id = R.drawable.signupbg), contentDescription ="Signup BG",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedButton(
                onClick = {},
                modifier = Modifier.width(190.dp),
                contentPadding = PaddingValues(vertical = 15.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                /*Image(painter = painterResource(id = R.drawable.socialbutton_google),
                    contentDescription = "GoogleLogo",
                    modifier = Modifier.padding(24.dp))*/
                Text(
                    "Upload Item Pictures",
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 10.dp)
                )}
            PrimaryTextField(
                value = item_title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                maxLines = 1,
                label = {
                    Text(text = "Email", color = Color.LightGray)
                },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email Icon") },
                onValueChange = { item_title = it },
            )
            PrimaryTextField(
                value = item_desc,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                maxLines = 1,
                label = {
                    Text(text = "Description", color = Color.LightGray)
                },
                leadingIcon = {Icon(Icons.Outlined.Person, contentDescription = "Username Icon") },
                onValueChange = {item_desc=it},
            )
            CategoryDropDown()
            ConditionDropDown()
            PrimaryTextField(
                value = item_price,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                maxLines = 1,
                label = {
                    Text(text = "Price", color = Color.LightGray)
                },
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email Icon") },
                onValueChange = { item_price = it },)

                Button(onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 45.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentPadding = PaddingValues(vertical = 15.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff0aa1dd),
                    )) {
                    Text(text = "Place Item",
                        color = Color.White)

                }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    NBayTheme {
        CreateListing(rememberNavController());
    }
}