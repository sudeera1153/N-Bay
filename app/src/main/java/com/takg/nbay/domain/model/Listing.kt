package com.takg.nbay.domain.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

enum class ListingStatus {
    PENDING, APPROVED, PENDING_PAYMENT, PAID
}

data class Listing(
    var id: String,
    var title: String,
    var isExternal: Boolean,
    var status: ListingStatus = ListingStatus.PENDING,
    var price: Double,
    var desc: String? = null,
    var imageUrl: String? = null,

    @ServerTimestamp
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
)