package com.takg.nbay.domain.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

enum class ListingStatus {
    PENDING, APPROVED, PENDING_PAYMENT, PAID
}

data class Listing(
    var id: String? = null,
    var title: String? = null,
    var isExternal: Boolean? = null,
    var status: ListingStatus = ListingStatus.PENDING,
    var price: Double? = null,
    var desc: String? = null,
    var imageUrl: String? = null,

    @ServerTimestamp
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
)