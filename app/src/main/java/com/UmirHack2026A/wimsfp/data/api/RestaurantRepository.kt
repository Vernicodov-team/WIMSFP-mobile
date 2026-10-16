package com.UmirHack2026A.wimsfp.data.repository

import com.UmirHack2026A.wimsfp.data.api.ApiClient
import com.UmirHack2026A.wimsfp.data.model.*

// object = синглтон, создаётся один раз и доступен везде
object RestaurantRepository {

    private val api = ApiClient.apiService

    // Переменная для хранения токена в памяти
    private var authToken: String? = null
    private var userRole: String? = null

    // === АВТОРИЗАЦИЯ ===
    suspend fun login(loginRequest: LoginRequest): String {
        val response = api.login(loginRequest)
        authToken = response
        return response
    }

    fun getToken(): String? = authToken
    fun getUserRole(): String? = userRole
    fun isAuthorized(): Boolean = authToken != null

    fun logout() {
        authToken = null
        userRole = null
    }

    // === МЕНЮ ===
    suspend fun getDishes(): List<Dish> = api.getDish()

    // === ЗАКАЗЫ ===
    suspend fun makeOrder(order: Order): Order = api.makeOrder(order)
    suspend fun getOrderList(): List<Order> = api.getOrderList()

    // === ПРОДУКТЫ ===
    suspend fun getProducts(): List<Product> = api.getProducts()
    suspend fun addProduct(product: Product): Product = api.addProduct(product)

    // === ПОСТАВКИ ===
    suspend fun addDelivery(delivery: Delivery): Delivery = api.addDelivery(delivery)
    suspend fun getDeliveries(): List<Delivery> = api.getDeliveries()
}