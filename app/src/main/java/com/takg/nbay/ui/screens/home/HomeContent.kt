package com.takg.nbay.ui.screens.home

import android.util.Log.i
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.Coil
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.google.accompanist.coil.rememberCoilPainter
import coil.compose.rememberImagePainter
import com.takg.nbay.data.BaseChip
import com.takg.nbay.data.DummyData
import com.takg.nbay.data.DummyData.listContent
import com.takg.nbay.data.DummyDataContent
import com.takg.nbay.ui.screens.auth.LoginScreen
import com.takg.nbay.ui.theme.NBayTheme

@Composable
fun HomeContent(){
    val contents = remember {DummyData.listContent.filter { it.id>0 }}
    
    /*Column(horizontalAlignment = Alignment.Start) {
        Text(text = "aaaaaaa",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(modifier = Modifier.padding(vertical = 8.dp).clickable {  })
        {
            items(
                count = contents.size,
                itemContent = {index -> 
                    BaseChip(selected = contents[index].id==1, text =contents[index].type, modifier = Modifier.padding(8.dp))
                }
            )
        }*/
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



/*
@Composable
fun  ContentListItem(content: DummyDataContent){

}*/
@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    NBayTheme {
        HomeContent()
    }
}
