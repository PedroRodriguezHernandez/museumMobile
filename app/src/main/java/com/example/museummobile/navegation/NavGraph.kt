package com.example.museummobile.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.museummobile.MainScreen
import com.example.museummobile.ui.features.cart.Cart
import com.example.museummobile.ui.features.exhibition.Exhibition
import com.example.museummobile.ui.features.home.Home
import com.example.museummobile.ui.features.login.Login
import com.example.museummobile.ui.features.myTickets.MyTickets
import com.example.museummobile.ui.features.scanQR.Scan
import com.example.museummobile.ui.features.shop.Shop
import com.example.museummobile.ui.features.signup.Signup


@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(Screen.Login.route){ Login(navController) }
        composable(Screen.SignUp.route){ Signup(navController) }
        composable(Screen.Home.route){ MainScreen(
            { Home(navController) },
            title = "Home",
            navController)}
        composable(Screen.MyTickets.route){ MainScreen(
            { Exhibition(navController) },
            title = "My Tickets",
            navController) }
        composable(Screen.Shop.route){ MainScreen(
            { MyTickets(navController) },
            title = "Shop",
            navController)}
        composable(Screen.Scan.route){ MainScreen(
            { Scan(navController) },
            title = "Scan",
            navController) }
        composable(Screen.Exhibition.route){ MainScreen(
            { Shop(navController) },
            title = "Exhibitions",
            navController) }
        composable(Screen.Cart.route){ MainScreen(
            { Cart(navController) },
            title = "Cart",
            navController) }
    }
}