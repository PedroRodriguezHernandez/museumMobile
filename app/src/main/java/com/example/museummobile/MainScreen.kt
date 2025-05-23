package com.example.museummobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.museummobile.ui.components.footer.Footer
import com.example.museummobile.ui.components.header.Header

@Composable
fun MainScreen(
    content: @Composable () -> Unit,
    title: String = "Lorem Ipsum",
    navController: NavHostController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(R.color.broken_withe))
    ) {
        Scaffold(
            topBar = { Header(title) },
            bottomBar = { Footer(navController) },
            modifier = Modifier
                .navigationBarsPadding()
                .statusBarsPadding()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)

            ) {
                content()
            }
        }
    }
}
