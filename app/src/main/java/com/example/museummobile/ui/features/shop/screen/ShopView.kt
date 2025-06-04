package com.example.museummobile.ui.features.shop.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.museummobile.core.model.Offer
import com.example.museummobile.core.supabase.OfferSupabase
import com.example.museummobile.ui.components.ticket.Ticket
import com.example.museummobile.ui.features.viewModels.OfferViewModel
import kotlinx.datetime.LocalDate

@Composable
fun Shop(navController: NavController) {

    val offerViewModel = remember { OfferViewModel(OfferSupabase()) }

    val offers = offerViewModel.offers

    val isLoaded = offerViewModel.isLoaded

    LaunchedEffect(Unit) {
        offerViewModel.loadOffers()
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter
    ){
        if (!isLoaded) {
            CircularProgressIndicator(
                color = colorResource(R.color.dark_blue),

            )
        }else{
            Column (
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                offers.forEach{
                        offer ->
                    if (offer != null) {
                        Log.d("Shop", "$offer")
                        Ticket(offer)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreCart(){
    val navController =  rememberNavController()
    Shop(navController)
}