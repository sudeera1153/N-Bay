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
    store: FirebaseFirestore
) : ListingRepository {

    private val listingsRef = store.collection("listings")

    override suspend fun findById(id: String): Listing? {
        val document = listingsRef.document(id).get().await()
        val listing = document.toObject(Listing::class.java)
        listing?.id = document.id

        return listing
    }

    override fun all(): Flow<Resource<List<Listing>>> = callbackFlow {
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

    override fun findByUserId(uid: String): Flow<Resource<List<Listing>>> = callbackFlow {
        val snapshotListener =
            listingsRef.whereEqualTo("uid", uid)
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

    override suspend fun add(
        title: String,
        description: String,
        condition: ItemCondition,
        category: ItemCategory,
        price: Double,
        uid: String
    ): Resource<Void> = try {
        val id = listingsRef.document().id
        val listing = Listing(
            id = id,
            title = title,
            desc = description,
            isExternal = true,
            price = price,
            uid = uid
        )
        val addition = listingsRef.document(id).set(listing).await()
        Resource.Success(addition)
    } catch (e: Exception) {
        Resource.Error(e.message ?: e.toString(), exception = e)
    }

    override suspend fun update(id: String, listing: Listing): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun removeById(id: String): Resource<Unit> {
        return try {
            val document = listingsRef.document(id).delete()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error", exception = e)
        }
    }
}