package com.UmirHack2026A.wimsfp.ui.screens.orders

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrdersViewModel : ViewModel() {
    private val _tableNumber = MutableStateFlow("")
    val tableNumber: StateFlow<String> = _tableNumber.asStateFlow()
    private val _selectedDishes = MutableStateFlow<List<String>>(emptyList())
    val selectedDishes: StateFlow<List<String>> = _selectedDishes.asStateFlow()
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun onTableNumberChange(value: String) { _tableNumber.value = value }

    fun toggleDish(dishName: String) {
        _selectedDishes.value = if (dishName in _selectedDishes.value) {
            _selectedDishes.value - dishName
        } else {
            _selectedDishes.value + dishName
        }
    }

    fun createOrder() {
        if (tableNumber.value.isBlank()) {
            _message.value = "Укажите номер стола"
            return
        }
        if (selectedDishes.value.isEmpty()) {
            _message.value = "Выберите хотя бы одно блюдо"
            return
        }
        // TODO: Отправка заказа на бэкенд
        _message.value = "Заказ для стола №${tableNumber.value} оформлен!"
        _selectedDishes.value = emptyList()
        _tableNumber.value = ""
    }
}