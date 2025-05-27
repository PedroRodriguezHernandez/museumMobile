package com.example.museummobile.core.model

import kotlinx.datetime.LocalDateTime

data class Offer(
    val id: String? = null,
    val name: String,
    val price: Double,
    val age: Int? = null,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime? = null
)