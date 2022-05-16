package com.takg.nbay.domain.repository

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.ItemCategory
import com.takg.nbay.domain.model.ItemCondition
import com.takg.nbay.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {

    fun getListings(): Flow<Resource<List<Listing>>>

    suspend fun addListing(
        title: String,
        description: String,
        condition: ItemCondition,
        category: ItemCategory,
        price: Double,
        uid: String,
    ): Resource<Void>
}