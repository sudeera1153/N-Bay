package com.takg.nbay.ui.screens.listings.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takg.nbay.domain.model.Listing
import java.text.NumberFormat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListingItemsList(items: List<Listing>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
    ) {
        items(items.size) { idx ->
            val listing = items[idx]
            Box(modifier = Modifier.padding(horizontal = 6.dp)) {
                ListingLineItem(
                    title = listing.title ?: "",
                    price = "₨ ${listing.price}",
                    imageUrl = listing.imageUrl
                )
            }

        }
    }
}

@Preview
@Composable
fun ListingItemsListPreview() {
    val listings = listOf(
        Listing(id = "1", title = "Sample Item 1", price = 1500.00, isExternal = false),
        Listing(id = "2", title = "Sample Item 2", price = 1250.35, isExternal = false),
        Listing(id = "3", title = "Sample Item 3", price = 15000.00, isExternal = false),
        Listing(id = "4", title = "Sample Item 4", price = 250.00, isExternal = false)
    )

    ListingItemsList(listings)
}
