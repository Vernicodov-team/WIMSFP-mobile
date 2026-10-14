package com.UmirHack2026A.wimsfp.ui.screens.menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Dish(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String?,
    val price: Int,
    val isAvailable: Boolean  // есть ли в стоп-листе
)

class MenuViewModel : ViewModel() {
    private val _dishes = MutableStateFlow<List<Dish>>(emptyList())
    val dishes: StateFlow<List<Dish>> = _dishes.asStateFlow()
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadDishes()
    }
    private fun loadDishes() {
        // TODO: Запрос с бэкенда
        _dishes.value = listOf()
        _isLoading.value = false
    }
}