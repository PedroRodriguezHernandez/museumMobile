package com.example.museummobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.navegation.AppNavGraph
import com.example.museummobile.ui.theme.MuseumMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MuseumMobileTheme {
                val navController =  rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuseumMobileTheme {
        val navController =  rememberNavController()
        AppNavGraph(navController)
    }
}