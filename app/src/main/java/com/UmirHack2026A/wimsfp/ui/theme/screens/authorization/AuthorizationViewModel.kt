package com.UmirHack2026A.wimsfp.ui.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UmirHack2026A.wimsfp.data.model.LoginRequest
import com.UmirHack2026A.wimsfp.data.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthorizationViewModel(private val repository: RestaurantRepository): ViewModel() {
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

    private val _userRole = MutableStateFlow<String?>(null)
    val userRole: StateFlow<String?> = _userRole.asStateFlow()
    fun onLoginChange(value: String) {
        _login.value = value
        _errorMessage.value = null
    }
    fun onPasswordChange(value: String) {
        _password.value = value
        _errorMessage.value = null
    }
    fun authorize(onSuccess: (role: String) -> Unit) {
        if (login.value.isBlank() || password.value.isBlank()) {
            _errorMessage.value = "Заполните все поля"
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val loginRequest = LoginRequest(
                    name = login.value,
                    password = password.value
                )
                val response = repository.login(loginRequest)
                _userRole.value = response.role
                onSuccess(response.role)

                kotlinx.coroutines.delay(1000)
                _isAuthorized.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка авторизации: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}