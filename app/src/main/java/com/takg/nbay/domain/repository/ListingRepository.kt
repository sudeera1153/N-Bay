package com.takg.nbay.domain.repository

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.ItemCategory
import com.takg.nbay.domain.model.ItemCondition
import com.takg.nbay.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    fun all(): Flow<Resource<List<Listing>>>
    suspend fun findById(id: String): Listing?
    suspend fun add(
        title: String,
        description: String,
        condition: ItemCondition,
        category: ItemCategory,
        price: Double,
        uid: String,
    ): Resource<Void>

    suspend fun update(id: String, listing: Listing): Resource<Unit>
    suspend fun removeById(id: String): Resource<Unit>
}