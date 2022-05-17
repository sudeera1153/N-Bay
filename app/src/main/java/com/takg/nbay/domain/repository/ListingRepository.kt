package com.takg.nbay.domain.repository

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.ItemCategory
import com.takg.nbay.domain.model.ItemCondition
import com.takg.nbay.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    /**
     * Find all listings
     */
    fun all(): Flow<Resource<List<Listing>>>

    /**
     * Find listing by its id
     */
    suspend fun findById(id: String): Listing?

    /**
     * Add a listing
     */
    suspend fun add(
        title: String,
        description: String,
        condition: ItemCondition,
        category: ItemCategory,
        price: Double,
        uid: String,
    ): Resource<Void>

    /**
     * Update a listing with new content
     */
    suspend fun update(id: String, listing: Listing): Resource<Unit>

    /**
     * Remove listing by  its id
     */
    suspend fun removeById(id: String): Resource<Unit>
}