package com.farma.poc.core.utils.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class CustomCircularButton {

    @Composable
    fun customCircularButton(
        content: @Composable () -> Unit,
        onClick: () -> Unit,
        elevation: ButtonElevation,
        colors: ButtonColors,
        contentPadding: PaddingValues,
        enabled: Boolean,
        modifier: Modifier,
        shape: RoundedCornerShape? = null
    ) {
        shape?.let {
            return Button(
                onClick = { onClick.invoke() },
                modifier = modifier,
                elevation = elevation,
                colors = colors,
                contentPadding = contentPadding,
                enabled = enabled,
                shape = it
            ) {
                content.invoke()
            }

        } ?: run {
            val defaultShape = RoundedCornerShape(2.dp)
            return Button(
                onClick = { onClick.invoke() },
                modifier = modifier,
                elevation = elevation,
                colors = colors,
                contentPadding = contentPadding,
                enabled = enabled,
                shape = defaultShape
            ) {
                content.invoke()
            }
        }

    }
}