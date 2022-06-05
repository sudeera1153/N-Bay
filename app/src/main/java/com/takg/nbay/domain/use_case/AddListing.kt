package com.takg.nbay.domain.use_case

import com.google.firebase.auth.FirebaseUser
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.ItemCategory
import com.takg.nbay.domain.model.ItemCondition
import com.takg.nbay.domain.repository.AuthRepository
import com.takg.nbay.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddListing @Inject constructor(
    private val authRepository: AuthRepository,
    private val repository: ListingRepository
) {
    operator fun invoke(
        title: String,
        description: String,
        condition: ItemCondition,
        category: ItemCategory,
        price: Double
    ): Flow<Resource<Void>> =
        flow {
            try {
                emit(Resource.Loading())

                if (!authRepository.isUserAuthenticatedInFirebase()) {
                    emit(Resource.Error("User unauthorized"))
                }
                val user: FirebaseUser = authRepository.getCurrentUser()!!

                val response = repository.add(
                    title = title,
                    description = description,
                    price = price,
                    category = category,
                    condition = condition,
                    uid = user.uid
                )
                emit(response)

            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            }
        }
}