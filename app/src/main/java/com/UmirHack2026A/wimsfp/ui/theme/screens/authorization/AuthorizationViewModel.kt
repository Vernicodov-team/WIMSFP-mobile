package com.UmirHack2026A.wimsfp.ui.screens.authorization

import android.app.Application
import android.util.Base64
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.UmirHack2026A.wimsfp.data.model.LoginRequest
import com.UmirHack2026A.wimsfp.data.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class AuthorizationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RestaurantRepository(application)

    private val _login = MutableStateFlow("")
    val login: StateFlow<String> = _login.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

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
                    name = login.value.trim(),
                    password = password.value
                )
                val token = repository.login(loginRequest)
                val role = extractRoleFromJwt(token)
                repository.saveAuthToken(token)
                onSuccess(role)

            } catch (e: Exception) {
                _errorMessage.value = "Ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun extractRoleFromJwt(token: String): String {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return "waiter"

            val payload = parts[1]
            val decodedBytes = Base64.decode(payload, Base64.URL_SAFE)
            val json = String(decodedBytes, Charsets.UTF_8)

            val jsonObject = JSONObject(json)
            val roleKey = jsonObject.keys().asSequence().find { it.contains("role", ignoreCase = true) }

            if (roleKey != null) {
                jsonObject.getString(roleKey).lowercase()
            } else {
                "waiter"
            }
        } catch (e: Exception) {
            "waiter"
        }
    }
}