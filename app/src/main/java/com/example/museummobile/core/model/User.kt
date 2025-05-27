package com.example.museummobile.core.model

import kotlinx.datetime.LocalDateTime

data class User(
    val id: String? = null,
    val name: String,
    val email: String,
    val rol: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime? = null,
    val enable: Boolean? = null
)