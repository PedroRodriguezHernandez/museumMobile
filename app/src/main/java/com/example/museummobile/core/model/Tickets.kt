package com.example.museummobile.core.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Tickets (
    val id:String? = null,
    val date_for: LocalDate,
    val age: String? = null,
    val name: String? = null,
    val price: Float? = null,
    val offer_id: Int
)