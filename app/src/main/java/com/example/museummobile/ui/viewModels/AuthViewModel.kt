package com.example.museummobile.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.domain.AuthRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.example.museummobile.core.model.AuthModel
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.collectLatest


class AuthViewModel (
    private val authRepository: AuthRepository
): ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)


    private var _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> get() = _isLoggedIn

    var authModel by mutableStateOf<AuthModel?>(null)
        private set


    init {
        observeSessionStatus()
    }

    private fun observeSessionStatus() {
        viewModelScope.launch {
            supabase.auth.sessionStatus.collectLatest { status ->
                _isLoggedIn.value = status is SessionStatus.Authenticated
                val auth = supabase.auth.currentSessionOrNull()
                authModel = auth?.let {
                    AuthModel(
                        uid = it.user?.id ?: "",
                        userName = it.user?.email ?: ""
                    )
                }
            }
        }
    }

    fun checkLoggedIn() {
        viewModelScope.launch {
            isLoading = true
            _isLoggedIn.value = authRepository.isLoggedIn()
        }
        isLoading = false
    }


    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            val result = authRepository.login(email, password)
            isLoading = false
            result.fold(
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                    errorMessage = "fail_login"
                }
            )
        }
    }

    fun logout(onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                authRepository.logout()
                isLoading = false
                errorMessage = null
                onComplete?.invoke()
            } catch (e: Exception) {
                errorMessage = e.message
                isLoading = false
            }
        }
    }

    fun signUp(email: String, password: String, user: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            val result = authRepository.signUp(email, password,user)
            isLoading = false
            result.fold(
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                    errorMessage = "fail_signup"
                }
            )
        }
    }



}
