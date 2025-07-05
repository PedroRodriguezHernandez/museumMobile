
package com.example.museummobile.ui.components.ticket

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.museummobile.R
import com.example.museummobile.core.model.Offer
import com.example.museummobile.ui.viewModels.SelectDate
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.log


@Composable
fun Ticket(
    offer: Offer,
    selectedDateMillis: Long? = null,
    dates: List<SelectDate>,
    onDatesUpdated: (List<SelectDate>) -> Unit,
    number: Int,
) {
    val selectedDate = selectedDateMillis?.let {
        Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
    }


    var currentNumber by remember (number){ mutableIntStateOf(number) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .border(1.dp, colorResource(R.color.blue), RoundedCornerShape(15.dp))
            .height(108.dp)
            .background(colorResource(R.color.broken_withe))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(
                text = offer.name,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    color = colorResource(R.color.blue)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${stringResource(R.string.price)} ${offer.price}€",
                fontSize = 12.sp,
                color = colorResource(R.color.blue)
            )
            offer.age?.let {
                Text(
                    text = "$it ${stringResource(R.string.age)}",
                    fontSize = 12.sp,
                    color = colorResource(R.color.blue)
                )
            }
        }

        Box(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
        ) {
            NumberPicker(
                value = currentNumber,
                onValueChange = { newValue ->
                    if (selectedDate != null) {
                        val updatedList = dates.toMutableList()
                        val diff = newValue - currentNumber

                        currentNumber = newValue

                        if (diff > 0) {
                            repeat(diff) {
                                updatedList.add(SelectDate(id = offer.id, date = selectedDate))
                            }
                        } else if (diff < 0) {
                            repeat(-diff) {
                                val index = updatedList.indexOfLast {
                                    it.id == offer.id && it.date == selectedDate
                                }
                                if (index != -1) {
                                    updatedList.removeAt(index)
                                }
                            }
                        }
                        onDatesUpdated(updatedList)
                    }
                },
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}



