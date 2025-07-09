package com.example.museummobile.ui.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.core.supabase.AuthSupabase
import com.example.museummobile.navegation.Screen
import com.example.museummobile.ui.components.textField.InputField
import com.example.museummobile.ui.viewModels.AuthViewModel
import com.example.museummobile.ui.theme.MuseumMobileTheme

@Composable
fun Login(navController: NavController) {

    val authViewModel = remember { AuthViewModel(AuthSupabase()) }
    val loggedIn = authViewModel.isLoggedIn.value
    val isLoading = authViewModel.isLoading

    LaunchedEffect(Unit) {
        authViewModel.checkLoggedIn()
    }

    LaunchedEffect(loggedIn) {
        if (loggedIn) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }


    if (isLoading) {
        Box (
            modifier = Modifier
                .fillMaxSize(),
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    } else {
        Column (
            modifier = Modifier.fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ){
            Text(
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.dark_blue),
                    fontFamily = FontFamily.Serif
                ),
                text = stringResource(R.string.app_name)
            )

            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    focusColor = colorResource(R.color.light_blue),
                    unfocusColor = colorResource(R.color.ultra_light_blue),
                    value = email,
                    onValueChange = { email = it },
                    placeholder = stringResource(R.string.enter_username),
                    backgroundColor = Color.White,
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = "Email Icon")
                    },
                    keyboardType = KeyboardType.Email
                )
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    focusColor = colorResource(R.color.light_blue),
                    unfocusColor = colorResource(R.color.ultra_light_blue),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = stringResource(R.string.enter_password),
                    isPassword = true,
                    backgroundColor = Color.White,
                    leadingIcon = {
                        Icon(
                            Icons.Default.Password,
                            contentDescription = "Password Icon",
                        )
                    },
                    keyboardType = KeyboardType.Password
                )
                authViewModel.errorMessage?.let { errorKey ->
                    val message = when (errorKey) {
                        "fail_login" -> stringResource(id = R.string.fail_login)
                        else -> errorKey
                    }

                    Text(text = message, color = Color.Red)
                }
                Button(
                    onClick = {
                        authViewModel.login(email, password) {
                            navController.navigate(Screen.Home.route)
                        }
                    },
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
                    onClick = { }
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.dont_have_acount))
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.signUp)),
                    onClick = { navController.navigate(Screen.SignUp.route) }
                )
            }
        }
    }
}

@Preview( showBackground = true)
@Composable
fun LoginPreview(){
    MuseumMobileTheme {
        val navController =  rememberNavController()
        Login(navController)
    }
}
