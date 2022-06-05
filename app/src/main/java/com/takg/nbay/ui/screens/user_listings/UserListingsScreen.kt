package com.takg.nbay.ui.screens.user_listings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.domain.model.Listing
import com.takg.nbay.ui.components.AppTabBar
import com.takg.nbay.ui.components.AppTabs
import com.takg.nbay.ui.navigation.Screen
import com.takg.nbay.ui.screens.listings.components.ListingItemsList
import com.takg.nbay.ui.theme.NBayTheme

enum class ListingsTab {
    PENDING, ONGOING, FINISHED
}

@Composable
fun UserListingScreen(
    navController: NavController,
    viewModel: UserListingViewModel = hiltViewModel()
) {
    var tabSelected by remember { mutableStateOf(ListingsTab.ONGOING) }

    val state = viewModel.state.value

    if (state.error.isNotBlank()) {
        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
    }

    if (state.isLoading) {
        Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_LONG).show()
    }


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
                    ListingItemsList(items = state.listings) { listingId ->
                        navController.navigate("${Screen.SingleListing.route}/$listingId")
                    }
                }
                ListingsTab.PENDING -> {
                    ListingItemsList(items = emptyList()) { listingId ->
                        navController.navigate("${Screen.SingleListing.route}/$listingId")
                    }
                }
                ListingsTab.FINISHED -> {
                    ListingItemsList(items = emptyList()) { listingId ->
                        navController.navigate("${Screen.SingleListing.route}/$listingId")
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