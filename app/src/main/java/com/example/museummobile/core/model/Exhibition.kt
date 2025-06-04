package com.example.museummobile.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Exhibition(
    val id: Int? = null,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val QRUrl: String? = null,
    val enable: Boolean,
    val views: Int? = null
)