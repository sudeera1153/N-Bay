package com.takg.nbay.ui.screens.listings.index

import com.takg.nbay.domain.model.Listing

data class ListingsViewState(
    val isLoading: Boolean = false,
    val listings: List<Listing> = emptyList(),
    val error: String = ""
)