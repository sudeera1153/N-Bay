package com.takg.nbay.ui.screens.listings.my_listings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takg.nbay.data.DummyData
import com.takg.nbay.data.DummyData.listContent
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun MyListingOngoingScreen(){
    val contents = remember {DummyData.listContent.filter { it.id>0 }}

    LazyColumn{
        items(listContent){DummyDataContent->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { },
                shape = RoundedCornerShape(14.dp)) {
                Column {
                    Image(painter = painterResource(id = DummyDataContent.imageUrl), contentDescription = "Cover")
                    Text(DummyDataContent.title, style = MaterialTheme.typography.h6, modifier = Modifier.padding(horizontal = 14.dp))
                    Text(DummyDataContent.desc, modifier = Modifier.padding(horizontal = 14.dp))
                }

            }

        }
    }
}