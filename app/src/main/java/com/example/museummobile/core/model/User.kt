package com.example.museummobile.core.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val myOffer: List<String>? = null
)