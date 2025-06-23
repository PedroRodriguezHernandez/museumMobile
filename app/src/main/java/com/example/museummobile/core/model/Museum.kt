package com.example.museummobile.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Museum (
    val id: String,
    val name: String,
    val maximum_capacity: Int
)