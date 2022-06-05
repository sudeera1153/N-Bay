package com.takg.nbay.ui.screens.user_profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun UserProfileHead(displayName: String) {
    var isInternal by remember { mutableStateOf(false) }

    Surface(color = Color(0xff0aa1dd)) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.Person, contentDescription = "",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .border(
                        border = BorderStroke((4.dp), color = Color.White),
                        shape = CircleShape
                    )
                    .clip(shape = CircleShape)
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = displayName, color = Color.White, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.padding(top = 2.dp))
            Text(
                text = "Internal", color = Color.White, style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .border(
                        BorderStroke((1.dp), color = Color.White),
                        shape = CircleShape
                    )
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            ListCountView()

        }
    }
}

@Composable
fun ListCountView() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Column {
                Text(
                    text = "Pending Listings",
                    color = Color.White,
                    style = MaterialTheme.typography.caption
                )
                Text(text = "000", color = Color.White, style = MaterialTheme.typography.h5)
            }
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Column {
                Text(
                    text = "In Progress Listings",
                    color = Color.White,
                    style = MaterialTheme.typography.caption
                )
                Text(text = "000", color = Color.White, style = MaterialTheme.typography.h5)
            }
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Column() {
                Text(
                    text = "Finished Listings",
                    color = Color.White,
                    style = MaterialTheme.typography.caption
                )
                Text(text = "000", color = Color.White, style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    NBayTheme {
        UserProfileHead("John Doe");
    }
}