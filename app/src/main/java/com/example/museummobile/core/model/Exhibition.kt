package com.example.museummobile.core.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Exhibition(
    val id: String? = null,
    val title: String,
    val description: String,
    val image_url: String? = null,
    val views: Int? = null,
    val tags: Map<String, JsonElement>? = null
)
