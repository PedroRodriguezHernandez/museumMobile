package com.example.museummobile.core.domain

import com.example.museummobile.core.model.Museum

interface MuseumRepository {
    suspend fun getMuseum(): List<Museum>
}