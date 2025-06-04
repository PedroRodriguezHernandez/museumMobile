package com.example.museummobile.core.supabase

import android.content.Context
import android.util.Log
import com.example.museummobile.core.domain.UserRepository
import com.example.museummobile.core.model.User
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

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

    override suspend fun addOfferToMyList(user: User): Boolean {
        return try {
            supabase.from("user")
                .update({
                    set("myOffer", user.myOffer)
                }){
                    filter { eq("id", user.id) }
                }
            return true
        }catch (e: Exception){
            throw Error(e)
            return false
        }
    }

}