package com.UmirHack2026A.wimsfp.ui.theme.navigation

import androidx.compose.runtime.Composable
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

    NavHost(navController = navController, startDestination = "authorization") {

        composable("authorization") {
            AuthorizationScreen(
                onLoginSuccess = {
                    navController.navigate("menu") {
                        popUpTo("authorization") {inclusive = true}
                    }
                }
            )
        }

        composable("menu") {
            MenuScreen()
        }

        composable("deliveries") {
            DeliveriesScreen()
        }

        composable("orders") {
            OrdersScreen()
        }
    }
}