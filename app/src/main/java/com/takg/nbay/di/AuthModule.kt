package com.takg.nbay.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.takg.nbay.data.repository.AuthRepositoryImpl
import com.takg.nbay.data.repository.UserRepositoryImpl
import com.takg.nbay.domain.repository.AuthRepository
import com.takg.nbay.domain.repository.UserRepository
import com.takg.nbay.domain.use_case.CreateUser
import com.takg.nbay.domain.use_case.GetCurrentUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideUserRepository(auth: FirebaseAuth, store: FirebaseFirestore): UserRepository =
        UserRepositoryImpl(auth, store)

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        store: FirebaseFirestore,
    ): AuthRepository {

        return AuthRepositoryImpl(
            auth = auth,
            store = store
        )
    }

    @Provides
    fun provideGetCurrentUser(
        userRepository: UserRepository,
        authRepository: AuthRepository
    ): GetCurrentUser {

        return GetCurrentUser(authRepository, userRepository);
    }

    @Provides
    fun provideCreateUser(userRepository: UserRepository) = CreateUser(userRepository)

}