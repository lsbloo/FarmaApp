package com.farma.poc.core.utils.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.*
import com.farma.poc.core.resources.colors.Colors

@ExperimentalUnitApi
@Composable
fun customOutlinedTextField(
    modifier: Modifier,
    isPassword: Boolean,
    state: String, onValueChange: (String) -> Unit,
    label: @Composable() () -> Unit, placeholder: @Composable() () -> Unit,
    leadingIcon: @Composable() (() -> Unit)? = null,
) {
    return OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Colors.redPrimary,
            unfocusedBorderColor = Color.Black
        ),
        modifier = modifier,
        value = state,
        onValueChange = {
            onValueChange.invoke(it)
        },
        maxLines = 1,
        textStyle = TextStyle(
            color = Color.Black,
            textAlign = if (isPassword) TextAlign.Center else {
                TextAlign.Left
            },
            letterSpacing = TextUnit(2F, TextUnitType(type = 10L))
        ),
        label = label ,
        placeholder = placeholder,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else {
            KeyboardOptions(keyboardType = KeyboardType.Text)
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else {
            VisualTransformation.None
        },
        leadingIcon = leadingIcon
    )
}