package com.takg.nbay.components.navigation

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.Nbay
import com.takg.nbay.R
import com.takg.nbay.ui.theme.NBayTheme
import kotlinx.coroutines.delay

@Composable
fun navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            Nbay()
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true ){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(durationMillis = 500,
                easing = {OvershootInterpolator(2f).getInterpolation(it)})
        )

        delay(3000L)
        navController.navigate("main_screen")
    }
    Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.nbay_cropped), contentDescription ="Logo" )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NBayTheme {
        navigation()
    }
}