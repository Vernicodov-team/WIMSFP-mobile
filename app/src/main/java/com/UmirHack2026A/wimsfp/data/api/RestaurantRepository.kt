package com.UmirHack2026A.wimsfp.data.repository

import com.UmirHack2026A.wimsfp.data.api.ApiClient
import com.UmirHack2026A.wimsfp.data.model.*
import android.content.Context
import android.content.SharedPreferences

class RestaurantRepository(context: Context) {
    private val api = ApiClient.apiService
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveAuthData(token: String, role: String, name: String) {
        prefs.edit()
            .putString("auth_token", token)
            .putString("user_role", role)
            .putString("user_name", name)
            .apply()
    }

    fun getToken(): String? = prefs.getString("auth_token", null)

    fun getUserRole(): String? = prefs.getString("user_role", null)

    fun logout() {
        prefs.edit().clear().apply()
    }

    suspend fun login(login: LoginRequest): LoginResponse {
        val response = api.login(login)
        saveAuthData(response.token, response.role, response.name)
        return response
    }

    fun isAuthorized(): Boolean = getToken() != null

    suspend fun getDishes(): List<Dish> = api.getDish()
    suspend fun addDish(dish: Dish) = api.addDish(dish)
    suspend fun updateDish(dish: Dish) = api.updateDish(dish)

    suspend fun makeOrder(order: Order): Order = api.makeOrder(order)
    suspend fun getOrderList(): List<Order> = api.getOrderList()

    suspend fun getProducts(): List<Product> = api.getProducts()
    suspend fun addProduct(product: Product) = api.addProduct(product)

    suspend fun addDelivery(delivery: Delivery): Any = api.addDelivery(delivery)
    suspend fun getDeliveries(): List<Delivery> = api.getDeliveries()
}