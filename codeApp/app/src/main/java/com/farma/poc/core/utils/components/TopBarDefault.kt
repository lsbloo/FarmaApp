package com.farma.poc.core.utils.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme

class TopBarDefault(
    private val backGroundColor: Color,
    private val imageLeft: Int,
    private val imageSupportText: Int? = null,
    private val textTopBar: String,
    private val onCLickImageLeft: (() -> Unit)? = null
) {

    @ExperimentalUnitApi
    @Composable
    fun setupTopBar() {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(backGroundColor)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(2.dp)),
            backgroundColor = backGroundColor, elevation = 4.dp,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = imageLeft),
                contentDescription = "",
                modifier = Modifier
                    .width(26.dp)
                    .height(26.dp)
                    .clickable {
                        onCLickImageLeft?.invoke()
                    }

            )
            Spacer(modifier = Modifier.width(80.dp))
            imageSupportText?.let {
                Image(
                    painter = painterResource(id = it), contentDescription = "",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(2.dp))
            CustomTextView().apply {
                customTextView(
                    text = textTopBar,
                    upperCase = false,
                    modifier =
                    Modifier.padding(12.dp),
                    color = Colors.whitePrimary,
                    textStyle =
                    FontsTheme(
                        shadow = Shadow(
                            color = Colors.whitePrimary,
                            blurRadius = 2F,
                        )
                    ).typography.h2,
                )
            }
        }
    }
}