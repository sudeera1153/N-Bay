package com.takg.nbay.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takg.nbay.ui.theme.NBayTheme
import com.takg.nbay.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun searchbar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Box(modifier = Modifier.padding(10.dp)) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 6.dp,
            modifier = Modifier.requiredHeight(50.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(Alignment.Center)
            ) {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(Icons.Default.Menu, "Menu")
                }
                //TextField(value = "text",onValueChange = {newText ->newText})
                Text(
                    text = "Search Your Item", modifier = Modifier
                        .padding(start = 0.dp)
                        .weight(2.0f), textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(id = R.drawable.nbay_cropped),
                    contentDescription = "Nbay Icon", modifier = Modifier.size(50.dp)
                )

            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview1() {
    NBayTheme {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        searchbar(scaffoldState, coroutineScope)

    }
}