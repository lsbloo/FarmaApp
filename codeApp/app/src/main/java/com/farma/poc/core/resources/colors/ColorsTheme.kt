package com.farma.poc.core.resources.colors

import androidx.compose.material.lightColors

class ColorsTheme {

    fun primaryTheme(): androidx.compose.material.Colors {
        return lightColors(
            primary = Colors.redPrimary,
            primaryVariant = Colors.redSecundary,
            secondary = Colors.lightGray
        )
    }
}