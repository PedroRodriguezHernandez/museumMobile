package com.example.museummobile.core.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthModel(
    val uid: String,
    val userName: String
)
