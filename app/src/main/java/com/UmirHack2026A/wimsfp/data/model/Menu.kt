package com.UmirHack2026A.wimsfp.data.model

data class Menu(
    val name: String,
    val main: List<DishDescription>
)

data class DishDescription(
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String = ""
)