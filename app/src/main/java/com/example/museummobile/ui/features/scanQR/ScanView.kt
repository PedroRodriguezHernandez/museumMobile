package com.example.museummobile.ui.features.scanQR

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.museummobile.camera.CameraPreview
import com.example.museummobile.camera.analyzer.QRCodeAnalyzer
import com.example.museummobile.core.permissions.RequestCameraPermission
import com.example.museummobile.navegation.Screen

@Composable
fun Scan(navController: NavController) {
    val context = LocalContext.current
    var hasPermission by remember { mutableStateOf(false) }

    if (hasPermission) {
        val analyzer = remember {
            QRCodeAnalyzer { qrValue ->
                Toast.makeText(context, "QR leído: $qrValue", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Exhibition.createRoute(qrValue))
            }
        }

        CameraPreview(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            analyzer = analyzer
        )
    } else {
        RequestCameraPermission {
            hasPermission = true
        }
    }
}

