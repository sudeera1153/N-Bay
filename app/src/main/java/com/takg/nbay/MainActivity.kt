package com.takg.nbay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.components.drawer
import com.takg.nbay.ui.components.searchbar
import com.takg.nbay.ui.navigation.SetupNavGraph
import com.takg.nbay.ui.theme.NBayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBayTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Nbay() {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(scaffoldState = scaffoldState,
        topBar = { searchbar(scaffoldState, coroutineScope) },
        drawerContent = {
            drawer(scrollState)
        }) {

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NBayTheme {
        Nbay()
    }
}

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = Color.LightGray,
            focusedIndicatorColor = MaterialTheme.colors.primary,
            leadingIconColor = Color.LightGray,
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions ?: KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
        ),
        isError = isError,
        singleLine = singleLine,
        maxLines = maxLines,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        onValueChange = onValueChange
    )
}

@Composable
fun SocialLogin(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            /*Image(painter = painterResource(id = R.drawable.socialbutton_google),
                contentDescription = "GoogleLogo",
                modifier = Modifier.padding(24.dp))*/
            Text(
                "Login with Google",
                color = Color.LightGray,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            /*Image(painter = painterResource(id = R.drawable.socialbutton_fb),
                contentDescription = "FBLogo",
                modifier = Modifier.padding(24.dp))*/
            Text(
                "Login with Facebook",
                color = Color.LightGray,
                modifier = Modifier.padding(start = 15.dp)
            )

        }

    }
}

@Composable
fun SocialSignUp(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            /*Image(painter = painterResource(id = R.drawable.socialbutton_google),
                contentDescription = "GoogleLogo",
                modifier = Modifier.padding(24.dp))*/
            Text(
                "Sign-up with Google",
                color = Color.LightGray,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            /*Image(painter = painterResource(id = R.drawable.nbay_cropped),
                contentDescription = "FBLogo",
                modifier = Modifier.padding(24.dp))*/
            Text(
                "Sign-up with Facebook",
                color = Color.LightGray,
                modifier = Modifier.padding(start = 10.dp)
            )

        }

    }
}
