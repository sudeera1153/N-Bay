package com.takg.nbay.ui.screens.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import com.takg.nbay.common.Resource.*
import com.takg.nbay.ui.navigation.Screen
import com.takg.nbay.ui.screens.auth.components.SocialButton
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            SocialButton(provider = "Google") {

            }

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            SocialButton(provider = "Facebook") {

            }

            Button(
                onClick = {
                    navController.navigate(Screen.SignUp.route)
                },
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
                    text = "Sign up with email",
                    color = Color.White
                )
            }

            Row {
                Text(
                    text = "Already have an account?",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(vertical = 25.dp)
                )
                Text(text = "Log in",
                    color = Color(0xff0aa1dd),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(vertical = 25.dp, horizontal = 10.dp)
                        .clickable {
                            navController.navigate(Screen.Login.route)
                        })

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    NBayTheme {
        AuthScreen(rememberNavController())
    }
}