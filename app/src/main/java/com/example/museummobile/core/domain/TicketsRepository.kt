package com.example.museummobile.core.domain

import com.example.museummobile.core.model.Offer
import com.example.museummobile.core.model.Tickets
import kotlinx.datetime.LocalDate

interface TicketsRepository {
    suspend fun getTickets(): List<Tickets>;
    suspend fun addTickets(date:LocalDate, offerId:Int)
    suspend fun getMyTickets(tickets_ids: List<String>): List<Tickets>
}