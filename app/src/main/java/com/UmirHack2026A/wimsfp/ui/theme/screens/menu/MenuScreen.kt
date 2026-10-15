@file:OptIn(ExperimentalMaterial3Api::class)

package com.UmirHack2026A.wimsfp.ui.screens.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.UmirHack2026A.wimsfp.ui.theme.components.ProductCard
import com.UmirHack2026A.wimsfp.data.model.Dish
import com.UmirHack2026A.wimsfp.ui.theme.components.CustomButton

@Composable
fun MenuScreen(
    onNavigateToOrders: () -> Unit,
    viewModel: MenuViewModel = viewModel()
) {
    val dishes by viewModel.dishes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var selectedDish by remember { mutableStateOf<Dish?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Меню ресторана") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            actions = {
                TextButton(
                    onClick = onNavigateToOrders,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "Заказы",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
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
                    if (dish.available) {
                        ProductCard(
                            title = dish.name,
                            description = dish.description,
                            imageUrl = dish.imageUrl,
                            price = "${dish.price} ₽",
                            available = true,
                            onClick = { selectedDish = dish }
                        )
                    }
                    if (!dish.available) {
                        ProductCard(
                            title = dish.name,
                            description = dish.description,
                            imageUrl = dish.imageUrl,
                            price = "${dish.price} ₽",
                            available = false,
                            onClick = { selectedDish = dish }
                        )
                    }
                }
            }
        }
    }
    if (selectedDish != null) {
        AlertDialog(
            onDismissRequest = { selectedDish = null },
            title = {
                Text(
                    text = selectedDish!!.name,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Column {
                    Text(
                        text = selectedDish!!.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column {
                        Text(
                            text = "Состав:",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        selectedDish!!.ingredients.forEach { ingredient ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = "• ",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    text = "${ingredient.product}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "${ingredient.count} ${ingredient.measure}",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Цена: ${selectedDish!!.price} ₽",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Категория: ${selectedDish!!.category}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (selectedDish!!.available) "Доступно" else "В стоп-листе",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedDish!!.available)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedDish = null }) {
                    Text("Закрыть")
                }
            }
        )
    }
}