package com.takg.nbay.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun OutLinedDropDown(
    value: String,
    labelText: String,
    options: Map<String, String>,
    onSelect: (key: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(value) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {
        Box {
            OutlinedTextField(
                value = selectedText,
                readOnly = true,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text(labelText, color = Color.LightGray) },
                trailingIcon = {
                    Icon(icon, "Content Description",
                        Modifier.clickable { expanded = !expanded })
                },
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) {
                        textFieldSize.width.toDp()
                    })
            ) {
                options.forEach { item ->
                    DropdownMenuItem(onClick = {
                        onSelect(item.key)
                        selectedText = item.value
                        expanded = false
                    }) {
                        Text(text = item.value)
                    }
                }
            }
        }
    }
}