package com.UmirHack2026A.wimsfp.ui.screens.orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.UmirHack2026A.wimsfp.ui.theme.components.CustomButton
import com.UmirHack2026A.wimsfp.ui.theme.components.CustomTextField

@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = viewModel()
) {
    val tableNumber by viewModel.tableNumber.collectAsState()
    val selectedDishes by viewModel.selectedDishes.collectAsState()
    val message by viewModel.message.collectAsState()
    val availableDishes = listOf("Борщ", "Цезарь", "Паста", "Стейк")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Оформление заказа",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
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