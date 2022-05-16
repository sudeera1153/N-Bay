package com.takg.nbay.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.Listing
import com.takg.nbay.domain.repository.AuthRepository
import com.takg.nbay.domain.repository.ListingRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val auth: AuthRepository,
    private val db: FirebaseFirestore
) : ListingRepository {

    override fun getListings(): Flow<Resource<List<Listing>>> = callbackFlow {

        val collection = db.collection("listings")
        val snapshotListener = collection.orderBy("created", Query.Direction.DESCENDING)
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

    override suspend fun addListing(listing: Listing): Flow<Resource<Void?>> {
        TODO("Not yet implemented")
    }
}