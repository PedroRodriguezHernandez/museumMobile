package com.example.museummobile.ui.features.scanQR

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.museummobile.camera.CameraPreview
import com.example.museummobile.camera.analyzer.QRCodeAnalyzer

@Composable
fun Scan(navController: NavController) {
    val context = LocalContext.current
    val analyzer = remember {
        QRCodeAnalyzer { qrValue ->
            Toast.makeText(context, "QR leído: $qrValue", Toast.LENGTH_SHORT).show()
            // Aquí puedes manejar el valor leído, navegar, etc.
        }
    }

    CameraPreview(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        analyzer = analyzer
    )
}
