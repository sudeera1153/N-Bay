package com.takg.nbay.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class User(
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var photoUrl: String? = null,
    var universityEmail: String? = null,
    var UniversityEmailVerifiedAt: Date? = null,
    var isVerified: Boolean = false,
    @ServerTimestamp
    var createdAt: Date? = null
)
