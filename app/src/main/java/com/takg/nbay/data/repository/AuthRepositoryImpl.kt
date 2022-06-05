package com.takg.nbay.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    store: FirebaseFirestore
) : AuthRepository {
    private val usersRef = store.collection("users")

    override fun isUserAuthenticatedInFirebase() = auth.currentUser != null
    override fun getCurrentUser(): FirebaseUser? = auth.currentUser

    override suspend fun signIn(email: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        if (isUserAuthenticatedInFirebase()) {
            auth.signOut()
        }

        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString(), exception = e))
        }
    }

    override suspend fun createUserInFirestore(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            auth.currentUser?.apply {
                usersRef.document(uid).set(
                    mapOf(
                        "displayName" to displayName,
                        "email" to email,
                        "photoUrl" to photoUrl?.toString(),
                        "createdAt" to serverTimestamp()
                    )
                ).await()
                emit(Resource.Success(true))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.javaClass.name, false, exception = e))
        }
    }

    override fun getFirebaseAuthState() = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    override suspend fun revokeAccess() = flow {
        try {
            emit(Resource.Loading())
            auth.currentUser?.apply {
                usersRef.document(uid).delete().await()
                delete().await()
            }
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.javaClass.name))
        }
    }

    override fun getDisplayName() = auth.currentUser?.displayName.toString()

    override fun getPhotoUrl() = auth.currentUser?.photoUrl.toString()
}