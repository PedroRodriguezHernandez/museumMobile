package com.example.museummobile.core.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

object FCMTokenManager {
    fun getToken(onTokenReceived: (String) -> Unit) {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                val token = task.result
                Log.d("FCM", "Token: $token")
                onTokenReceived(token)
            }
    }
}