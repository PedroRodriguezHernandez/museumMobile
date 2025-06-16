package com.example.museummobile.ui.components.QrCode

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter


@Composable
fun QRCode(content: String, modifier: Modifier = Modifier) {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 550, 550)
    val bitmap = Bitmap.createBitmap(550, 550, Bitmap.Config.RGB_565)
    for (x in 0 until 550) {
        for (y in 0 until 550) {
            bitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
        }
    }
    Image(bitmap = bitmap.asImageBitmap(), contentDescription = "QR Code ${content}",modifier = modifier)
}