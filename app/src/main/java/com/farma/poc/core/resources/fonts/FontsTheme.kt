package com.farma.poc.core.resources.fonts

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.sp
import com.farma.poc.R
import org.w3c.dom.Text

interface PropertiesFont {
    fun setupStyle(shadow: Shadow, fontFamily: FontFamily, fontWeight: FontWeight? = null): TextStyle
}

enum class FontEnum(val textStyle: TextStyle): PropertiesFont {
    H1(
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Left,
            lineHeight = 50.sp,
    )){
        override fun setupStyle(shadow: Shadow, fontFamily: FontFamily,fontWeight: FontWeight?): TextStyle {
            return TextStyle(
                fontWeight = textStyle.fontWeight,
                fontSize =  textStyle.fontSize,
                letterSpacing = textStyle.letterSpacing,
                textAlign = textStyle.textAlign,
                lineHeight = textStyle.lineHeight,
                shadow = shadow,
                fontFamily = fontFamily
            )
        }
    },

    H2(
    textStyle = TextStyle(
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp,
        letterSpacing = 2.sp,
        textAlign = TextAlign.Left,
        lineHeight = 50.sp,
    )){
        override fun setupStyle(shadow: Shadow, fontFamily: FontFamily,fontWeight: FontWeight?): TextStyle {
            return TextStyle(
                fontWeight = fontWeight?.let { it } ?: textStyle.fontWeight,
                fontSize =  textStyle.fontSize,
                letterSpacing = textStyle.letterSpacing,
                textAlign = textStyle.textAlign,
                lineHeight = textStyle.lineHeight,
                shadow = shadow,
                fontFamily = fontFamily
            )
        }
    }

}

class FontsTheme(shadow: Shadow, fontWeight: FontWeight? = null) {

    private val fonts = FontFamily(
        Font(R.font.roboto_black, weight = FontWeight.Medium),
        Font(R.font.roboto_medium, weight = FontWeight.Normal),
        Font(R.font.roboto_bold, weight = FontWeight.Bold),
        Font(R.font.roboto_light, weight = FontWeight.Light),
    )


    @ExperimentalUnitApi
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
        ),
        h1 = FontEnum.H1.setupStyle(
            shadow = shadow,
            fontFamily = fonts
        ),
        body2 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
        ),
        h2 = FontEnum.H2.setupStyle(
            shadow = shadow,
            fontFamily = fonts,
            fontWeight = fontWeight
        ),
        h3 = TextStyle(
            fontFamily = fonts,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
        ),
    )
}