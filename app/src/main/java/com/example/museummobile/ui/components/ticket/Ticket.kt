package com.example.museummobile.ui.components.ticket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.museummobile.R

@Composable
fun Ticket(){
    Row (modifier = Modifier.fillMaxWidth()
        .height(108.dp)
        .background(color = colorResource(R.color.broken_withe))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Column (verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxHeight()){
            Text(style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                color = colorResource(R.color.blue),
            ),
                text = "Lorem Ipsum")
            Text(style = TextStyle(
                fontSize = 12.sp,
                color = colorResource(R.color.blue),
            ),
                text = "Lorem Ipsum")
        }
        Icon(imageVector = Icons.Outlined.Image, contentDescription = "Lorem Ipsum",
            modifier = Modifier.fillMaxHeight().aspectRatio(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTicket(){
    Ticket()
}