package com.example.museummobile.ui.features.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.museummobile.R
import com.example.museummobile.core.model.Offer
import kotlinx.datetime.LocalDate

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