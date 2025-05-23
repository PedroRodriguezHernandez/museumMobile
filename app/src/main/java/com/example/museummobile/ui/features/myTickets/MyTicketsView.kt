package com.example.museummobile.ui.features.myTickets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R

@Composable
fun MyTickets(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.broken_withe))
    ){
        Icon(
            imageVector = Icons.Default.DinnerDining,
            contentDescription = "Pitosaurio"
        )
    }
}

@Preview
@Composable
fun PreCart(){
    val navController =  rememberNavController()
    MyTickets(navController)
}