package com.farma.poc.core.utils.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor

class CustomTopBar(
    private val backGroundColor: Color,
    private val imageLeft: Int? = null,
    private val imageSupportText: Int? = null,
    private val imageCart: Int? = null,
    private val textTopBar: String,
    private val onCLickImageLeft: (() -> Unit)? = null,
    private val hasSearchField: Boolean? = false,
    private val labelSearchTextField: String? = null,
    private val iconLabelSearchField: Int? = null,
    private val onValueTextSearchChange: ((String) -> Unit)? = null,
    private val onCLickIconSearchField: (() -> Unit)? = null,
    private val onClickBasketIcon: (() -> Unit)? = null,
    private val colorsTextField: OutlinedTextFieldColor
) {

    @ExperimentalUnitApi
    @Composable
    fun setupTopBar() {

        var stateField by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (labelSearchTextField != null) 120.dp else 75.dp)
                .shadow(elevation = 8.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf<Color>(
                            backGroundColor,
                            backGroundColor
                        ),
                    )
                ),
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(8.dp))
                AnimatedVisibility(visible = imageLeft != null) {
                    imageLeft?.let { painterResource(id = it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "",
                            modifier = Modifier
                                .width(26.dp)
                                .height(26.dp)
                                .clickable {
                                    onCLickImageLeft?.invoke()
                                }

                        )
                    }
                }
                if (imageLeft != null) Spacer(modifier = Modifier.width(80.dp)) else Spacer(
                    modifier = Modifier.width(
                        120.dp
                    )
                )
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
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
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

                if (imageCart != null) Spacer(modifier = Modifier.width(65.dp))
                imageCart?.let {
                    Image(
                        painter = painterResource(id = it), contentDescription = "",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .clickable {
                                onClickBasketIcon?.invoke()
                            }
                    )
                }
            }
            if (hasSearchField == true) Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .padding(start = 36.dp, end = 36.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                labelSearchTextField?.let { labelField ->
                    TextField(
                        value = stateField,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = colorsTextField.focusedBorderColor,
                            focusedBorderColor = colorsTextField.focusedBorderColor,
                            unfocusedBorderColor = colorsTextField.unfocusedBorderColor
                        ),
                        trailingIcon = {
                            iconLabelSearchField?.let {
                                Icon(
                                    painter = painterResource(id = it),
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        onCLickIconSearchField?.invoke()
                                    })
                            }
                        },
                        placeholder = {
                            Text(
                                text = labelField,
                                color = Colors.black,
                                textAlign = TextAlign.Left,
                                style = FontsTheme(
                                    shadow = Shadow(
                                        color = Colors.black,
                                        blurRadius = 2F,
                                    )
                                ).typography.h3
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = true,
                        onValueChange = { newValue ->
                            stateField = newValue
                            onValueTextSearchChange?.invoke(stateField)
                        },
                        textStyle = TextStyle(
                            color = Colors.black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Left,
                            textDirection = TextDirection.Ltr,
                        )
                    )
                }
            }
            if (hasSearchField == true) Spacer(modifier = Modifier.height(18.dp))
        }
    }
}