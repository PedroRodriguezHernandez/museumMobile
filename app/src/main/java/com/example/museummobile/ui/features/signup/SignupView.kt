package com.example.museummobile.ui.features.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.navegation.Screen
import com.example.museummobile.ui.components.textField.InputField

@Composable
fun Signup(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){
        Text(style = TextStyle(
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.dark_blue),
            fontFamily = FontFamily.Serif
        ),
            text = stringResource(R.string.museum_name))

        Column(
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var user by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var repeatPassword by remember { mutableStateOf("") }

            InputField(
                modifier = Modifier.fillMaxWidth(),
                focusColor = colorResource(R.color.light_blue),
                unfocusColor = colorResource(R.color.ultra_light_blue),
                value = user,
                onValueChange = { user = it },
                placeholder = stringResource(R.string.user_name),
                backgroundColor = Color.White,
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Person Icon")
                }
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                focusColor = colorResource(R.color.light_blue),
                unfocusColor = colorResource(R.color.ultra_light_blue),
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.email),
                backgroundColor = Color.White,
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Person Icon")
                }
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                focusColor = colorResource(R.color.light_blue),
                unfocusColor = colorResource(R.color.ultra_light_blue),
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(R.string.password),
                backgroundColor = Color.White,
                isPassword = true,
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Lock Icon")
                }
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                focusColor = colorResource(R.color.light_blue),
                unfocusColor = colorResource(R.color.ultra_light_blue),
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                placeholder = stringResource(R.string.rep_password),
                backgroundColor = Color.White,
                isPassword = true,
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Lock Icon")
                }
            )
            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue),
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.signUp))
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.have_acount))
            ClickableText(
                text = AnnotatedString(stringResource(R.string.login)),
                onClick = { navController.navigate(Screen.Login.route)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp(){
    val navController =  rememberNavController()
    Signup(navController)
}