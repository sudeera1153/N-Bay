package com.takg.nbay.domain.dto

data class ListingDto(
    var seller: String? = null,
    var phoneNumber: String? = null,
    var title: String? = null,
    var isExternal: Boolean? = null,
    var price: Double? = null,
    var desc: String? = null,
    var imageUrl: String? = null,
)
