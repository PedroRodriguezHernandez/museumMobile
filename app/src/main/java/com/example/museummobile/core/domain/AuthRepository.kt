package com.example.museummobile.core.domain

interface AuthRepository {
    suspend fun login(userEmail: String, userPassword: String): Result<Unit>
    suspend fun signUp(userEmail: String, userPassword: String): Result<Unit>
    suspend fun logout()
    fun isLoggedIn(): Boolean
}