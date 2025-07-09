package com.example.museummobile.ui.features.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(onClick = { function() }) {
                    Text(text = stringResource(R.string.cancel))
                }
                Spacer(modifier = Modifier.width(32.dp))
                Button(onClick = { functionAcceptance() }) {
                    Text(text = stringResource(R.string.confirm))
                }
            }
        }
    )
}