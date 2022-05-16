package com.takg.nbay.ui.screens.listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.R
import com.takg.nbay.ui.components.AppTabBar
import com.takg.nbay.ui.components.AppTabs
import com.takg.nbay.ui.screens.home.HomeScreen
import com.takg.nbay.ui.screens.listings.my_listings.MyListingFinishedScreen
import com.takg.nbay.ui.screens.listings.my_listings.MyListingOngoingScreen
import com.takg.nbay.ui.screens.listings.my_listings.MyListingPendingScreen

enum class HomeTab {
    PENDING, ONGOING, FINISHED
}

@Composable
fun MyListingsScreen() {
    var tabSelected by remember { mutableStateOf(HomeTab.ONGOING) }
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = Color(0xff0aa1dd), elevation = 0.dp, contentColor = Color.White) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.ArrowBack,contentDescription = "")
                    }
                    Text(
                        text = "My Listings",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )

                }

            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {
            HomeTabBar(
                tabSelected,
                onTabSelected = { tabSelected = it }
            )
            when (tabSelected) {
                HomeTab.PENDING -> MyListingPendingScreen()
                HomeTab.ONGOING -> MyListingOngoingScreen()
                HomeTab.FINISHED -> MyListingFinishedScreen()
            }
        }
    }
}

@Composable
fun HomeTabBar(
    tabSelected: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    AppTabBar { tabBarModifier ->
        AppTabs(
            modifier = Modifier.background(Color(0xff0aa1dd)),
            titles = HomeTab.values().map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { newTab ->
                onTabSelected(HomeTab.values()[newTab.ordinal])
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyListingsScreen()
}