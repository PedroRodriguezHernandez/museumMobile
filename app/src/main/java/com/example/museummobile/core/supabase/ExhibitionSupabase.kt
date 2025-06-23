package com.example.museummobile.core.supabase

import android.util.Log
import com.example.museummobile.core.domain.ExhibitionRepository
import com.example.museummobile.core.model.Exhibition
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.rpc

class ExhibitionSupabase: ExhibitionRepository {

    override suspend fun getExhibitionById(id: String): Exhibition? {
        return try {
            val resource = supabase.postgrest.rpc("record_view", mapOf("p_exhibition_id" to id))
                .decodeSingleOrNull<Exhibition>()
            resource

        }catch (e:Exception){
            Log.d("Prueba", "getExhibitionById: $e")
            throw RuntimeException(e)
        }
    }
}