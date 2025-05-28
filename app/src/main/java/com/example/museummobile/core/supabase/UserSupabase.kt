package com.example.museummobile.core.supabase

import com.example.museummobile.core.domain.UserRepository
import com.example.museummobile.core.model.User
import io.github.jan.supabase.SupabaseClient

class UserSupabase(
    private val client: SupabaseClient
) : UserRepository{
    override suspend fun getUserById(id: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

}