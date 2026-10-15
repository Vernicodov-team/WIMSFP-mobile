package com.UmirHack2026A.wimsfp.data.model

data class Order(
    val created: String,
    val payment: Int,
    val status: Int,
    val customerName: String,
    val contactPhone: String,
    val format: Int,
    val comment: String,
    val dishes: List<OrderDishes>
)

data class OrderDishes(
    val dishName: String,
    val dishPrice: Int,
    val count: Int
)