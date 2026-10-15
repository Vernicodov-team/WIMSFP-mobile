package com.UmirHack2026A.wimsfp.ui.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.UmirHack2026A.wimsfp.data.repository.RestaurantRepository

class AuthorizationViewModelFactory(
    private val repository: RestaurantRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthorizationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthorizationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}