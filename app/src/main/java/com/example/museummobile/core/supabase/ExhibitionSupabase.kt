package com.example.museummobile.core.supabase

import android.util.Log
import com.example.museummobile.core.domain.ExhibitionRepository
import com.example.museummobile.core.model.Exhibition
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlin.math.log

class ExhibitionSupabase: ExhibitionRepository {

    override suspend fun getExhibitionById(id: String): Exhibition? {
        return try {
            supabase.from("exhibition").select(Columns.ALL){
                filter {
                    eq("id", id)
                }
            }.decodeSingleOrNull<Exhibition>()
        }catch (e:Exception){
            null
        }
    }
}