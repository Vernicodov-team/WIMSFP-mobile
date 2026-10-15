package com.UmirHack2026A.wimsfp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.UmirHack2026A.wimsfp.ui.screens.*
import com.UmirHack2026A.wimsfp.ui.screens.authorization.AuthorizationScreen
import com.UmirHack2026A.wimsfp.ui.screens.deliveries.DeliveriesScreen
import com.UmirHack2026A.wimsfp.ui.screens.menu.MenuScreen
import com.UmirHack2026A.wimsfp.ui.screens.orders.OrdersScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    var userRole by remember { mutableStateOf<String?>(null) }

    NavHost(navController = navController, startDestination = "authorization") {

        composable("authorization") {
            AuthorizationScreen(
                onLoginSuccess = { role ->
                    userRole = role
                    when (role.lowercase()) {
                        "waiter", "официант" -> {
                            navController.navigate("menu") {
                                popUpTo("authorization") { inclusive = true }
                            }
                        }
                        "admin", "администратор" -> {
                            navController.navigate("deliveries") {
                                popUpTo("authorization") { inclusive = true }
                            }
                        }
                        "manager", "менеджер" -> {
                            navController.navigate("orders") {
                                popUpTo("authorization") { inclusive = true }
                            }
                        }
                        else -> {
                            navController.navigate("menu") {
                                popUpTo("authorization") { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        composable("menu") {
            MenuScreen(
                onNavigateToOrders = {
                    navController.navigate("orders")
                }
            )
        }

        composable("orders") {
            OrdersScreen(
                onNavigateToMenu = {
                    navController.navigate("menu")
                }
            )
        }

        composable("deliveries") {
            DeliveriesScreen()
        }
    }
}