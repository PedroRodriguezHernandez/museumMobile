package com.example.museummobile.core.supabase

import android.util.Log
import com.example.museummobile.core.domain.NewsRepository
import com.example.museummobile.core.model.News
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow

class NewsSupabase :  NewsRepository {
    override suspend fun getNews(): List<News> {
        return try {
            supabase.from("news").select()
                .decodeList<News>()
                .sortedByDescending { it.start_date }
        }catch (err : Exception){
            err.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getNewsById(id: String): News {
        return try {
            supabase.from("news").select(){
                filter {
                    eq("id", id)
                }
            }.decodeSingle<News>()
        }catch (e : Exception){
            Log.e("Error", "$e")
            throw Error(e)
        }
    }

}