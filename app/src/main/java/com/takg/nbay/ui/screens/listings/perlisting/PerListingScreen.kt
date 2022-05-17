package com.takg.nbay.ui.screens.listings.perlisting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.theme.NBayTheme
import com.takg.nbay.R
import com.takg.nbay.ui.components.ExpandableCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PerListingScreen(navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        tint = Color.Black
                    )
                }

            }
        }
    ){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Image(
                painter = painterResource(id = R.drawable.box_placeholder),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Text(text = "Rs 2500",
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 8.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold
            )
            Text(text = "Sample Item Name",
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Text(text = "As Good as New",
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.subtitle2, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Row(modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 8.dp)) {
                Image(painter = painterResource(id = R.drawable.box_placeholder), contentDescription = null,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(shape = CircleShape))
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier
                    .padding(top = 9.dp)) {
                    Text(text = "Sudeera Wijenayake",
                        modifier = Modifier
                            .padding(start = 10.dp),
                        style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.W800)
                    Text(text = "Colombo,Sri Lanka",
                        modifier = Modifier
                            .padding(start = 10.dp),
                        style = MaterialTheme.typography.subtitle2, fontWeight = FontWeight.W600)
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)

            Text(text = "Sample Item Description!Sample Item Description!Sample Item Description!Sample Item Description!Sample Item Description!Sample Item Description!Sample Item Description!",
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 8.dp, top = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.caption, fontWeight = FontWeight.Light, color = Color.Black)

            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)

            ExpandableCard(
                title = "Contact Now",
                description = "Mobile - 0777557895\n\nEmail - abcd@gmail.com"
            )

        }


        
    }
}

@Preview(showBackground = true)
@Composable
fun PerListingPreview(){
    NBayTheme {
        PerListingScreen(rememberNavController())
    }
}