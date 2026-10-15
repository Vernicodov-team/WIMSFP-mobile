package com.UmirHack2026A.wimsfp.data.model

data class Delivery(
    val number: String,
    val dateReceived: String,
    val price: Int,
    val supplierName: String,
    val receivedProducts: List<DeliveryProduct>
)

data class DeliveryProduct(
    val productName: String,
    val expirationDate: String,
    val count: Int,
    val itemPrice: Int
)