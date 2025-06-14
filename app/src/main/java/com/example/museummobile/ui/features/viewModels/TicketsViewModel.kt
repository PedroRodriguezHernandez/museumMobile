package com.example.museummobile.ui.features.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.domain.TicketsRepository
import com.example.museummobile.core.model.Tickets
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class TicketsViewModel(
    private val ticketsRepository: TicketsRepository
) : ViewModel() {
    var tickets = mutableStateListOf<Tickets?>()
        private set

    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
    var isCreated by mutableStateOf(false)
        private set

    fun loadTickets(tickets_id: List<String>){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                var result = ticketsRepository.getMyTickets(tickets_id)
                tickets.clear()
                tickets.addAll(result)
            }catch (e: Exception){
                errorMessage = e.message
            }finally {
                isLoading =  false
            }
        }
    }

    fun addTickets(date: LocalDate, offer_id: Int){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val result = ticketsRepository.addTickets(date,offer_id)
                Log.d("prueba", "addTickets: $result")
                isCreated =  true
            }catch (e: Exception){
                errorMessage = e.message
            }finally {
                isLoading =  false
            }
        }
    }
}