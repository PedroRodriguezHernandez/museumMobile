package com.example.museummobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.museummobile.ui.components.footer.Footer
import com.example.museummobile.ui.components.header.Header
import com.example.museummobile.ui.components.sideDrawer.SideDrawer
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    content: @Composable () -> Unit,
    title: String = "Lorem Ipsum",
    navController: NavHostController,
    onLogoutClick: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    SideDrawer(
        drawerState = drawerState,
        navController = navController,
        onLogoutClick = onLogoutClick
    ) {
        Scaffold(
            topBar = {
                Header(title = title, onMenuClick = {
                    scope.launch { drawerState.open() }
                })
            },
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

