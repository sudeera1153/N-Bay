package com.takg.nbay.ui.screens.userprofile

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun UserProfileHead()
{
    Surface(color = Color.LightGray){
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Filled.Person, contentDescription = "",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .border(
                        border = BorderStroke((4.dp), color = Color.Black),
                        shape = CircleShape
                    )
                    .clip(shape = CircleShape)
                    .padding(8.dp),
            )
            Spacer(modifier = Modifier.padding(8.dp))



        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    NBayTheme {
        UserProfileHead();
    }
}