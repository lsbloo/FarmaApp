package com.farma.poc.core.utils.components

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun customTextField(modifier: Modifier,
                    isPassword: Boolean,
                    state: String, onValueChange: (String) -> Unit,
                    label: @Composable() () -> Unit, placeholder: @Composable() () -> Unit,
                    leadingIcon: @Composable() (() -> Unit)? = null,) {
    return TextField(value = state, onValueChange = {onValueChange.invoke(it)})
}