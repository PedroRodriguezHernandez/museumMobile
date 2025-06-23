package com.example.museummobile.navegation

sealed class Screen(val route : String){
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Home: Screen("home")
    object Cart: Screen("cart")
    object Exhibition: Screen("exhibition/{id}"){
        fun createRoute(id:String) = "exhibition/$id"
    }
    object MyTickets: Screen("my-tickets")
    object Scan: Screen("scan")
    object Shop: Screen("shop")
    object Confirm: Screen("confirm_email")
    object News: Screen("news/{id}"){
        fun createRouter(id:String) = "news/$id"
    }
}