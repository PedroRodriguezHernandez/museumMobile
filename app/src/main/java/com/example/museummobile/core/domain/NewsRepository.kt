package com.example.museummobile.core.domain

import com.example.museummobile.core.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews():List<News>;
    suspend fun getNewsById(id:String):News;

}