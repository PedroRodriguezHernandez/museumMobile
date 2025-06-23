package com.example.museummobile.core.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: Int,
    val name: String,
    val price: Double,
    val age: String? = null,
    val start_date: LocalDate,
    val end_date: LocalDate? = null,
    val museum_id: String,
)