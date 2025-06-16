package com.example.museummobile.ui.features.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.core.model.Offer
import com.example.museummobile.core.supabase.OfferSupabase
import com.example.museummobile.core.supabase.TicketsSupabase
import com.example.museummobile.ui.features.viewModels.OfferViewModel
import com.example.museummobile.ui.features.viewModels.SelectDate
import com.example.museummobile.ui.features.viewModels.SharedViewModel
import com.example.museummobile.ui.features.viewModels.TicketsViewModel
import kotlinx.datetime.LocalDate

@Composable
fun Cart(navController: NavController,
         sharedViewModel: SharedViewModel = viewModel()
) {

    var total by remember { mutableDoubleStateOf(0.00) }

    val selectDate by sharedViewModel.selectDates.collectAsState()

    val offerViewModel = remember { OfferViewModel(OfferSupabase()) }
    val ticketsViewModel = remember{ TicketsViewModel(TicketsSupabase())}

    val offers = offerViewModel.offers
    val isLoaded = offerViewModel.isLoaded

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
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.broken_withe))
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        tint = colorResource(R.color.blue),
                        contentDescription = stringResource(R.string.go_back)
                    )
                }
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    Alignment.Center
                ) {
                    Text(text = stringResource(R.string.purchase_summary),
                        color = colorResource(R.color.blue),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                val offerWithDates = selectDate.mapNotNull { selDate ->
                    val offer = offers.find { it?.id == selDate.id }
                    if (offer != null) Pair(offer, selDate.date) else null
                }
                total = calcTotalCost(offerWithDates)

                offerWithDates.forEach { (offer, date) ->
                    CartItem(offer,date,
                        function = {sharedViewModel.removeDate(SelectDate(id = offer.id, date = date))})
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "${stringResource(R.string.total)} ${
                        String.format("%.2f", total)
                    }${stringResource(R.string.euro)}",
                    color = colorResource(R.color.blue),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Button(
                    onClick = {buyTickets(selectDate,ticketsViewModel,sharedViewModel)},
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue))
                ) {
                    Text(text = stringResource(R.string.pay))
                }
            }
        }
    }
}

@Composable
fun PayAcceptance (){

}

fun buyTickets(
    selectDate: List<SelectDate>, ticketsViewModel: TicketsViewModel,sharedViewModel: SharedViewModel) {
    for (date in selectDate) {
       ticketsViewModel.addTickets(date.date,date.id)
        sharedViewModel.removeDate(SelectDate(id = date.id, date = date.date))
    }
}

@Composable
fun CartItem(offer: Offer, date: LocalDate, function: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.broken_withe))
            .border(1.dp, color = colorResource(R.color.ultra_light_blue))
            .padding(13.dp),
        verticalAlignment = Alignment.Top ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            Text(text = offer.name,
                fontSize = 24.sp,
                color = colorResource(R.color.blue),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (offer.age != null) {
                Text(text = "${stringResource(R.string.age)}: ${ offer.age }",
                    fontSize = 16.sp,
                    color = colorResource(R.color.blue)
                )
            }
            Text(text = date.toString(),
                fontSize = 16.sp,
                color = colorResource(R.color.blue)
            )
        }

        Column (
            modifier = Modifier
                .width(70.dp)
        ){
            IconButton(onClick = function) {
                Icon(imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(R.string.delete),
                    tint = colorResource(R.color.blue)
                )
            }
            Text(text = "${String.format("%.2f", offer.price)}${stringResource(R.string.euro)}",
                fontSize = 16.sp,
                color = colorResource(R.color.blue)
            )
        }
    }
}

fun calcTotalCost(offerWithDates: List<Pair<Offer, LocalDate>>): Double {
    return offerWithDates.sumOf { it.first.price }
}

@Preview
@Composable
fun PreCart(){
    val navController =  rememberNavController()
    Cart(navController)
}