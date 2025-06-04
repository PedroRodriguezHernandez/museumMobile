package com.example.museummobile.ui.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museummobile.R
import com.example.museummobile.core.supabase.AuthSupabase
import com.example.museummobile.core.supabase.UserSupabase
import com.example.museummobile.ui.features.viewModels.AuthViewModel
import com.example.museummobile.ui.features.viewModels.UserViewModel

@Composable
fun Home(navController: NavController) {

    val userViewModel = remember { UserViewModel(UserSupabase()) }
    val authViewModel = remember { AuthViewModel(AuthSupabase()) }

    val user = userViewModel.user.value
    val authModel = authViewModel.authModel

    LaunchedEffect(authModel?.uid) {
        authModel?.uid?.let { uid ->
            userViewModel.loadUserById(uid)
        }
    }


    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (userViewModel.isLoading){
                CircularProgressIndicator()
            }else{
                Text(
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.blue),
                        fontFamily = FontFamily.Serif
                    ),
                    text = "${stringResource(R.string.welcome)} ${user?.name}"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreHome(){
    val navController =  rememberNavController()
    Home(navController)
}