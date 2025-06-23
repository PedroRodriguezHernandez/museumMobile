package com.example.museummobile.ui.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.museummobile.R
import com.example.museummobile.core.model.Museum

@Composable
fun MuseumFilterDropdown(
    museums: List<Museum>,
    selectedMuseumIds: Set<String>,
    onSelectionChange: (Set<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedNames = museums
        .filter { it.id in selectedMuseumIds }
        .map { it.name }

    Box(modifier = modifier) {
        OutlinedButton(onClick = { expanded = true }) {
            Text(stringResource(R.string.select_museum))
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            museums.forEach { museum ->
                val isSelected = museum.id in selectedMuseumIds
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(museum.name)
                        }
                    },
                    onClick = {
                        val updated = if (isSelected) {
                            selectedMuseumIds - museum.id
                        } else {
                            selectedMuseumIds + museum.id
                        }
                        onSelectionChange(updated)
                    }
                )
            }
        }
    }
}
