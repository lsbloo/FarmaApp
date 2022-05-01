package com.farma.poc.core.utils.colors

import androidx.compose.ui.graphics.Color
import com.farma.poc.core.resources.colors.Colors

data class OutlinedTextFieldColor(
    val focusedBorderColor: Color,
    val unfocusedBorderColor: Color
) {

    companion object {
        fun getDefaultTextFieldOutlinedColor() =
            OutlinedTextFieldColor(focusedBorderColor = Colors.whitePrimary, Colors.whiteSecundary)
    }
}