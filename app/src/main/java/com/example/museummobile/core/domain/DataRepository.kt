package com.example.museummobile.core.domain

interface DataRepository {
    suspend fun <T> getAllFrom(table: String, clazz: Class<T>): Result<List<T>>
    suspend fun <T> insertInto(table: String, data: T): Result<Unit>
}