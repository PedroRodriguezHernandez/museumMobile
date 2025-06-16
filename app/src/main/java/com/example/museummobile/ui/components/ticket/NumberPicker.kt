package com.example.museummobile.ui.components.ticket

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange = 0..10,
    modifier: Modifier = Modifier
) {
    var internalValue by remember(value) { mutableIntStateOf(value) }

    LaunchedEffect(value) {
        if (value != internalValue) {
            internalValue = value
        }
    }

    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(15.dp)
            )
            .background(color = Color.White)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (internalValue > range.first) {
                    internalValue--
                    onValueChange(internalValue)
                }
            },
            enabled = internalValue > range.first,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease",
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            text = internalValue.toString(),
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp
        )

        IconButton(
            onClick = {
                if (internalValue < range.last) {
                    internalValue++
                    onValueChange(internalValue)
                }
            },
            enabled = internalValue < range.last,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
