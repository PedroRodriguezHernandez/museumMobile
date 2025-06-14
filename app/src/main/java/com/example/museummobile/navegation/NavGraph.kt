package com.example.museummobile.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.museummobile.MainScreen
import com.example.museummobile.core.supabase.AuthSupabase
import com.example.museummobile.ui.features.cart.Cart
import com.example.museummobile.ui.features.confirm_email.ConfirmEmail
import com.example.museummobile.ui.features.exhibition.Exhibition
import com.example.museummobile.ui.features.home.Home
import com.example.museummobile.ui.features.login.Login
import com.example.museummobile.ui.features.myTickets.MyTickets
import com.example.museummobile.ui.features.scanQR.Scan
import com.example.museummobile.ui.features.shop.Shop
import com.example.museummobile.ui.features.signup.Signup
import com.example.museummobile.ui.features.viewModels.AuthViewModel
import com.example.museummobile.ui.features.viewModels.SharedViewModel
import com.example.museummobile.ui.features.viewModels.SharedViewModelFactory


@Composable
fun AppNavGraph(navController: NavHostController){

    val context = LocalContext.current
    val authViewModel = remember { AuthViewModel(AuthSupabase()) }

    val sharedViewModelFactory = remember { SharedViewModelFactory(context) }
    val sharedViewModel: SharedViewModel = viewModel(factory = sharedViewModelFactory)

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(Screen.Login.route){ Login(navController) }
        composable(Screen.SignUp.route){ Signup(navController) }
        composable(Screen.Confirm.route){ ConfirmEmail(navController) }
        composable(Screen.Home.route){ MainScreen(
            { Home(navController) },
            title = "Home",
            navController,
            {
                authViewModel.logout(){
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            })}
        composable(Screen.MyTickets.route){ MainScreen(
            { MyTickets(navController) },
            title = "My Tickets",
            navController,
            {
                authViewModel.logout(){
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }) }
        composable(Screen.Shop.route){ MainScreen(
            { Shop(navController,sharedViewModel) },
            title = "Shop",
            navController,
            {
                authViewModel.logout(){
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            })}
        composable(Screen.Scan.route){ MainScreen(
            { Scan(navController) },
            title = "Scan",
            navController,
            {
                authViewModel.logout(){
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }) }
        composable(route = Screen.Exhibition.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            Exhibition(navController, id)
        }
        composable(Screen.Cart.route){ Cart(navController,sharedViewModel)}
    }
}