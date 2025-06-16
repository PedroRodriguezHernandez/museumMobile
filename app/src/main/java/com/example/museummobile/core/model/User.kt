package com.example.museummobile.core.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val my_tickets: List<String>? = null
)