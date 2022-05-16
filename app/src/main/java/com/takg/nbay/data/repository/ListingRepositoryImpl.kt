package com.takg.nbay.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.ItemCategory
import com.takg.nbay.domain.model.ItemCondition
import com.takg.nbay.domain.model.Listing
import com.takg.nbay.domain.repository.AuthRepository
import com.takg.nbay.domain.repository.ListingRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val auth: AuthRepository,
    store: FirebaseFirestore
) : ListingRepository {

    private val listingsRef = store.collection("listings")

    override fun getListings(): Flow<Resource<List<Listing>>> = callbackFlow {
        val snapshotListener = listingsRef.orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val listings = mutableListOf<Listing>()

                    snapshot.documents.forEach { doc ->
                        val listing = doc.toObject(Listing::class.java)
                        listing?.id = doc.id
                        listing?.let { listings.add(it) }
                    }

                    Resource.Success(listings)
                } else {
                    Resource.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addListing(
        title: String,
        description: String,
        condition: ItemCondition,
        category: ItemCategory,
        price: Double,
        uid: String
    ): Resource<Void> {
        try {
            val id = listingsRef.document().id
            val listing = Listing(
                id = id,
                title = title,
                desc = description,
                isExternal = true,
                price = price,
            )
            val addition = listingsRef.document(id).set(listing).await()
            return Resource.Success(addition)
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString(), exception = e)
        }
    }
}