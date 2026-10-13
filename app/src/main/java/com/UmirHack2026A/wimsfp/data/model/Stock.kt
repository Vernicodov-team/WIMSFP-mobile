package com.UmirHack2026A.wimsfp.data.model

data class Stock(
    val name: String,
    val sumAmount: Int,
    val firstExpired: String,
    val lastDelivery: String,
    val stocks: List<StockProduct>
)

data class StockProduct(
    val productType: String,
    val amount: Int,
    val dateExpired: String,
    val dateReceived: String
)