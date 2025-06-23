package com.example.museummobile.core.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: String,
    val title: String,
    val body: String,
    val start_date : LocalDate,
    val museum_id: String? = null
)
