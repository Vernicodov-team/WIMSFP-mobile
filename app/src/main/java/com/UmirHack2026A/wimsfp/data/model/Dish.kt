package com.UmirHack2026A.wimsfp.data.model

data class Dish(
    val name: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val price: Int,
    val available: Boolean,
    val ingredients: List<Ingredient>
)

data class Ingredient(
    val product: String,
    val count: Int
)