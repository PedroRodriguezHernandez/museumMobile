package com.example.museummobile.ui.features.cart

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.museummobile.R

@Composable
fun PayAcceptance(function: () -> Unit, functionAcceptance: () -> Unit) {
    AlertDialog(
        onDismissRequest = { function() },
        title = {
            Text(text = stringResource(R.string.confirm_payment))
        },
        text = {
            Text(text = stringResource(R.string.entry_policy))
        },
        confirmButton = {
            Button(
                onClick = {
                    functionAcceptance()
                }
            ) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(
                onClick = { function() }
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}