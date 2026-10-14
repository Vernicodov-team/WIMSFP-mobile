@file:OptIn(ExperimentalMaterial3Api::class)

package com.UmirHack2026A.wimsfp.ui.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.UmirHack2026A.wimsfp.ui.theme.components.ProductCard

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = viewModel()
) {
    val dishes by viewModel.dishes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Меню ресторана") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(dishes) { dish ->
                    ProductCard(
                        title = dish.name,
                        description = dish.description,
                        imageUrl = dish.imageUrl,
                        price = "${dish.price} ₽",
                        onClick = { /* TODO: переход к деталям или заказу */ }
                    )
                    if (!dish.isAvailable) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
                            )
                        ) {
                            Text(
                                text = "⛔ Временно недоступно",
                                modifier = Modifier.padding(8.dp),
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}