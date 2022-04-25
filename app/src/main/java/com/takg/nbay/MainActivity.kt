package com.takg.nbay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takg.nbay.components.WelcomeScreen
import com.takg.nbay.components.drawer
import com.takg.nbay.components.navigation.navigation
import com.takg.nbay.components.searchbar
import com.takg.nbay.ui.screens.home.HomeScreen
import com.takg.nbay.ui.theme.NBayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBayTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    navigation()

                }
            }
        }
    }
}

@Composable
fun Nbay(){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold( scaffoldState = scaffoldState,
        topBar = { searchbar(scaffoldState,coroutineScope) },
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