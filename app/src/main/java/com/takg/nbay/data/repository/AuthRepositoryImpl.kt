package com.takg.nbay.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue.serverTimestamp
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
    private var oneTapClient: SignInClient,
    private var signInRequest: BeginSignInRequest,
    private var signUpRequest: BeginSignInRequest,
    private var signInClient: GoogleSignInClient,
    private val usersRef: CollectionReference
) : AuthRepository {
    override fun isUserAuthenticatedInFirebase() = auth.currentUser != null

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

    override suspend fun oneTapSignInWithGoogle() = flow {
        try {
            emit(Resource.Loading())
            val result = oneTapClient.beginSignIn(signInRequest).await()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.javaClass.name, exception = e))
        }
    }

    override suspend fun oneTapSignUpWithGoogle() = flow {
        try {
            emit(Resource.Loading())
            val result = oneTapClient.beginSignIn(signUpRequest).await()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.javaClass.name, exception = e))
        }
    }

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Flow<Resource<Boolean>> =
        flow {
            try {
                emit(Resource.Loading())
                val authResult = auth.signInWithCredential(googleCredential).await()
                val isNewUser = authResult.additionalUserInfo?.isNewUser
                emit(Resource.Success(isNewUser ?: false))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.javaClass.name, exception = e))
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

    override suspend fun signOut() = flow {
        try {
            emit(Resource.Loading())
            auth.signOut()
            oneTapClient.signOut().await()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.javaClass.name))
        }
    }

    override suspend fun revokeAccess() = flow {
        try {
            emit(Resource.Loading())
            auth.currentUser?.apply {
                usersRef.document(uid).delete().await()
                delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
            }
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.javaClass.name))
        }
    }

    override fun getDisplayName() = auth.currentUser?.displayName.toString()

    override fun getPhotoUrl() = auth.currentUser?.photoUrl.toString()
}