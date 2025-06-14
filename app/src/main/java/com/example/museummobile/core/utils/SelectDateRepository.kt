package com.example.museummobile.core.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.museummobile.ui.features.viewModels.SelectDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class SelectDateRepository(private val context: Context) {

    private val KEY_SELECT_DATES = stringPreferencesKey("select_dates")

    fun getSelectDatesFlow(): Flow<List<SelectDate>> = context.dataStore.data.map { preferences ->
        val jsonString = preferences[KEY_SELECT_DATES] ?: "[]"
        try {
            Json.decodeFromString(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun saveSelectDates(dates: List<SelectDate>) {
        val jsonString = Json.encodeToString(dates)
        context.dataStore.edit { preferences ->
            preferences[KEY_SELECT_DATES] = jsonString
        }
    }
}