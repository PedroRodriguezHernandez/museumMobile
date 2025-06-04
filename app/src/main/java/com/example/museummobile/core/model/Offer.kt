package com.example.museummobile.core.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: Int? = null,
    val name: String,
    val price: Double,
    val age: Int? = null,
    val startDate: LocalDate,
    val endDate: LocalDate? = null
)