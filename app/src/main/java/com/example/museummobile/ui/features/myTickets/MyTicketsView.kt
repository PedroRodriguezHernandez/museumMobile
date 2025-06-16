package com.example.museummobile.ui.features.myTickets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.core.supabase.AuthSupabase
import com.example.museummobile.core.supabase.TicketsSupabase
import com.example.museummobile.core.supabase.UserSupabase
import com.example.museummobile.ui.features.viewModels.AuthViewModel
import com.example.museummobile.ui.features.viewModels.TicketsViewModel
import com.example.museummobile.ui.features.viewModels.UserViewModel

@Composable
fun MyTickets(navController: NavController) {

    val userViewModel = remember { UserViewModel(UserSupabase()) }
    val authViewModel = remember { AuthViewModel(AuthSupabase()) }
    val ticketsViewModel = remember { TicketsViewModel(TicketsSupabase()) }

    val user = userViewModel.user.value
    val authModel = authViewModel.authModel
    val tickets = ticketsViewModel.tickets

    LaunchedEffect(authModel?.uid) {
        authModel?.uid?.let { uid ->
            userViewModel.loadUserById(uid)
        }
    }
    LaunchedEffect(user) {
        if (user != null) {
            user.my_tickets?.let { ticketsViewModel.loadTickets(it) }
        }
    }

   if(userViewModel.isLoading || ticketsViewModel.isLoading){
       Box(modifier = Modifier
           .fillMaxSize(),
           contentAlignment = Alignment.Center
       ) {
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
           ) {
               CircularProgressIndicator()
           }
       }
   }else{
       LazyColumn(
           modifier = Modifier
               .fillMaxSize()
               .background(color = colorResource(R.color.white)),
           verticalArrangement = Arrangement.spacedBy(8.dp)
       ) {
           items(tickets.filterNotNull().sortedBy { it.date_for }) { ticket ->
               Ticket(ticket)
           }
       }
   }
}




@Preview
@Composable
fun PreCart(){
    val navController =  rememberNavController()
    MyTickets(navController)
}