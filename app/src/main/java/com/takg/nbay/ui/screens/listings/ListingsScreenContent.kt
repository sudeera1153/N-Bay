package com.takg.nbay.ui.screens.listings

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.takg.nbay.ui.screens.listings.components.ListingItemsList

@Composable
fun ListingsScreenContent(
    viewModel: ListingViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    ListingItemsList(items = state.listings)

    if (!state.isLoading && state.error.isBlank()) {
        Toast.makeText(LocalContext.current, "No listings available", Toast.LENGTH_LONG).show()
    }

    if (state.error.isNotBlank()) {
        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
    }

    if (state.isLoading) {
        Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_LONG).show()
    }
}

@Composable
@Preview
fun ListingsScreenPreview() {

}