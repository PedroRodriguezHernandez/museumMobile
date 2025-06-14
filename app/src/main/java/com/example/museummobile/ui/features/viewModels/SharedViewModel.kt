package com.example.museummobile.ui.features.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.utils.SelectDateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class SelectDate (
    val id: Int,
    val date: LocalDate
)

class SharedViewModel(private val repository: SelectDateRepository): ViewModel() {

    private val _selectDates = MutableStateFlow<List<SelectDate>>(emptyList())
    val selectDates: StateFlow<List<SelectDate>> = _selectDates

    init {
        viewModelScope.launch {
            repository.getSelectDatesFlow().collectLatest {
                _selectDates.value = it
            }
        }
    }

    fun addDates(dates: List<SelectDate>) {
        val updated = _selectDates.value.toMutableList()
        updated.addAll(dates)
        saveAndUpdate(updated)
    }

    fun removeDate(date: SelectDate) {
        val updated = _selectDates.value.toMutableList()
        updated.remove(date)
        saveAndUpdate(updated)
    }

    fun clearDates() {
        saveAndUpdate(emptyList())
    }

    private fun saveAndUpdate(updatedList: List<SelectDate>) {
        _selectDates.value = updatedList
        viewModelScope.launch {
            repository.saveSelectDates(updatedList)
        }
    }
}
