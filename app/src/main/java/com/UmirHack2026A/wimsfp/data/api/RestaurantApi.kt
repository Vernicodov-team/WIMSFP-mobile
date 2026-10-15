package com.UmirHack2026A.wimsfp.data.api

import com.UmirHack2026A.wimsfp.data.model.Dish
import com.UmirHack2026A.wimsfp.data.model.Order
import com.UmirHack2026A.wimsfp.data.model.Product
import retrofit2.http.*
import com.UmirHack2026A.wimsfp.data.model.Delivery

interface RestaurantApi {

    @GET("api/v1/dish")
    suspend fun getDish(): List<Dish>

    @POST("api/v1/dish/AddDish")
    suspend fun addDish(@Body dish: Dish): Dish

    @POST("api/v1/dish/UpdateDish")
    suspend fun updateDish(@Body dish: Dish): Dish




    @POST("api/v1/order/makeOrder")
    suspend fun makeOrder(@Body order: Order): Order

    @GET("api/v1/order/getOrderList")
    suspend fun getOrderList(): List<Order>




    @GET("api/v1/Products")
    suspend fun getProducts(): List<Product>

    @POST("api/v1/Products/AddProduct")
    suspend fun addProducts(@Body product: Product): Product



    @POST("api/v1/supply/AddDelivery")
    suspend fun addDelivery(@Body delivery: Delivery): Delivery

    @GET("api/v1/supply/GetDeliveries")
    suspend fun getDeliveries(): List<Delivery>
}