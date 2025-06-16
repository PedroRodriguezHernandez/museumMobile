package com.example.museummobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
            modifier = Modifier
                .background(color = colorResource(R.color.broken_withe))
                .navigationBarsPadding()
                .statusBarsPadding(),
            topBar = {
                Header(title = title, onMenuClick = {
                    scope.launch { drawerState.open() }
                })
            },
            bottomBar = { Footer(navController) },

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

