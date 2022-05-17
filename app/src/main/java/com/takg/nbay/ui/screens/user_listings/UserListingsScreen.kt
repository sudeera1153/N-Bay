package com.takg.nbay.ui.screens.user_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.domain.model.Listing
import com.takg.nbay.ui.components.AppTabBar
import com.takg.nbay.ui.components.AppTabs
import com.takg.nbay.ui.screens.listings.components.ListingItemsList
import com.takg.nbay.ui.theme.NBayTheme

enum class ListingsTab {
    PENDING, ONGOING, FINISHED
}

@Composable
fun UserListingScreen(navController: NavController) {
    var tabSelected by remember { mutableStateOf(ListingsTab.ONGOING) }

    val listings = listOf(
        Listing(id = "1", title = "Sample Item 1", price = 1500.00, isExternal = false),
        Listing(id = "2", title = "Sample Item 2", price = 1250.35, isExternal = false),
        Listing(id = "3", title = "Sample Item 3", price = 15000.00, isExternal = false),
        Listing(id = "4", title = "Sample Item 4", price = 250.00, isExternal = false)
    )


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color(0xff0aa1dd),
                elevation = 0.dp,
                contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                    ) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "")
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
                tabSelected.ordinal,
                onTabSelected = { tabSelected = ListingsTab.values()[it] }
            )
            when (tabSelected) {
                ListingsTab.ONGOING -> {
                    ListingItemsList(items = listings) {

                    }
                }
                ListingsTab.PENDING -> {
                    ListingItemsList(items = listings) {

                    }
                }
                ListingsTab.FINISHED -> {
                    ListingItemsList(items = emptyList()) {

                    }
                }
            }
        }
    }
}

@Composable
fun HomeTabBar(
    tabSelected: Int,
    onTabSelected: (id: Int) -> Unit
) {
    AppTabBar { _ ->
        AppTabs(
            modifier = Modifier.background(Color(0xff0aa1dd)),
            titles = ListingsTab.values().map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { id ->
                onTabSelected(id)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyListingsScreenPreview() {
    NBayTheme() {
        UserListingScreen(rememberNavController())
    }

}