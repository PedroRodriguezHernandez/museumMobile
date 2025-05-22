package com.example.museummobile.ui.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.museummobile.R
import com.example.museummobile.ui.components.textField.InputField
import com.example.museummobile.ui.theme.MuseumMobileTheme

@Composable
fun Login(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){
        Text(stringResource(R.string.museum_name))

        Column(
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var text by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            InputField(
                modifier = Modifier.fillMaxWidth(),
                focusColor = colorResource(R.color.light_blue),
                unfocusColor = colorResource(R.color.ultra_light_blue),
                value = text,
                onValueChange = { text = it },
                placeholder = "Enter username",
                backgroundColor = Color.White,
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email Icon")
                }
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                focusColor = colorResource(R.color.light_blue),
                unfocusColor = colorResource(R.color.ultra_light_blue),
                value = password,
                onValueChange = { password = it },
                placeholder = "Enter password",
                isPassword = true,
                backgroundColor = Color.White,
                leadingIcon = {
                    Icon(
                        Icons.Default.Password,
                        contentDescription = "Password Icon",
                    )
                }
            )
            Button(
                onClick = { TODO() },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue),
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.login))
            }
            ClickableText(
                text = AnnotatedString(stringResource(R.string.forgot_password)),
                onClick = {  TODO() }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.dont_have_acount))
            ClickableText(
                text = AnnotatedString(stringResource(R.string.signUp)),
                onClick = { TODO() }
            )
        }

    }
}

@Preview( showBackground = true)
@Composable
fun LoginPreview(){
    MuseumMobileTheme {
        Login()
    }
}
