package com.takg.nbay.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.takg.nbay.common.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Boolean

    suspend fun signIn(email: String, password: String): Flow<Resource<Unit>>

    suspend fun oneTapSignInWithGoogle(): Flow<Resource<BeginSignInResult>>

    suspend fun oneTapSignUpWithGoogle(): Flow<Resource<BeginSignInResult>>

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Flow<Resource<Boolean>>

    suspend fun createUserInFirestore(): Flow<Resource<Boolean>>

    suspend fun signOut(): Flow<Resource<Boolean>>

    suspend fun revokeAccess(): Flow<Resource<Boolean>>

    fun getFirebaseAuthState(): Flow<Boolean>

    fun getDisplayName(): String

    fun getPhotoUrl(): String
}
