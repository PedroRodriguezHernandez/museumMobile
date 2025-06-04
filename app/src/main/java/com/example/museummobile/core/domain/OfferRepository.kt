package com.example.museummobile.core.domain

import com.example.museummobile.core.model.Offer

interface OfferRepository {
    suspend fun getOfferById(id: String): Offer?
    suspend fun getOffers(): List<Offer>
}