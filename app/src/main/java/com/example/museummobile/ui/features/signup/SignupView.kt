package com.example.museummobile.ui.features.signup

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.museummobile.core.supabase.AuthSupabase
import com.example.museummobile.navegation.Screen
import com.example.museummobile.ui.components.textField.InputField
import com.example.museummobile.ui.features.viewModels.AuthViewModel

@Composable
fun Signup(navController: NavController) {

    val authViewModel = remember { AuthViewModel(AuthSupabase()) }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    if (authViewModel.isLoading){
        Box (
            modifier = Modifier
                .fillMaxSize(),
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
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
                    onClick = {
                        registerUser(context,navController,authViewModel,
                        email,repeatPassword,password,user) },
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
                    onClick = { navController.navigate(Screen.Login.route) }
                )
            }
        }
    }
}

private fun registerUser(
    context: Context,
    navController: NavController,
    viewModel: AuthViewModel,
    email: String,
    repeatPassword: String,
    password: String,
    user: String
) {

    val emailTrim = email.toString().trim()
    Log.d("Signup", "registerUser: $emailTrim")
    if (!Patterns.EMAIL_ADDRESS.matcher(emailTrim).matches()) {
        Toast.makeText(context,R.string.invalid_email, Toast.LENGTH_SHORT).show()
        return
    }

    if (password != repeatPassword || password.length < 6) {
        Toast.makeText(context, R.string.invalid_password, Toast.LENGTH_SHORT).show()
        return
    }

    val forbiddenPatterns = listOf("'", "\"", ";", "--", "/*", "*/", "DROP", "SELECT", "INSERT", "DELETE", "UPDATE")
    if (forbiddenPatterns.any { user.uppercase().contains(it) }) {
        Toast.makeText(context, R.string.invalid_username, Toast.LENGTH_SHORT).show()
        return
    }

    viewModel.signUp(email, password, user) {
        navController.navigate(Screen.Confirm.route) {
            popUpTo("signup") { inclusive = true }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp(){
    val navController =  rememberNavController()
    Signup(navController)
}