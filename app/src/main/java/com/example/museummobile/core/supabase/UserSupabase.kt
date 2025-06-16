package com.example.museummobile.core.supabase

import com.example.museummobile.core.domain.UserRepository
import com.example.museummobile.core.model.User
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.postgrest.from

class UserSupabase : UserRepository{

    override suspend fun getUserById(id: String): User? {
        try{
            val result = supabase.from("users").select(){
                filter {
                    eq("id", id)
                }
            }.decodeSingleOrNull<User>()
            return  result
        }catch ( e: Exception){
            return null }
    }

    override suspend fun updateUser(user: User): Boolean {
        return try {
            supabase.from("users")
                .update({
                    set("user", user)
                }){
                    filter { eq("id",user.id) }
                }
            true
        }catch (e : Exception){
            false
        }
    }


}