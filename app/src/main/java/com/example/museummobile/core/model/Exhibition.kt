package com.example.museummobile.core.model

data class Exhibition(
    val id: String? = null,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val QRUrl: String? = null,
    val enable: Boolean,
    val views: Int? = null
)