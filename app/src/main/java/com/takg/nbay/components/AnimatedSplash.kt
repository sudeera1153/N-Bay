package com.takg.nbay.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.takg.nbay.ui.theme.Purple700
import com.takg.nbay.R

@Composable
fun AnimatedSplash(){

}

@Composable
fun Splash(){
    Box(modifier = Modifier
        .background(if (isSystemInDarkTheme()) Color.Black else Purple700)
        .fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.nbay_cropped), contentDescription ="Splash Title")

    }
}