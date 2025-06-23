package com.example.museummobile.ui.features.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.domain.MuseumRepository
import com.example.museummobile.core.model.Museum
import kotlinx.coroutines.launch

class MuseumViewModel(
    private val museumRepository: MuseumRepository
) : ViewModel() {

    var museums = mutableStateListOf<Museum>()
        private set

    var errorMessage by mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)
        private set

    fun loadMuseums(){
        viewModelScope.launch (){
            isLoading = true
            errorMessage = null
            this@MuseumViewModel.errorMessage = null
            try {
                val result = museumRepository.getMuseum()
                museums.clear()
                museums.addAll(result)
            } catch ( e : Exception ){
                this@MuseumViewModel.errorMessage
            } finally {
                isLoading =  false
            }
        }
    }

}