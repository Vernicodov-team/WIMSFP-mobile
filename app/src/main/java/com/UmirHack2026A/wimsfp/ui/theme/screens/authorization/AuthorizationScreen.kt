package com.UmirHack2026A.wimsfp.ui.screens.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.UmirHack2026A.wimsfp.ui.theme.components.CustomButton
import com.UmirHack2026A.wimsfp.ui.theme.components.CustomTextField

@Composable
fun AuthorizationScreen(
    onLoginSuccess: (role: String) -> Unit,
    viewModel: AuthorizationViewModel = viewModel()
) {
    val login by viewModel.login.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "WIMSFP",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Вход для официанта",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(48.dp))
        CustomTextField(
            value = login,
            onValueChange = viewModel::onLoginChange,
            label = "Логин",
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = password,
            onValueChange = viewModel::onPasswordChange,
            label = "Пароль",
            isPassword = true,
            keyboardType = KeyboardType.Password
        )
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        CustomButton(
            text = if (isLoading) "Вход..." else "Войти",
            onClick = { viewModel.authorize(onLoginSuccess) },
            enabled = !isLoading
        )
    }
}