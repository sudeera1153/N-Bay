package com.takg.nbay.di

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.takg.nbay.R
import com.takg.nbay.common.Constants.USERS_REF
import com.takg.nbay.data.repository.AuthRepositoryImpl
import com.takg.nbay.data.repository.UserRepositoryImpl
import com.takg.nbay.domain.repository.AuthRepository
import com.takg.nbay.domain.repository.UserRepository
import com.takg.nbay.domain.use_case.CreateUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Clock
import java.time.LocalDateTime

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideUsersRef(
        db: FirebaseFirestore
    ) = db.collection(USERS_REF)

    @Provides
    fun provideUserRepository(auth: FirebaseAuth, store: FirebaseFirestore): UserRepository =
        UserRepositoryImpl(auth, store)

    @Provides
    fun provideCreateUser(userRepository: UserRepository) = CreateUser(userRepository)
}