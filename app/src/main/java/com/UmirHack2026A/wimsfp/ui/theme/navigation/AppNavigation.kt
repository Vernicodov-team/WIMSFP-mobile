package com.UmirHack2026A.wimsfp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("authorization") {

        }

        composable("menu") {

        }

        composable("deliveries") {

        }

        composable("orders") {

        }

    }
}