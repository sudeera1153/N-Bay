package com.takg.nbay.domain.use_case

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.dto.ListingDto
import com.takg.nbay.domain.model.User
import com.takg.nbay.domain.repository.AuthRepository
import com.takg.nbay.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<Resource<User>> =
        flow {
            emit(Resource.Loading())

            val authUser = authRepository.getCurrentUser()
            if (authUser == null) {
                emit(Resource.Error("User not logged in"))
            } else {
                userRepository.findById(authUser.uid).let { user ->
                    if (user == null) {
                        emit(Resource.Error("User with id does not exist"))
                    } else {
                        emit(Resource.Success(user))
                    }
                }
            }

        }
}