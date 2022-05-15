package com.takg.nbay.domain.repository

import com.takg.nbay.common.Resource

interface UserRepository {
    suspend fun create(email: String, password: String): Resource<Boolean>
}