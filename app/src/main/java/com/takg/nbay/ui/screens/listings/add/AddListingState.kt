package com.takg.nbay.ui.screens.listings.add


data class AddListingState(
    val isCreating: Boolean = false,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val condition: String = "",
    val conditionError: String? = null,
    val category: String = "",
    val categoryError: String? = null,
    val price: String = "",
    val priceError: String? = null,
)
