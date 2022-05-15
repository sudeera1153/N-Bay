package com.takg.nbay.ui.screens.home

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.takg.nbay.ui.components.drawer
import com.takg.nbay.ui.components.searchbar


@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(scaffoldState = scaffoldState,
        topBar = { searchbar(scaffoldState, coroutineScope) },
        drawerContent = {
            drawer(scrollState)
        }) {
        HomeContent()

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}