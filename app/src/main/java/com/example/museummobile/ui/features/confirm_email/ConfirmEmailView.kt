package com.example.museummobile.ui.features.confirm_email

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.navegation.Screen

@Composable
fun ConfirmEmail(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.broken_withe))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.confirm_email),
            fontSize = 24.sp,
            color = colorResource(R.color.blue),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.check_email),
            fontSize = 18.sp,
            color = colorResource(R.color.blue),
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue)),
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        ) {
                Text(
                    text = stringResource(R.string.understand),
                    fontSize = 18.sp
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun preConfirmEmail(){
    val navController =  rememberNavController()
    ConfirmEmail(navController)
}