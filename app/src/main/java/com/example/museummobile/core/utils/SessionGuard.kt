package com.example.museummobile.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.museummobile.navegation.Screen
import com.example.museummobile.ui.viewModels.AuthViewModel

@Composable
fun SessionGuard(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val isLoggedIn by authViewModel.isLoggedIn
    var checked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        authViewModel.checkLoggedIn()
        checked = true
    }

    LaunchedEffect(checked, isLoggedIn) {
        if (checked) {
            if (isLoggedIn) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(0) { inclusive = true }
                }
            } else {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }
}
