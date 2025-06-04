package com.example.museummobile.core.domain

import com.example.museummobile.core.model.Exhibition

interface ExhibitionRepository {
    suspend fun getExhibitionById(id: String): Exhibition?
}