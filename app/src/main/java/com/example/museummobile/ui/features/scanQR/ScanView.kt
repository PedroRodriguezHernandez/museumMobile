package com.example.museummobile.ui.features.scanQR

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.museummobile.R
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
                try {
                    navController.navigate(Screen.Exhibition.createRoute(qrValue))
                } catch (e: IllegalArgumentException) {
                    Log.e("Navigation", "Ruta inválida desde QR: $qrValue", e)
                    Toast.makeText(context, context.getString(R.string.fail_to_open_QR), Toast.LENGTH_SHORT).show()
                }
            }
        }

        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            analyzer = analyzer
        )
    } else {
        RequestCameraPermission {
            hasPermission = true
        }
    }
}


