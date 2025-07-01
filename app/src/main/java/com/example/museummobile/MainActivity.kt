package com.example.museummobile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.core.firebase.FCMTokenManager
import com.example.museummobile.core.permissions.RequestNotificationPermission
import com.example.museummobile.navegation.AppNavGraph
import com.example.museummobile.ui.theme.MuseumMobileTheme
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        FCMTokenManager.getToken{}

        setContent {
            MuseumMobileTheme {
                val navController = rememberNavController()
                val permissionGranted = remember { mutableStateOf(false) }

                RequestNotificationPermission { granted ->
                    permissionGranted.value = granted
                    if (granted) {
                        FirebaseMessaging.getInstance().subscribeToTopic("news")
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("FCM", "Suscripción al topic 'news' exitosa")
                                } else {
                                    Log.e("FCM", "Error al suscribirse al topic 'news'", task.exception)
                                }
                            }
                    } else {
                        Log.w("FCM", "Permiso de notificaciones denegado")
                    }
                }

                AppNavGraph(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuseumMobileTheme {
        val navController = rememberNavController()
        AppNavGraph(navController)
    }
}
