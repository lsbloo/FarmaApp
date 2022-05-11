package com.farma.poc.core.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme

class CustomCircularImageView(
    private val textImageView: String? = null,
    private val image: Int = 0,
    private val onClickImage: ((Int) -> Unit)? = null,
    private val index: Int
) {

    @ExperimentalUnitApi
    @Composable
    fun setup() {
        Column(
            modifier = Modifier
                .height(120.dp)
                .width(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(image != 0) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(color = Colors.blackBackGroundPrimaryTheme)
                        .clickable {
                            onClickImage?.invoke(index)
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.logo_farma),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(color = Colors.blackBackGroundPrimaryTheme)
                        .clickable {
                            onClickImage?.invoke(index)
                        }
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            textImageView?.let {
                Text(
                    text = it,
                    color = Colors.whitePrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 8.dp),
                    style = FontsTheme(
                        shadow = Shadow(
                            color = Colors.whitePrimary,
                            blurRadius = 2F,
                        )
                    ).typography.h4
                )
            }
        }
    }

    companion object {
        @ExperimentalUnitApi
        @Composable
        fun setupCategories(
            textImageView: String? = null,
            image: Int = 0,
            onClickImage: ((Int) -> Unit)? = null,
            index: Int
        ) = CustomCircularImageView(
            textImageView = textImageView,
            image,
            onClickImage = onClickImage,
            index = index
        ).setup()
    }
}