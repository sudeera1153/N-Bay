package com.takg.nbay.domain.use_case

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveListing @Inject constructor(
    private val repository: ListingRepository
) {
    operator fun invoke(id: String): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                repository.removeById(id)
                emit(Resource.Success(Unit))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "", exception = e))
            }
            emit(Resource.Error("Listing not found"))
        }
}