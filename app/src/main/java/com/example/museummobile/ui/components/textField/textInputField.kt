package com.example.museummobile.ui.components.textField

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.museummobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    focusColor: Color,
    unfocusColor: Color,
    backgroundColor: Color,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: (@Composable (() -> Unit))? = null,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (isPassword && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val trailingIcon: @Composable (() -> Unit)? = if (isPassword) {
        {
            val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = icon,
                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = if (passwordVisible) focusColor else unfocusColor
                )
            }
        }
    } else null

    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = unfocusColor) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(color = colorResource(R.color.black)),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusColor,
            unfocusedBorderColor = unfocusColor,
            focusedLeadingIconColor = focusColor,
            unfocusedLeadingIconColor = unfocusColor,
            focusedPlaceholderColor = focusColor,
            unfocusedPlaceholderColor = unfocusColor,
            containerColor = backgroundColor,
            cursorColor = focusColor
        )
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewInputField(){
    var text by remember { mutableStateOf("") }
    InputField(
        value = text,
        onValueChange = { text = it },
        placeholder = "Contraseña",
        focusColor = colorResource(R.color.blue),
        unfocusColor = colorResource(R.color.light_blue),
        backgroundColor = colorResource(R.color.white),
        isPassword = true,
        leadingIcon = {
            Icon(Icons.Default.Password, contentDescription = "Password Icon")
        },
        keyboardType = KeyboardType.Password
    )
}