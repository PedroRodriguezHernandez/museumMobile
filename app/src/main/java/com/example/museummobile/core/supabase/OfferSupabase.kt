package com.example.museummobile.core.supabase

import android.util.Log
import com.example.museummobile.core.domain.OfferRepository
import com.example.museummobile.core.model.Offer
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.result.PostgrestResult

class OfferSupabase: OfferRepository {

    override suspend fun getOfferById(id: String): Offer? {
        return try {
            supabase.from("Offer").select(){
                filter {
                    eq("id", id)
                }
            }.decodeSingleOrNull<Offer>()
        }catch (e : Exception){
            Log.e("Error", "$e")
            throw Error(e)
        }
    }

    override suspend fun getOffers(): List<Offer> {
        return supabase.from("Offer").select()
                .decodeList<Offer>()
    }
}