package com.takg.nbay.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.takg.nbay.common.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Boolean

    fun getCurrentUser(): FirebaseUser?

    suspend fun signIn(email: String, password: String): Flow<Resource<Unit>>

    suspend fun createUserInFirestore(): Flow<Resource<Boolean>>

    fun signOut()

    suspend fun revokeAccess(): Flow<Resource<Boolean>>

    fun getFirebaseAuthState(): Flow<Boolean>

    fun getDisplayName(): String

    fun getPhotoUrl(): String
}
