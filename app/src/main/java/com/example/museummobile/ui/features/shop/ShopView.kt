package com.example.museummobile.ui.features.shop

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCartCheckout
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.core.supabase.OfferSupabase
import com.example.museummobile.ui.components.dateSelector.DateSelector
import com.example.museummobile.ui.components.ticket.Ticket
import com.example.museummobile.ui.features.viewModels.OfferViewModel
import com.example.museummobile.ui.features.viewModels.SharedViewModel
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun Shop(navController: NavController,
         sharedViewModel: SharedViewModel = viewModel())
{
    val offerViewModel = remember { OfferViewModel(OfferSupabase()) }
    val offers = offerViewModel.offers
    val isLoaded = offerViewModel.isLoaded

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }

    val selectDates by sharedViewModel.selectDates.collectAsState()
    val currentDate: LocalDate? = selectedDateMillis?.let {
        Instant.fromEpochMilliseconds(it)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
    }

    val selectedList = remember(selectDates, selectedDateMillis) {
        selectDates.filter { it.date == currentDate }
            .toMutableList()
    }

    var number by remember { mutableIntStateOf(selectDates.size) }


    LaunchedEffect(Unit) {
        offerViewModel.loadOffers()
    }


        if (!isLoaded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = colorResource(R.color.dark_blue))
            }
        } else {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = colorResource(R.color.light_blue),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { showDatePicker = true }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.blue),
                            text = selectedDateMillis?.let {
                                Instant.fromEpochMilliseconds(it)
                                    .toLocalDateTime(TimeZone.currentSystemDefault())
                                    .date.toString()
                            } ?: stringResource(R.string.select_date),
                        )
                    }

                    IconButton(onClick = {
                        val selectedDate = selectedDateMillis?.let {
                            Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
                        }

                        selectedDate?.let { currentDate ->
                            val updatedList = sharedViewModel.selectDates.value
                                .filterNot { it.date == currentDate && selectedList.any{sel -> sel.id == it.id} }

                            sharedViewModel.clearDates()
                            sharedViewModel.addDates(updatedList + selectedList)
                        }

                        navController.navigate("cart")
                    }) {
                        BadgedBox( badge = {
                            if (number > 0) {
                                Badge { Text("") }
                            }
                        }) {
                            Icon(Icons.Outlined.ShoppingCartCheckout,
                                contentDescription = stringResource(R.string.shopping_checkout),
                                tint = colorResource(R.color.blue))
                        }

                    }
                }

                val filteredOffers = selectedDateMillis?.let { millis ->
                    val selectedDate = Instant.fromEpochMilliseconds(millis)
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                        .date

                    offers.filterNotNull()
                        .filter { offer ->
                            val start = offer.start_date
                            val end = offer.end_date

                            if (end != null) {
                                selectedDate >= start && selectedDate <= end
                            } else {
                                selectedDate >= start
                            }
                        }
                } ?: offers.filterNotNull()


                if(selectedDateMillis != null) {
                    filteredOffers.forEach { offer ->
                        val countForThisOffer = selectDates.count { it.id == offer.id && it.date == currentDate }

                        Ticket(offer,selectedDateMillis,selectedList,
                            onDatesUpdated = {
                                selectedList.clear()
                                selectedList.addAll(it)
                                number = selectedList.size
                                Log.d("Prueba", "Shop: $number")
                            },
                            countForThisOffer)
                    }
                }else{
                   DateSelectorBox( onButtonClick = {showDatePicker = true })
                }
            }
    }

    if (showDatePicker) {
        DateSelector(
            onDateSelected = { date ->
                selectedDateMillis = date
            },
            onDismiss = {
                showDatePicker = false
            }
        )
    }
}


@Composable
fun DateSelectorBox(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(150.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        color = colorResource(R.color.broken_withe),
        onClick = onButtonClick
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.pls_select_date),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.blue),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreCart(){
    val navController =  rememberNavController()
    Shop(navController)
}