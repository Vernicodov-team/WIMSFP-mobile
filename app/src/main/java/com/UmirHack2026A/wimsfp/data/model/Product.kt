package com.UmirHack2026A.wimsfp.data.model

data class Product(
    val name: String,
    val measureUnit: String
) {
    val productUnits = listOf(
        "Kilogram",
        "Liter",
        "Item")
}