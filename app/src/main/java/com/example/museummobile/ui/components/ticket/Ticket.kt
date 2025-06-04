package com.example.museummobile.ui.components.ticket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.museummobile.R
import com.example.museummobile.core.model.Offer
import kotlinx.datetime.LocalDate
import kotlin.math.round

@Composable
fun Ticket(offer: Offer){
    Row (modifier = Modifier.fillMaxWidth()
        .clip(shape = RoundedCornerShape(15.dp))
        .border(
            width = 1.dp,
            color = colorResource(R.color.blue),
            shape = RoundedCornerShape(15.dp)
        ).height(108.dp)
        .background(color = colorResource(R.color.broken_withe))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            Text(
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    color = colorResource(R.color.blue),
                ),
                text = offer.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                style = TextStyle(
                    fontSize = 12.sp,
                    color = colorResource(R.color.blue),
                ),
                text = "${stringResource(R.string.price)} ${offer.price}€"
            )
            if (offer.age != null) {
                Text(
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = colorResource(R.color.blue),
                    ),
                    text = "${offer.age} ${stringResource(R.string.age)}"
                )
            }
        }
        var number by remember { mutableStateOf(0) }
        Box(modifier = Modifier
            .wrapContentWidth() // ocupa solo lo necesario
            .fillMaxHeight()){
            NumberPicker(number,{number = it},modifier = Modifier.align(Alignment.TopEnd))
        }
    }
}

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange = 0..10,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(15.dp)
            )
            .background(color = Color.White)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { if (value > range.first) onValueChange(value - 1) },
            enabled = value > range.first,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease",
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            text = value.toString(),
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp
        )

        IconButton(
            onClick = { if (value < range.last) onValueChange(value + 1) },
            enabled = value < range.last,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTicket(){
    val offer = Offer(
        id = 0,
        name = "Feliz día de canaria: Solo para residentes canarios",
        price = 00.00,
        age = 12,
        startDate = LocalDate(2020,10,20),
        endDate = null,
    )
    Ticket(offer)
}