package com.example.museummobile.ui.features.shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.core.model.Museum
import com.example.museummobile.core.supabase.MuseumSupabase
import com.example.museummobile.core.supabase.OfferSupabase
import com.example.museummobile.ui.components.dateSelector.DateSelector
import com.example.museummobile.ui.components.ticket.Ticket
import com.example.museummobile.ui.viewModels.MuseumViewModel
import com.example.museummobile.ui.viewModels.OfferViewModel
import com.example.museummobile.ui.viewModels.SharedViewModel
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun Shop(
    navController: NavController,
    sharedViewModel: SharedViewModel = viewModel()
) {
    val museumViewModel = remember { MuseumViewModel(MuseumSupabase()) }
    val museums = museumViewModel.museums
    var selectedMuseum by remember { mutableStateOf<Museum?>(null) }

    LaunchedEffect(Unit) {
        museumViewModel.loadMuseums()
    }
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

    val filteredSelectDate = selectDates.filter { it.date == currentDate }

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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            TopBarShop(
                selectedDateMillis = selectedDateMillis,
                number = number,
                sharedViewModel = sharedViewModel,
                navController = navController,
                selectedList = filteredSelectDate.toMutableList(),
                function = { showDatePicker = true },
                museums = museums,
                selectedMuseum = selectedMuseum,
                onMuseumSelected = {selectedMuseum = it}
            )

            val filteredOffers = selectedDateMillis?.let { millis ->
                val selectedDate = Instant.fromEpochMilliseconds(millis)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                    .date

                offers.filterNotNull().filter { offer ->
                    val start = offer.start_date
                    val end = offer.end_date

                    val matchesDate = if (end != null) {
                        selectedDate >= start && selectedDate <= end
                    } else {
                        selectedDate >= start
                    }
                    val matchesMuseum = selectedMuseum?.let { museum ->
                        offer.museum_id == museum.id
                    } ?: true

                    matchesDate && matchesMuseum
                }
            } ?: offers.filterNotNull()

            if (selectedDateMillis != null) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val offersToShow = filteredOffers

                    items(offersToShow) { offer ->
                        val countForThisOffer = filteredSelectDate.count {
                            it.id == offer.id
                        }
                        Ticket(
                            offer,
                            selectedDateMillis,
                            filteredSelectDate.toMutableList(),
                            onDatesUpdated = { newDates ->
                                val otherDates = selectDates.filter { it.date != currentDate }
                                val updatedDates = otherDates + newDates
                                sharedViewModel.replaceDates(updatedDates)
                                number = updatedDates.size
                            },
                            countForThisOffer
                        )
                    }
                }
            } else {
                DateSelectorBox(onButtonClick = { showDatePicker = true })
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



@Preview(showBackground = true)
@Composable
fun PreCart(){
    val navController =  rememberNavController()
    Shop(navController)
}