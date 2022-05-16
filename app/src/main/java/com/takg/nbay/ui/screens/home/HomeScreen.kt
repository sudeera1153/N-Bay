package com.takg.nbay.ui.screens.home

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.components.Drawer
import com.takg.nbay.ui.components.SearchBar
import com.takg.nbay.ui.screens.listings.index.ListingsScreenContent


@Composable
fun HomeScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { SearchBar(scaffoldState, coroutineScope) },
        drawerContent = {
            Drawer(scrollState, navController)
        },
    ) {

        ListingsScreenContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen(rememberNavController())
}