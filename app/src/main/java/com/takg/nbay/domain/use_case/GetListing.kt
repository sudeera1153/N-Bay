package com.takg.nbay.domain.use_case

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.dto.ListingDto
import com.takg.nbay.domain.repository.ListingRepository
import com.takg.nbay.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListing @Inject constructor(
    private val userRepository: UserRepository,
    private val listingRepository: ListingRepository
) {
    operator fun invoke(id: String): Flow<Resource<ListingDto>> =
        flow {
            emit(Resource.Loading())

            val listing = listingRepository.findById(id)
            val user = listing?.uid?.let {
                userRepository.findById(it)
            }
            if (listing != null) {
                val listingDto = ListingDto(
                    seller = user?.name,
                    phoneNumber = user?.phoneNumber,
                    title = listing.title,
                    desc = listing.desc,
                    isExternal = listing.isExternal,
                    imageUrl = listing.imageUrl,
                    price = listing.price
                )
                emit(Resource.Success(listingDto))

            } else {
                emit(Resource.Error("Listing not found"))
            }
        }
}