package com.example.museummobile.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.domain.UserRepository
import com.example.museummobile.core.model.User
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    var user = mutableStateOf<User?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadUserById(id: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val result = userRepository.getUserById(id)
                user.value = result
            } catch (e: Exception) {
                errorMessage = e.message ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    fun updateUser(userToUpdate: User, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val success = userRepository.updateUser(userToUpdate)
                if(success) {
                    user.value = userToUpdate
                } else {
                    errorMessage = "No se pudo actualizar el usuario"
                }
                onResult(success)
            } catch (e: Exception) {
                errorMessage = e.message ?: "Error desconocido"
                onResult(false)
            } finally {
                isLoading = false
            }
        }
    }
}
