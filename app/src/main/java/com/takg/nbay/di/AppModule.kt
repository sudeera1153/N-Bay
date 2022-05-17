package com.takg.nbay.di

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.takg.nbay.common.Constants.USERS_REF
import com.takg.nbay.data.repository.ListingRepositoryImpl
import com.takg.nbay.data.repository.UserRepositoryImpl
import com.takg.nbay.domain.repository.AuthRepository
import com.takg.nbay.domain.repository.ListingRepository
import com.takg.nbay.domain.repository.UserRepository
import com.takg.nbay.domain.use_case.AddListing
import com.takg.nbay.domain.use_case.CreateUser
import com.takg.nbay.domain.use_case.GetListings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideListingRepository(
        auth: AuthRepository,
        store: FirebaseFirestore
    ): ListingRepository =
        ListingRepositoryImpl(auth, store)

    @Provides
    fun provideAddListing(auth: AuthRepository, listingRepository: ListingRepository) =
        AddListing(auth, listingRepository)

    @Provides
    fun provideGetListing(listingRepository: ListingRepository) =
        GetListings(listingRepository)

}