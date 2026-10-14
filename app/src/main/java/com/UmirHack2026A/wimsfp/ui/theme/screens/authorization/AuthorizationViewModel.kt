package com.UmirHack2026A.wimsfp.ui.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthorizationViewModel : ViewModel() {
    private val _login = MutableStateFlow("")
    val login: StateFlow<String> = _login.asStateFlow()
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized: StateFlow<Boolean> = _isAuthorized.asStateFlow()
    fun onLoginChange(value: String) {
        _login.value = value
        _errorMessage.value = null
    }
    fun onPasswordChange(value: String) {
        _password.value = value
        _errorMessage.value = null
    }
    fun authorize(onSuccess: () -> Unit) {
        if (login.value.isBlank() || password.value.isBlank()) {
            _errorMessage.value = "Заполните все поля"
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: запрос к бэкенду

                kotlinx.coroutines.delay(1000)
                _isAuthorized.value = true
                onSuccess()
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка авторизации: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}