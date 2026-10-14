package com.UmirHack2026A.wimsfp.ui.screens.deliveries

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
fun DeliveriesScreen(
    viewModel: DeliveriesViewModel = viewModel()
) {
    val deliveryNumber by viewModel.deliveryNumber.collectAsState()
    val productName by viewModel.productName.collectAsState()
    val quantity by viewModel.quantity.collectAsState()
    val expirationDate by viewModel.expirationDate.collectAsState()
    val message by viewModel.message.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Регистрация поставки",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        CustomTextField(
            value = deliveryNumber,
            onValueChange = viewModel::onDeliveryNumberChange,
            label = "Номер поставки"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = productName,
            onValueChange = viewModel::onProductNameChange,
            label = "Название продукта"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = quantity,
            onValueChange = viewModel::onQuantityChange,
            label = "Количество",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = expirationDate,
            onValueChange = viewModel::onExpirationDateChange,
            label = "Срок годности (ДД.ММ.ГГГГ)",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(24.dp))
        CustomButton(
            text = "Зарегистрировать поставку",
            onClick = viewModel::registerDelivery
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