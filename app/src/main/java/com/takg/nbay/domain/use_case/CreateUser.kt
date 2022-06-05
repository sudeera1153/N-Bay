package com.takg.nbay.domain.use_case

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CreateUser @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(name: String, email: String, password: String): Flow<Resource<Boolean>> =
        flow {
            try {
                emit(Resource.Loading())

                val response = repository.add(email, password)
                emit(response)

            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            }
        }
}