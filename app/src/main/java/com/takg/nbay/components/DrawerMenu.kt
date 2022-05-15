package com.takg.nbay.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takg.nbay.data.DrawerData
import com.takg.nbay.ui.theme.NBayTheme
import com.takg.nbay.ui.theme.Nsbmblue

@Composable
fun drawer(scrollState: ScrollState){

    val MenuList = listOf(
        DrawerData.Home,
        DrawerData.Divider,
        DrawerData.HdrMyAccount,
        DrawerData.Profile,
        DrawerData.MyListings,
        DrawerData.HdrCategories,
        DrawerData.Apparel,
        DrawerData.Electronics,
        DrawerData.JobsAndRecruitments,
        DrawerData.Accommodation,
        DrawerData.Educational,
        DrawerData.More,
        DrawerData.Divider,
        DrawerData.Settings,
        DrawerData.LogOut
    )

    Column (Modifier.verticalScroll(scrollState)){
        Text(text = "N-Bay",
            color = Nsbmblue,
            modifier = Modifier.padding(start = 20.dp,top = 20.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        MenuList.forEach{
            item ->
            when{
                item.isDivider ->{
                    Divider(modifier = Modifier.padding(bottom = 20.dp,top = 20.dp))
                }
                item.isHeader ->{
                    Text(text = item.title!!,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(start = 20.dp,bottom = 20.dp,top = 20.dp))
                }
                else ->{
                    Button(onClick = { /*TODO*/ },colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White),elevation = null) {
                        DrawerItem(item = item)
                    }
                }
            }

        }
    }

}

@Composable
fun DrawerItem(item: DrawerData){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(top = 16.dp)) {
        Image(imageVector = item.icon!!, contentDescription = item.title!!,
            modifier = Modifier.weight(0.5f)
        )
        Text(text = item.title, modifier = Modifier.weight(2.0f))
    }
}

@Preview
@Composable
fun drawerprewiw(){
    NBayTheme {


    }
}