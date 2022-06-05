package com.takg.nbay.domain.repository

import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.User

interface UserRepository {
    suspend fun findById(id: String): User?
    suspend fun add(email: String, password: String): Resource<Boolean>
}