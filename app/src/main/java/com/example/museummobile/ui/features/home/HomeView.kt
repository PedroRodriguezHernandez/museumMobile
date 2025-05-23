package com.example.museummobile.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R

@Composable
fun Home(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
        ){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue),
                fontFamily = FontFamily.Serif
            ),
            text = stringResource(R.string.welcome)
        )
        Icon(
            imageVector = Icons.Default.DinnerDining,
            contentDescription = "Pitosaurio"
        )
    }
}
}

@Preview(showBackground = true)
@Composable
fun PreHome(){
    val navController =  rememberNavController()
    Home(navController)
}