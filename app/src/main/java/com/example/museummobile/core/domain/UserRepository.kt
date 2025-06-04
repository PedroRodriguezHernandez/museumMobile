package com.example.museummobile.core.domain

import com.example.museummobile.core.model.User

interface UserRepository {
    suspend fun getUserById(id: String): User?
    suspend fun updateUser(user: User): Boolean
    suspend fun addOfferToMyList(user: User): Boolean
}