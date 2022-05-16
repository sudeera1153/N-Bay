package com.takg.nbay.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppTabBar(
    modifier: Modifier = Modifier,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun AppTabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: Int,
    onTabSelected: (id: Int) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected,
        modifier = modifier,
        backgroundColor = Color(0xff0aa1dd), contentColor = Color.White,
        indicator = { tabIndicator ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabIndicator[tabSelected])
            )
        },
        divider = {}
    ) {
        titles.forEachIndexed { index, title ->
            val isSelected = index == tabSelected
            Tab(
                text = { Text(text = title) },
                selected = isSelected,
                onClick = { onTabSelected(index) },

                )
        }
    }
}