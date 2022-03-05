package com.farma.poc.login.presentation


import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.components.*
import com.farma.poc.core.utils.enums.DurationSnackBarEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenLogin(loginViewModel: LoginViewModel, context: Context) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {},
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(), content = {
            DefaultSnackBar(
                snackbarHostState = scaffoldState.snackbarHostState,
                modifier = Modifier,
                onDismiss = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                }
            ).apply {
                defaultSnackBar()
            }
            setupLoginScreen(loginViewModel, context, scaffoldState)
        })
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun setupLoginScreen(
    loginViewModel: LoginViewModel,
    context: Context,
    scaffoldState: ScaffoldState
) {
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
        bodyContent(loginViewModel, context, scaffoldState = scaffoldState)
    }

}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun bodyContent(loginViewModel: LoginViewModel, context: Context, scaffoldState: ScaffoldState) {

    val coroutineScope = rememberCoroutineScope()
    var loginText by remember {
        mutableStateOf("")
    }

    var passwordText by remember {
        mutableStateOf("")
    }

    loginViewModel.statusShowLoading.observeAsState().value?.let { showLoading ->
        if (showLoading) {
            CustomProgressButton().apply {
                customProgressButton()
            }
        }
    }

    loginViewModel.showErrorFeedBack.value.let { showStatus ->
        if (showStatus) {
            CustomErrorFeedBack(
                snackBarHostState = scaffoldState.snackbarHostState,
                coroutineScope = coroutineScope,
                message = "Não foi possivel realizar o login",
                actionLabel = "Fechar",
                durationSnackBar = DurationSnackBarEnum.SHORT,
                actionPerformed = {

                }
            ).apply {
                setupSnackBarError()
                loginViewModel.showErrorFeedBack.value = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_farm), contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_email),
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
            )
        }
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
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_password),
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
            )
        }
        customTextField(
            modifier = Modifier
                .padding(start = 40.dp, end = 20.dp)
                .fillMaxWidth()
                .padding(
                    end = 10.dp
                ),
            isPassword = loginViewModel.stateEyeLogin.value,
            passwordText,
            onValueChange = { newValue ->
                passwordText = newValue
            },
            trailingIcon = {
                IconButton(onClick = {
                    loginViewModel.apply {
                        changeStateEyeLogin(!stateEyeLogin.value)
                    }
                }) {
                    Icon(
                        painter = painterResource(id = loginViewModel.iconLogin),
                        contentDescription = ""
                    )
                }
            },
        )

        Spacer(modifier = Modifier.height(28.dp))
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_forgot_pass), upperCase = false,
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
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
        CustomCircularButton().apply {
            customCircularButton(
                content = {
                    CustomTextView().apply {
                        customTextView(
                            text = context.getString(R.string.button_login), upperCase = true,
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
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        loginViewModel.login()
                    }
                },
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
            Divider(
                color = Colors.dimGray, thickness = 2.dp, modifier = Modifier
                    .width(100.dp)
                    .padding(start = 40.dp)
            )
            CustomTextView().apply {
                customTextView(
                    text = context.getString(R.string.label_connect_with),
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
            Divider(
                color = Colors.dimGray, thickness = 2.dp, modifier = Modifier
                    .width(100.dp)
                    .padding(end = 40.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CustomCircularButton().apply {
                customCircularButton(
                    content = {
                        CustomTextView().apply {
                            customTextView(
                                text = context.getString(R.string.button_facebook),
                                upperCase = true,
                                modifier =
                                Modifier.padding(12.dp),
                                color = Colors.whitePrimary,
                                textStyle =
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
                    colors = ButtonDefaults.buttonColors(backgroundColor = Colors.bluePrimary),
                    contentPadding = ButtonDefaults.ContentPadding,
                    enabled = true,
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .width(190.dp)
                        .height(60.dp)
                        .padding(start = 32.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            CustomCircularButton().apply {
                customCircularButton(
                    content = {
                        CustomTextView().apply {
                            customTextView(
                                text = context.getString(R.string.button_google), upperCase = true,
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
                    colors = ButtonDefaults.buttonColors(backgroundColor = Colors.redSecundary),
                    contentPadding = ButtonDefaults.ContentPadding,
                    enabled = true,
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp)
                        .padding(end = 32.dp)
                )
            }
        }
    }
}
