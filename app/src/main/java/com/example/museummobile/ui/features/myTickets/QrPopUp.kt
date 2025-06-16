package com.example.museummobile.ui.features.myTickets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.museummobile.R
import com.example.museummobile.ui.components.QrCode.QRCode

@Composable
fun QrPopup(content: String, function: () -> Unit){

    Popup(
        alignment = Alignment.Center,
        properties = PopupProperties(focusable = true),
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, color = colorResource(R.color.ultra_light_blue),
                        shape = RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(20.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    QRCode(
                        content = content,
                        modifier = Modifier.size(300.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = content, fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = function,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.blue),
                            contentColor = Color.White
                        ),) {
                        Text(stringResource(R.string.close))
                    }
                }
            }
        }
    }
}
