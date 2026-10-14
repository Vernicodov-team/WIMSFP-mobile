package com.UmirHack2026A.wimsfp.ui.screens.deliveries

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeliveriesViewModel : ViewModel() {
    private val _deliveryNumber = MutableStateFlow("")
    val deliveryNumber: StateFlow<String> = _deliveryNumber.asStateFlow()
    private val _productName = MutableStateFlow("")
    val productName: StateFlow<String> = _productName.asStateFlow()
    private val _quantity = MutableStateFlow("")
    val quantity: StateFlow<String> = _quantity.asStateFlow()
    private val _expirationDate = MutableStateFlow("")
    val expirationDate: StateFlow<String> = _expirationDate.asStateFlow()
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun onDeliveryNumberChange(value: String) { _deliveryNumber.value = value }
    fun onProductNameChange(value: String) { _productName.value = value }
    fun onQuantityChange(value: String) { _quantity.value = value }
    fun onExpirationDateChange(value: String) { _expirationDate.value = value }

    fun registerDelivery() {
        if (deliveryNumber.value.isBlank() || productName.value.isBlank() ||
            quantity.value.isBlank() || expirationDate.value.isBlank()) {
            _message.value = "Заполните все поля"
            return
        }
        // TODO: Отправка на бэкенд
        _message.value = "Поставка №${deliveryNumber.value} зарегистрирована!"
        _productName.value = ""
        _quantity.value = ""
        _expirationDate.value = ""
    }
}