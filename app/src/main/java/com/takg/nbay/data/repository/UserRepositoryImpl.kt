package com.takg.nbay.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.User
import com.takg.nbay.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val store: FirebaseFirestore
) : UserRepository {

    override suspend fun create(email: String, password: String): Resource<Boolean> {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()

            result.user?.apply {

                val user = User(email = email, photoUrl = photoUrl?.toString())
                store.collection("users").document(uid).set(user).await()
                return Resource.Success(true)
            }

        } catch (e: FirebaseAuthException) {
            return Resource.Error(e.message.toString(), false, exception = e)
        }
        return Resource.Error("Unknown State")
    }

}