package com.takg.nbay.ui.screens.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun HomeScreen(){
    Text(text = "nbay")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen() 
}