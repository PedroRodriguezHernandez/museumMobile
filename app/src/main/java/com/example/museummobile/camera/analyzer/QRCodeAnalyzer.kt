package com.example.museummobile.camera.analyzer

import android.util.Log
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class QRCodeAnalyzer(
    private val onQRCodeDetected: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE)
            .build()
    )

    private var lastScanTime = 0L
    private val scanCooldownMs = 500L

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {

        val mediaImage = imageProxy.image
        val currentTime = System.currentTimeMillis()
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        barcode.rawValue?.let { rawValue ->
                            if (currentTime - lastScanTime > scanCooldownMs) {
                                lastScanTime = currentTime
                                Log.d("QRCodeAnalyzer", "QR detectado: $rawValue")
                                onQRCodeDetected(rawValue)
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("QRCodeAnalyzer", "Error leyendo QR", e)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}
