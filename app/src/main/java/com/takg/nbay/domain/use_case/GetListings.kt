package com.takg.nbay.domain.use_case

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.Listing
import com.takg.nbay.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetListings @Inject constructor(
    private val repository: ListingRepository
) {
    operator fun invoke(): Flow<Resource<List<Listing>>> =
        flow {
            emit(Resource.Loading())

            repository.all().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        if (result.e is IOException) {
                            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
                        } else {
                            emit(result)
                        }
                    }
                    is Resource.Loading -> { /* Do nothing */
                    }
                    is Resource.Success -> {
                        emit(result)
                    }
                }
            }
        }
}