package com.example.museummobile.ui.features.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.museummobile.R
import com.example.museummobile.core.model.Museum
import com.example.museummobile.core.supabase.MuseumSupabase
import com.example.museummobile.ui.viewModels.MuseumViewModel

@Composable
fun MuseumSelectorBox(
    onMuseumSelected: (Museum) -> Unit
){
    val museumViewModel = remember { MuseumViewModel(MuseumSupabase()) }
    val museums = museumViewModel.museums
    val isLoading = museumViewModel.isLoading

    var expanded by remember { mutableStateOf(false) }
    var selectedMuseum by remember { mutableStateOf<Museum?>(null) }

    LaunchedEffect(Unit) {
        museumViewModel.loadMuseums()
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        color = colorResource(R.color.broken_withe),
        onClick = { expanded = true }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = colorResource(R.color.dark_blue))
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = selectedMuseum?.name ?: "Selecciona un museo",
                        color = colorResource(R.color.blue),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.clickable { expanded = true }
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        museums.forEach { museum ->
                            DropdownMenuItem(
                                text = { museum?.let { Text(it.name) } },
                                onClick = {
                                    selectedMuseum = museum
                                    museum?.let { onMuseumSelected(it) }
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}