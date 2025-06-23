package com.example.museummobile.core.supabase

import com.example.museummobile.core.domain.MuseumRepository
import com.example.museummobile.core.model.Museum
import com.example.museummobile.core.model.Offer
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.postgrest.from

class MuseumSupabase : MuseumRepository {
    override suspend fun getMuseum(): List<Museum> {
        val result = supabase.from("museum").select()
            .decodeList<Museum>()
        return  result
    }
}