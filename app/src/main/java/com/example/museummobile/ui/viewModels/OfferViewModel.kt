package com.example.museummobile.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.domain.OfferRepository
import com.example.museummobile.core.model.AuthModel
import com.example.museummobile.core.model.Offer
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OfferViewModel(
    private val offerRepository: OfferRepository
) : ViewModel() {

    var offers = mutableStateListOf<Offer?>()
        private set

    private val _offer = mutableStateOf<Offer?>(null)
    val offer get() = _offer.value

    var isLoading by mutableStateOf(false)
        private set

    var isLoaded by mutableStateOf(false)
        private set


    var errorMessage by mutableStateOf<String?>(null)


    fun loadOffers() {
        viewModelScope.launch {
            isLoading = true
            this@OfferViewModel.errorMessage = null
            try {
                val result = offerRepository.getOffers()
                offers.clear()
                offers.addAll(result)
                isLoaded = true
            } catch (e: Exception) {
                this@OfferViewModel.errorMessage = e.message
            }finally {
                isLoading = false
            }

        }
    }

    fun loadOfferById(id: String) {
        viewModelScope.launch {
            isLoading = true
            this@OfferViewModel.errorMessage = null
            try {
                val result = offerRepository.getOfferById(id)
                _offer.value = result
            } catch (e: Exception) {
                this@OfferViewModel.errorMessage = e.message
            }
            isLoading = false
        }
    }
}
