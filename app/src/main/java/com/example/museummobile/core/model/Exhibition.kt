package com.example.museummobile.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Exhibition(
    val id: String? = null,
    val title: String,
    val description: String,
    val image_url: String? = null,
    val views: Int? = null
)
