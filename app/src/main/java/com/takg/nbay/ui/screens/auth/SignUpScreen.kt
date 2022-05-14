package com.takg.nbay.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.PrimaryTextField
import com.takg.nbay.R
import com.takg.nbay.SocialSignUp
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun SignUpScreen(navController: NavController)
{
    var username by remember{ mutableStateOf("")}
    var email by remember{ mutableStateOf("")}
    var password by remember{ mutableStateOf("")}
    var passwordvisibility by remember{ mutableStateOf(false)}
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                IconButton(onClick = {navController.popBackStack()}) {
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
            Image(painter = painterResource(id = R.drawable.signupbg), contentDescription ="Signup BG",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(vertical = 16.dp),
            )
            Text(text = "Sign-Up",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            PrimaryTextField(
                value = username,
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
                    Text(text = "Username", color = Color.LightGray)
                },
                leadingIcon = {Icon(Icons.Outlined.Person, contentDescription = "Username Icon") },
                onValueChange = {username=it},
            )
            PrimaryTextField(
                value = email,
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
                    Text(text = "Email", color = Color.LightGray)
                },
                leadingIcon = {Icon(Icons.Outlined.Email, contentDescription = "Email Icon") },
                onValueChange = {email=it},
            )
            PrimaryTextField(
                value = password,
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
                    Text(text = "Password", color = Color.LightGray)
                },
                leadingIcon = {Icon(Icons.Outlined.VpnKey, contentDescription = "Password Icon") },
                trailingIcon = {
                    IconButton(onClick = {passwordvisibility = !passwordvisibility}) {
                        val icon = if(passwordvisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                        Icon(icon, contentDescription = "Visibility Password")
                    }
                },
                visualTransformation = if(passwordvisibility) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = {password=it},
            )
            Button(onClick = {
                navController.navigate("login_screen")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp, bottom = 25.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentPadding = PaddingValues(vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff0aa1dd),
                )
            ){
                Text(text = "Join N-Bay",
                    color = Color.White)
            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NBayTheme {
        SignUpScreen(rememberNavController())
    }
}