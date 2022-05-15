package com.takg.nbay.data

import com.takg.nbay.R

data class DummyDataContent(
    val id: Int,
    val title: String,
    val desc: String,
    val imageUrl: Int,
    val type: String)

object DummyData{
    val listContent = listOf(
        DummyDataContent(
            id = 1,
            title = "Sample Item 1",
            desc = "Rs.1500.00",
            imageUrl = R.drawable.sampleimage,
            type = "Type A"
        ),
        DummyDataContent(
            id = 2,
            title = "Sample Item 2",
            desc = "Rs 1250.35",
            imageUrl = R.drawable.sampleimage,
            type = "Type B"
        ),
        DummyDataContent(
            id = 3,
            title = "Sample Item 3",
            desc = "Rs 15000.00",
            imageUrl = R.drawable.sampleimage,
            type = "Type C"
        ),
        DummyDataContent(
            id = 5,
            title = "Sample Item 5",
            desc = "Rs 1250.32",
            imageUrl = R.drawable.sampleimage,
            type = "Type A"
        ),
        DummyDataContent(
            id = 6,
            title = "Sample Item 6",
            desc = "Rs12000.00",
            imageUrl = R.drawable.sampleimage,
            type = "Type D"
        ),
        DummyDataContent(
            id = 7,
            title = "Sample Item 9",
            desc = "Rs 1690.00",
            imageUrl = R.drawable.sampleimage,
            type = "Type B"
        )
    )
}