package com.example.museummobile.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.domain.ExhibitionRepository
import com.example.museummobile.core.model.Exhibition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExhibitionViewModel(
    private val repository: ExhibitionRepository
) : ViewModel() {

    private val _exhibitionState = MutableStateFlow<Exhibition?>(null)
    val exhibitionState: StateFlow<Exhibition?> = _exhibitionState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchExhibitionById(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val exhibition = repository.getExhibitionById(id)
                _exhibitionState.value = exhibition
            } catch (e: Exception) {
              _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
