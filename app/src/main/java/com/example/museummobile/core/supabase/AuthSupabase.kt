package com.example.museummobile.core.supabase

import android.util.Log
import com.example.museummobile.core.domain.AuthRepository
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthSupabase : AuthRepository{

    override suspend fun login(userEmail: String, userPassword: String): Result<Unit> {
        return try {
            val response = supabase.auth.signInWith(Email) {
                email = userEmail
                password = userPassword
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(userEmail: String, userPassword: String, name: String): Result<Unit> {
        return try {
            val response = supabase.auth.signUpWith(Email){
                email = userEmail
                password = userPassword
                data = buildJsonObject {
                    put("name", JsonPrimitive(name))
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        supabase.auth.signOut()
    }

     override fun isLoggedIn(): Boolean {
        val result =  supabase.auth.currentSessionOrNull() != null
         return result
    }
}