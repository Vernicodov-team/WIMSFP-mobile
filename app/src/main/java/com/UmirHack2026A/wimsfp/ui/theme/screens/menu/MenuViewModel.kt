package com.UmirHack2026A.wimsfp.ui.screens.menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.UmirHack2026A.wimsfp.data.model.Dish
import com.UmirHack2026A.wimsfp.data.model.Ingredient


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
        _dishes.value = listOf(
            Dish(
                id = "1",
                name = "Борщ украинский",
                description = "Традиционный борщ со сметаной и пампушками",
                imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=400",
                price = 350,
                available = true,
                category = "Супы",
                ingredients = listOf(
                    Ingredient("Свёкла", 150, "г"),
                    Ingredient("Капуста", 100, "г"),
                    Ingredient("Картофель", 80, "г"),
                    Ingredient("Морковь", 50, "г"),
                    Ingredient("Лук", 30, "г"),
                    Ingredient("Томатная паста", 20, "г"),
                    Ingredient("Сметана", 30, "г"),
                    Ingredient("Пампушки", 2, "шт")
                )
            ),
            Dish(
                id = "2",
                name = "Салат Цезарь с курицей",
                description = "Классический салат с куриным филе, пармезаном и соусом Цезарь",
                imageUrl = "https://images.unsplash.com/photo-1546793665-c74683f339c1?w=400",
                price = 420,
                available = true,
                category = "Салаты",
                ingredients = listOf(
                    Ingredient("Куриное филе", 120, "г"),
                    Ingredient("Салат Романо", 80, "г"),
                    Ingredient("Пармезан", 30, "г"),
                    Ingredient("Сухарики", 40, "г"),
                    Ingredient("Соус Цезарь", 50, "мл"),
                    Ingredient("Помидоры черри", 60, "г")
                )
            ),
            Dish(
                id = "3",
                name = "Стейк Рибай",
                description = "Мраморная говядина medium rare с овощами гриль",
                imageUrl = "https://images.unsplash.com/photo-1600891964092-4316c288032e?w=400",
                price = 1290,
                available = false,  // в стоп-листе — нет говядины
                category = "Горячее",
                ingredients = listOf(
                    Ingredient("Говядина Рибай", 300, "г"),
                    Ingredient("Оливковое масло", 15, "мл"),
                    Ingredient("Розмарин", 5, "г"),
                    Ingredient("Чеснок", 10, "г"),
                    Ingredient("Овощи гриль", 150, "г")
                )
            ),
            Dish(
                id = "4",
                name = "Паста Карбонара",
                description = "Спагетти с беконом, яйцом и пармезаном",
                imageUrl = "https://images.unsplash.com/photo-1612874742237-6526221588e3?w=400",
                price = 480,
                available = true,
                category = "Горячее",
                ingredients = listOf(
                    Ingredient("Спагетти", 100, "г"),
                    Ingredient("Бекон", 80, "г"),
                    Ingredient("Яйцо", 2, "шт"),
                    Ingredient("Пармезан", 40, "г"),
                    Ingredient("Чёрный перец", 2, "г")
                )
            ),
            Dish(
                id = "5",
                name = "Тирамису",
                description = "Итальянский десерт с маскарпоне и кофе",
                imageUrl = "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=400",
                price = 320,
                available = true,
                category = "Десерты",
                ingredients = listOf(
                    Ingredient("Маскарпоне", 150, "г"),
                    Ingredient("Савоярди", 80, "г"),
                    Ingredient("Кофе эспрессо", 100, "мл"),
                    Ingredient("Яйцо", 1, "шт"),
                    Ingredient("Сахар", 30, "г"),
                    Ingredient("Какао", 10, "г")
                )
            ),
            Dish(
                id = "6",
                name = "Том Ям с креветками",
                description = "Острый тайский суп с креветками и кокосовым молоком",
                imageUrl = "https://images.unsplash.com/photo-1548943487-a2e4e43b4853?w=400",
                price = 590,
                available = true,
                category = "Супы",
                ingredients = listOf(
                    Ingredient("Креветки", 120, "г"),
                    Ingredient("Кокосовое молоко", 200, "мл"),
                    Ingredient("Бульон", 300, "мл"),
                    Ingredient("Лемонграсс", 20, "г"),
                    Ingredient("Имбирь", 15, "г"),
                    Ingredient( "Чили", 5, "г"),
                    Ingredient("Шампиньоны", 60, "г")
            )
        ),
        Dish(
            id = "7",
            name = "Пицца Маргарита",
            description = "Классическая итальянская пицца с томатами и моцареллой",
            imageUrl = "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400",
            price = 520,
            available = false,  // нет моцареллы
            category = "Горячее",
            ingredients = listOf(
                Ingredient("Тесто", 250, "г"),
                Ingredient("Моцарелла", 150, "г"),
                Ingredient("Томатный соус", 80, "г"),
                Ingredient("Базилик", 5, "г"),
                Ingredient("Оливковое масло", 10, "мл")
            )
        ),
        Dish(
            id = "8",
            name = "Чизкейк Нью-Йорк",
            description = "Классический американский чизкейк с ягодным соусом",
            imageUrl = "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=400",
            price = 380,
            available = true,
            category = "Десерты",
            ingredients = listOf(
                Ingredient("Сливочный сыр", 200, "г"),
                Ingredient("Печенье", 80, "г"),
                Ingredient("Сливки", 100, "мл"),
                Ingredient("Сахар", 50, "г"),
                Ingredient("Яйцо", 2, "шт"),
                Ingredient("Ягодный соус", 40, "мл")
            )
        ),
        Dish(
            id = "9",
            name = "Лосось на гриле",
            description = "Филе лосося с лимонным соусом и спаржей",
            imageUrl = "https://images.unsplash.com/photo-1467003909585-2f8a72700288?w=400",
            price = 890,
            available = true,
            category = "Горячее",
            ingredients = listOf(
                Ingredient("Филе лосося", 200, "г"),
                Ingredient("Спаржа", 100, "г"),
                Ingredient("Лимон", 30, "г"),
                Ingredient("Оливковое масло", 15, "мл"),
                Ingredient("Укроп", 5, "г")
            )
        ),
        Dish(
            id = "10",
            name = "Мохито безалкогольный",
            description = "Освежающий напиток с мятой, лаймом и газированной водой",
            imageUrl = "https://images.unsplash.com/photo-1551539807-4e3e7e0d2e5e?w=400",
            price = 250,
            available = true,
            category = "Напитки",
            ingredients = listOf(
                Ingredient("Мята", 10, "г"),
                Ingredient("Лайм", 60, "г"),
                Ingredient("Сахар", 20, "г"),
                Ingredient("Газированная вода", 200, "мл"),
                Ingredient("Лёд", 150, "г")
                )
            )
        )
        _isLoading.value = false
    }
}