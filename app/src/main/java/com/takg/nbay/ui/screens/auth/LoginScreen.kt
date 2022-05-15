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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.PrimaryTextField
import com.takg.nbay.R
import com.takg.nbay.SocialLogin
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun LoginScreen(navController: NavController){
    var email by remember{ mutableStateOf("")}
    var password by remember{ mutableStateOf("")}
    var passwordvisibility by remember{ mutableStateOf(false)}

    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(id = R.drawable.loginvector), contentDescription ="Login Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 16.dp),
            )
            Text(text = "Login",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start).padding(vertical = 15.dp)
            )
            SocialLogin()
            Text(text = "Or Login with Email",
                color=Color.LightGray,
                style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(vertical = 15.dp))
            PrimaryTextField(
                value = email,
                modifier = Modifier
                    .fillMaxWidth(),
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
                onValueChange = {email=it}
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
                onValueChange = {password=it},
                visualTransformation = PasswordVisualTransformation()

            )


            Button(onClick = {navController.navigate("main_screen")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentPadding = PaddingValues(vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff0aa1dd),
                )) {
                Text(text = "Login",
                    color = Color.White)
            }
            Row {
                Text(text = "New to N-Bay?",
                color = Color.LightGray,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(vertical = 25.dp))
            Text(text = "Sign-Up Here!",
            color = Color(0xff0aa1dd),
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp)
                .clickable {
                    navController.navigate("signup_screen")
                })

        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NBayTheme {
        LoginScreen(rememberNavController())
    }
}


