package com.example.museummobile.ui.components.sideDrawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.museummobile.R
import com.example.museummobile.ui.viewModels.SharedViewModel

@Composable
fun SideDrawer(
    drawerState: DrawerState,
    navController: NavHostController,
    onLogoutClick: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = stringResource(R.string.option),
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.log_out)) },
                    selected = false,
                    onClick = {
                        onLogoutClick()
                    }
                )
            }
        },
        content = content
    )
}
