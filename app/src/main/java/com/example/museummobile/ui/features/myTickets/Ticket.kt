package com.example.museummobile.ui.features.myTickets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.museummobile.R
import com.example.museummobile.core.model.Tickets

@Composable
fun Ticket(tickets: Tickets) {

    var showPopup by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.broken_withe))
            .border(1.dp, color = colorResource(R.color.ultra_light_blue))
            .padding(13.dp)
            .clickable { showPopup = true },
        verticalAlignment = Alignment.Top ,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            tickets.name?.let {
                Text(
                    text = it,
                    fontSize = 24.sp,
                    color = colorResource(R.color.blue),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (tickets.age != null) {
                Text(text = "${stringResource(R.string.age)}: ${ tickets.age }",
                    fontSize = 16.sp,
                    color = colorResource(R.color.blue)
                )
            }
            Text(text = tickets.date_for.toString(),
                fontSize = 16.sp,
                color = colorResource(R.color.blue)
            )
            if(showPopup){
                tickets.id?.let { QrPopup(it) {
                    showPopup = false
                }
                }
            }
        }
    }
}