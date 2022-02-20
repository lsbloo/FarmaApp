package com.farma.poc.core.utils.components

import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.farma.poc.core.resources.fonts.FontsTheme

@Composable
fun customTextView(
    text: String,
    upperCase: Boolean = false,
    modifier: Modifier,
    color: Color,
    textStyle: TextStyle,
    fontSize: TextUnit,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null) {
    return Text(
        text = if (upperCase) text.uppercase() else {
            text
        },
        color = color,
        style = textStyle,
        modifier = modifier,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign
    )
}