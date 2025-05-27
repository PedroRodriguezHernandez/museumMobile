package com.example.museummobile.core.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun logout()
    fun isLoggedIn(): Boolean
}