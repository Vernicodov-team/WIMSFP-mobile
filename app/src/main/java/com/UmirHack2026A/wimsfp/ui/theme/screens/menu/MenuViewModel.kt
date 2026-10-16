package com.UmirHack2026A.wimsfp.ui.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.UmirHack2026A.wimsfp.data.model.Dish
import com.UmirHack2026A.wimsfp.data.model.Ingredient
import kotlinx.coroutines.launch
import com.UmirHack2026A.wimsfp.data.repository.RestaurantRepository


class MenuViewModel(): ViewModel() {
    private val _dishes = MutableStateFlow<List<Dish>>(emptyList())
    val dishes: StateFlow<List<Dish>> = _dishes.asStateFlow()
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadDishes()
    }
    private fun loadDishes() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val dishesFromApi = RestaurantRepository.getDishes()
                _dishes.value = dishesFromApi
            } catch (e: Exception) {
                _dishes.value = getMockDishes()
            } finally {
                _isLoading.value = false
            }
        }
    }
    private fun getMockDishes(): List<Dish> {
        return listOf(
            Dish(
                id = "1",
                name = "Борщ",
                description = "Традиционный борщ со сметаной и пампушками",
                imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=400",
                price = 350,
                available = true,
                category = "Супы",
                ingredients = listOf(
                    Ingredient("Свёкла", 150, "г"),
                    Ingredient("Капуста", 100, "г")
                )
            )
        )
    }
}