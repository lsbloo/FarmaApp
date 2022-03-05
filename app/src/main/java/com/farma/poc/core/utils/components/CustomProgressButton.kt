package com.farma.poc.core.utils.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

class CustomProgressButton {
    @Composable
    fun customProgressButton(modifier: Modifier = Modifier,
                             color: Color = MaterialTheme.colors.primary,
                             strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth){
        return Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = modifier,
                color = color,
                strokeWidth = strokeWidth
            )
        }
    }
}