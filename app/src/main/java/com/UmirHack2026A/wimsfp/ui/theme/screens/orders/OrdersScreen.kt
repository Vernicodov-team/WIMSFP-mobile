@file:OptIn(ExperimentalMaterial3Api::class)

package com.UmirHack2026A.wimsfp.ui.screens.orders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.UmirHack2026A.wimsfp.ui.theme.components.CustomButton
import com.UmirHack2026A.wimsfp.ui.theme.components.CustomTextField

@Composable
fun OrdersScreen(
    onNavigateToMenu: () -> Unit,
    viewModel: OrdersViewModel = viewModel()
) {
    val tableNumber by viewModel.tableNumber.collectAsState()
    val selectedDishes by viewModel.selectedDishes.collectAsState()
    val message by viewModel.message.collectAsState()
    val availableDishes = listOf("Борщ", "Цезарь", "Паста", "Стейк")

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Оформление заказа") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFc85d18),
                titleContentColor = Color(0xFFFFFFFF),
            ),
            actions = {
                TextButton(
                    onClick = onNavigateToMenu,
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "Меню",
                        color = Color(0xFFFFFFFF),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
        ) {
            CustomTextField(
                value = tableNumber,
                onValueChange = viewModel::onTableNumberChange,
                label = "Номер стола",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Выберите блюда:",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            availableDishes.forEach { dish ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = dish in selectedDishes,
                        onCheckedChange = { viewModel.toggleDish(dish) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = dish,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            CustomButton(
                text = "Оформить заказ",
                onClick = viewModel::createOrder
            )

            if (message != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message!!,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}