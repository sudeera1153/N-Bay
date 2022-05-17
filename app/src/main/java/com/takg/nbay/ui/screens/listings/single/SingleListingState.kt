package com.takg.nbay.ui.screens.listings.single

import com.takg.nbay.domain.dto.ListingDto

data class SingleListingState(
    val isLoading: Boolean = false,
    val listing: ListingDto = ListingDto(),
    val error: String = ""
)
