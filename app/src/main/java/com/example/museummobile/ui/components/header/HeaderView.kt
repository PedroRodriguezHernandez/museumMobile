package com.example.museummobile.ui.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.museummobile.R

@Composable
fun Header(title: String = "Lorem Ipsum", onMenuClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(colorResource(R.color.broken_withe))
            .fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalContentColor provides colorResource(R.color.dark_blue)) {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
            Text(
                text = title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHeader(){
    Header(onMenuClick = {})
}