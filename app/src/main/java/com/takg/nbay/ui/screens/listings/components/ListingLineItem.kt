package com.takg.nbay.ui.screens.listings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.takg.nbay.R
import com.takg.nbay.ui.theme.NBayTheme
import java.text.NumberFormat
import java.util.*

@Composable
fun ListingLineItem(title: String, price: String, imageUrl: String? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { },
        shape = RoundedCornerShape(14.dp)
    ) {

        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.box_placeholder),
                contentDescription = title,
            )
            Text(
                text = price,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 14.dp)
            )
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 14.dp)
            )
        }

    }
}

@Preview
@Composable
fun ListingItemPreview() {
    val title = "Apple Iphone 13 Pro"
    val price = 320_000.50
    val formatter = NumberFormat.getCurrencyInstance()
    val formattedPrice = formatter.format(price)

    NBayTheme {
        Surface(color = MaterialTheme.colors.background) {
            ListingLineItem(title, formattedPrice)
        }
    }
}