package com.takg.nbay.domain.repository

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {

    fun getListings(): Flow<Resource<List<Listing>>>

    suspend fun addListing(listing: Listing): Flow<Resource<Void?>>
}