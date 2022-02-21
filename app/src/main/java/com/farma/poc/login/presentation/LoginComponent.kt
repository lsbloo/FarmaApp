package com.farma.poc.login.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.components.CustomCircularButton
import com.farma.poc.core.utils.components.CustomTextView
import com.farma.poc.core.utils.components.customTextField

@ExperimentalUnitApi
@Composable
fun screenLogin(){
    Scaffold(
        topBar = {},
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    backgroundColor = Colors.redPrimary
                ) {
                    Icon(Icons.Filled.Info,"")
                }
            },
        modifier = Modifier.fillMaxSize(), content = {
            topBanner()
        })
}


@ExperimentalUnitApi
@Composable
@Preview(showBackground = true)
fun topBanner(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf<Color>(
                        Colors.whitePrimary,
                        Colors.whitePrimary
                    ),
                ), alpha = 1.2f
            )
    ) {
        bodyContent()
    }

}

@ExperimentalUnitApi
@Composable
@Preview
fun bodyContent() {
    var loginText by remember {
        mutableStateOf("")
    }

    var passwordText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_farm), contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextView().apply{
            customTextView(
            text = LoginConstants.View.LABEL_EMAIL,
            upperCase = true,
            color = Colors.redQuar,
            modifier = Modifier.padding(start = 40.dp, end = 40.dp),
            textStyle =
            FontsTheme(
                shadow = Shadow(
                    color = Colors.redQuar,
                    blurRadius = 1F,
                )
            ).typography.h1,
            fontSize = TextUnit(20F, TextUnitType(20))
        )}
        customTextField(modifier = Modifier
            .padding(start = 40.dp, end = 20.dp)
            .fillMaxWidth()
            .padding(
                end = 10.dp
            ),
            isPassword = false,
            loginText,
            onValueChange = { newValue ->
                loginText = newValue
            })

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextView().apply{customTextView(
            text = LoginConstants.View.LABEL_PASSWORD,
            upperCase = true,
            color = Colors.redQuar,
            modifier = Modifier.padding(start = 40.dp, end = 40.dp),
            textStyle =
            FontsTheme(
                shadow = Shadow(
                    color = Colors.redQuar,
                    blurRadius = 1F,
                )
            ).typography.h1,
            fontSize = TextUnit(20F, TextUnitType(20))
        )}
        customTextField(modifier = Modifier
            .padding(start = 40.dp, end = 20.dp)
            .fillMaxWidth()
            .padding(
                end = 10.dp
            ),
            isPassword = true,
            passwordText,
            onValueChange = { newValue ->
                passwordText = newValue
            })

        Spacer(modifier = Modifier.height(28.dp))
        CustomTextView().apply{
            customTextView(
            text = LoginConstants.View.LABEL_FORGOT_PASSWORD, upperCase = true,
            modifier =
            Modifier
                .padding(start = 40.dp, end = 28.dp)
                .align(Alignment.End), color = Colors.redQuar, textStyle =
            FontsTheme(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Colors.redQuar,
                    blurRadius = 2F,
                )
            ).typography.h2
        )}

        Spacer(modifier = Modifier.height(28.dp))
        CustomCircularButton().apply {
            customCircularButton(
                content = {
                    CustomTextView().apply {
                        customTextView(
                            text = LoginConstants.View.BUTTON_LABEL_LOGIN, upperCase = true,
                            modifier =
                            Modifier.padding(12.dp), color = Colors.whitePrimary, textStyle =
                            FontsTheme(
                                shadow = Shadow(
                                    color = Colors.whitePrimary,
                                    blurRadius = 2F,
                                )
                            ).typography.h1
                        )
                    }
                },
                onClick = { },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Colors.redQuar),
                contentPadding = ButtonDefaults.ContentPadding,
                enabled = true,
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Divider(color = Colors.dimGray, thickness = 2.dp, modifier = Modifier
                .width(100.dp)
                .padding(start = 40.dp))
            CustomTextView().apply {
                customTextView(
                    text = LoginConstants.View.LABEL_OR_CONNECT_WITH,
                    upperCase = true,
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp)
                        .align(Alignment.CenterVertically),
                    color = Colors.dimGrayTwo,
                    textStyle = FontsTheme(
                        shadow = Shadow(
                            color = Colors.dimGrayTwo,
                            blurRadius = 2F,
                        )
                    ).typography.h1
                )
            }
            Divider(color = Colors.dimGray, thickness = 2.dp, modifier = Modifier
                .width(100.dp)
                .padding(end = 40.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CustomCircularButton().apply {customCircularButton(
                content = {
                    CustomTextView().apply{customTextView(
                        text = LoginConstants.View.BUTTON_LABEL_FACEBOOK, upperCase = true,
                        modifier =
                        Modifier.padding(12.dp), color = Colors.whitePrimary, textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h1
                    )
                }},
                onClick = { },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Colors.bluePrimary),
                contentPadding = ButtonDefaults.ContentPadding,
                enabled = true,
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .width(190.dp)
                    .height(60.dp)
                    .padding(start = 32.dp)
            )}

            Spacer(modifier = Modifier.width(20.dp))

            CustomCircularButton().apply {customCircularButton(
                content = {
                    CustomTextView().apply{customTextView(
                        text = LoginConstants.View.BUTTON_LABEL_GOOGLE, upperCase = true,
                        modifier =
                        Modifier.padding(12.dp), color = Colors.whitePrimary, textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h1
                    )
                }},
                onClick = { },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Colors.redSecundary),
                contentPadding = ButtonDefaults.ContentPadding,
                enabled = true,
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .width(180.dp)
                    .height(60.dp)
                    .padding(end = 32.dp)
            )}
        }
    }
}
