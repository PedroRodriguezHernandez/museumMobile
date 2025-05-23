package com.example.museummobile.ui.components.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LinkedCamera
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.museummobile.R

@Composable
fun Footer(){
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(colorResource(R.color.broken_withe)),
        horizontalArrangement = Arrangement.SpaceAround
    ){
    CompositionLocalProvider(LocalContentColor provides colorResource(R.color.dark_blue)) {
        IconButton(onClick = { TODO() }) {
            Icon(
                imageVector = Icons.Filled.AddShoppingCart,
                contentDescription = stringResource(R.string.shoping_cart)
            )
        }
        IconButton(onClick = { TODO() }) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = stringResource(R.string.home)
            )
        }
        IconButton(onClick = { TODO() }) {
            Icon(
                imageVector = Icons.Outlined.ConfirmationNumber,
                contentDescription = stringResource(R.string.ticket)
            )
        }
        IconButton(onClick = { TODO() }) {
            Icon(
                imageVector = Icons.Outlined.QrCodeScanner,
                contentDescription = stringResource(R.string.camera)
            )
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun PreFooter(){
    Footer();
}