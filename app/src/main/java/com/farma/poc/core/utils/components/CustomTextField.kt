package com.farma.poc.core.utils.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor


@ExperimentalUnitApi
@Composable
fun customTextField(
    modifier: Modifier,
    isPassword: Boolean,
    changePasswordTransformation: Boolean? = null,
    state: String,
    onValueChange: (String) -> Unit,
    label: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null, textStyle: TextStyle? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    colorsTextField: OutlinedTextFieldColor,
) {
    val maxCharPassword = 10
    val maxCharOtherField = 40
    return TextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorsTextField.focusedBorderColor,
            unfocusedBorderColor = colorsTextField.unfocusedBorderColor
        ),
        modifier = modifier,
        value = state,
        onValueChange = {
            if (it.length <= maxCharPassword && isPassword) onValueChange.invoke(it)
            else if (!isPassword && it.length <= maxCharOtherField) {
                onValueChange.invoke(it)
            }
        },
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else {
            KeyboardOptions(keyboardType = KeyboardType.Text)
        },
        visualTransformation = if (isPassword && changePasswordTransformation == true) PasswordVisualTransformation() else {
            VisualTransformation.None
        },
        trailingIcon = trailingIcon,
        maxLines = 1,
        textStyle = textStyle?.let {
            it
        } ?: run {
            TextStyle(
                color = Colors.whitePrimary,
                textAlign = TextAlign.Left,
                letterSpacing = TextUnit(1F, TextUnitType(type = 10L))
            )
        },
    )
}