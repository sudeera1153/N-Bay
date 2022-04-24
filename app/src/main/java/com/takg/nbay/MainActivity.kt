package com.takg.nbay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takg.nbay.ui.screens.home.HomeScreen
import com.takg.nbay.ui.theme.NBayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBayTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    Card(elevation = 6.dp,modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .size(200.dp)
            .padding(bottom = 100.dp)) {
            Image(painter = painterResource(id = R.drawable.page_title_edited),
                contentScale = ContentScale.Fit,
                contentDescription ="N-Bay Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 550.dp)
            )


        }

        Column {
            Text(text = "Welcome To N-Bay",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 180.dp),
                textAlign = TextAlign.Center,
                fontSize = 43.sp,
                fontFamily = FontFamily.Monospace

            )
        }

        Row(modifier = Modifier.padding(top = 500.dp)){
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Log in With Google")
                }

                Spacer(Modifier.height(10.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Sign up with E-Mail")
                    }

                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NBayTheme {
        MainScreen()
    }
}