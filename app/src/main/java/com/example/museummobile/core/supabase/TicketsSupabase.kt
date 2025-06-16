package com.example.museummobile.core.supabase

import android.util.Log
import androidx.test.espresso.util.filterToList
import com.example.museummobile.core.domain.TicketsRepository
import com.example.museummobile.core.model.Tickets
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import kotlinx.datetime.LocalDate

class TicketsSupabase :  TicketsRepository {

    override suspend fun getTickets(): List<Tickets> {
        val result = supabase.from("tickets").select()
            .decodeList<Tickets>()
        return result
    }

    override suspend fun getMyTickets(tickets_ids: List<String>): List<Tickets> {
        val idsFormatted = tickets_ids.joinToString(",", "(", ")")

        val result = supabase.from("tickets")
            .select() {
                filter{
                    filter(column = "id",operator = FilterOperator.IN, value = idsFormatted)
                }
            }
            .decodeList<Tickets>()
        return result
    }

    override suspend fun addTickets(date: LocalDate, offerId: Int) {
        val ticket = Tickets(
            date_for = date,
            offer_id = offerId
        )
        try {
            val result = supabase
                .from("tickets")
                .insert(ticket)
                .decodeSingleOrNull<Tickets>()
            Result.success(result)
        } catch (e : Exception){
            Result.failure<Error>(e)
        }
    }
}