package com.takg.nbay.ui.screens.auth.signup

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.R
import com.takg.nbay.common.Resource
import com.takg.nbay.ui.components.PrimaryTextField
import com.takg.nbay.ui.navigation.Screen
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    val state = viewModel.state
    val context = LocalContext.current

    var passwordvisibility by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = context) {
        viewModel.signUpEvents.collect {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> print("Loading createUser")
                is Resource.Success -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Auth.route)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
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
            Image(
                painter = painterResource(id = R.drawable.signupbg),
                contentDescription = "Signup BG",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(vertical = 16.dp),
            )
            Text(
                text = "Sign-Up",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            PrimaryTextField(
                value = state.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                maxLines = 1,
                label = {
                    Text(text = "Name", color = Color.LightGray)
                },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Username Icon") },
                onValueChange = { viewModel.onEvent(SignUpFormEvent.NameChanged(it)) },
                isError = state.nameError != null
            )
            if (state.nameError != null) {
                Text(
                    text = state.nameError,
                    color = MaterialTheme.colors.error
                )
            }
            PrimaryTextField(
                value = state.email,
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
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email Icon") },
                onValueChange = { viewModel.onEvent(SignUpFormEvent.EmailChanged(it)) },
                isError = state.emailError != null
            )
            if (state.emailError != null) {
                Text(
                    text = state.emailError,
                    color = MaterialTheme.colors.error
                )
            }
            PrimaryTextField(
                value = state.password,
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
                leadingIcon = { Icon(Icons.Outlined.VpnKey, contentDescription = "Password Icon") },
                trailingIcon = {
                    IconButton(onClick = { passwordvisibility = !passwordvisibility }) {
                        val icon =
                            if (passwordvisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                        Icon(icon, contentDescription = "Visibility Password")
                    }
                },
                visualTransformation = if (passwordvisibility) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = { viewModel.onEvent(SignUpFormEvent.PasswordChanged(it)) },
                isError = state.passwordError != null
            )
            if (state.passwordError != null) {
                Text(
                    text = state.passwordError,
                    color = MaterialTheme.colors.error
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxSize()
            ) {
                Checkbox(
                    checked = state.terms,
                    onCheckedChange = { viewModel.onEvent(SignUpFormEvent.AcceptTerms(it)) },
                )
                Text(
                    text = "I have agree to the privacy policy",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.LightGray
                )
            }

            if (state.termsError != null) {
                Text(
                    text = state.termsError,
                    color = MaterialTheme.colors.error
                )
            }


            Button(
                onClick = {
                    viewModel.onEvent(SignUpFormEvent.Submit)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp, bottom = 25.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentPadding = PaddingValues(vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff0aa1dd),
                )
            ) {
                Text(
                    text = "Join N-Bay",
                    color = Color.White
                )
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