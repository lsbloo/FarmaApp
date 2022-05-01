package com.farma.poc.features.login.presentation


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.farma.poc.MainActivity
import com.farma.poc.R
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor.Companion.getDefaultTextFieldOutlinedColor
import com.farma.poc.core.utils.components.*
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.core.utils.composables.ComposableUtils.Companion.showSnackBarError
import com.farma.poc.core.utils.enums.DurationSnackBarEnum
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenLogin(loginViewModel: LoginViewModel, context: Context, activity: MainActivity? = null) {
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

    ComposableUtils.setBackHandler(
        enable = true,
        onClickBackPressed = {
            activity?.finish()
        }
    )
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
                        Colors.colorBackGroundPrimaryTheme,
                        Colors.colorBackGroundPrimaryTheme
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

    with(loginViewModel) {
        statusShowLoading.observeAsState().value?.let { showLoading ->
            if (showLoading) {
                CustomProgressButton().apply {
                    customProgressButton()
                }
            }
        }

        this.passwordText.value = passwordText
        this.emailText.value = loginText


        showErrorFeedBack.value.let { showStatus ->
            showSnackBarError(scaffoldState = scaffoldState.snackbarHostState,
                coroutineScope = coroutineScope, enable = showStatus,
                message = context.getString(R.string.error_login_request),
                actionLabel = context.getString(R.string.label_closed_button), onApply = {
                    loginViewModel.showErrorFeedBack.value = false
                })
        }
        showErrorFeedBackEmptyCredentials.value.let {
            showSnackBarError(scaffoldState = scaffoldState.snackbarHostState,
                coroutineScope = coroutineScope, enable = it,
                message = context.getString(R.string.error_login_labels),
                actionLabel = context.getString(R.string.label_closed_button), onApply = {
                    showErrorFeedBackEmptyCredentials.value = false
                })
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
                .width(162.dp)
                .height(148.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_email),
                upperCase = false,
                color = Colors.whiteSecundary,
                modifier = Modifier.padding(start = 40.dp, end = 40.dp),
                textStyle =
                FontsTheme(
                    shadow = Shadow(
                        color = Colors.whitePrimary,
                    )
                ).typography.h2,
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
            state = loginText,
            onValueChange = { newValue ->
                loginText = newValue
            }, colorsTextField = getDefaultTextFieldOutlinedColor(), placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.label_placeholder_email_login),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.4f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                            )
                        ).typography.h3,
                        textAlign = TextAlign.Left
                    )
                }
            })

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_password),
                upperCase = false,
                color = Colors.whiteSecundary,
                modifier = Modifier.padding(start = 40.dp, end = 40.dp),
                textStyle =
                FontsTheme(
                    shadow = Shadow(
                        color = Colors.whitePrimary
                    )
                ).typography.h2,
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
            isPassword = true,
            changePasswordTransformation = loginViewModel.stateEyeLogin.value,
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
            }, colorsTextField = getDefaultTextFieldOutlinedColor(), placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.label_placeholder_password_login),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.4f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                            )
                        ).typography.h3,
                        textAlign = TextAlign.Left
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_forgot_pass),
                upperCase = false,
                modifier =
                Modifier
                    .padding(start = 40.dp, end = 28.dp)
                    .align(Alignment.End), color = Colors.whiteSecundary, textStyle =
                FontsTheme(
                    shadow = Shadow(
                        color = Colors.whitePrimary
                    )
                ).typography.h2,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_create_account),
                upperCase = false,
                modifier =
                Modifier
                    .padding(start = 40.dp, end = 28.dp)
                    .align(Alignment.End)
                    .clickable {
                        loginViewModel.redirectToSingUp()
                    }, color = Colors.whiteSecundary, textStyle =
                FontsTheme(
                    shadow = Shadow(
                        color = Colors.whitePrimary
                    )
                ).typography.h2,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
        CustomCircularButton().apply {
            customCircularButton(
                content = {
                    CustomTextView().apply {
                        customTextView(
                            text = context.getString(R.string.button_login), upperCase = false,
                            modifier =
                            Modifier.padding(12.dp), color = Colors.whitePrimary, textStyle =
                            FontsTheme(
                                shadow = Shadow(
                                    color = Colors.whitePrimary,
                                    blurRadius = 2F,
                                )
                            ).typography.h2
                        )
                    }
                },
                onClick = {
                    loginViewModel.login()
                },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Colors.redQuar),
                contentPadding = ButtonDefaults.ContentPadding,
                enabled = true,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Divider(
                color = Colors.whiteSecundary, thickness = 2.dp, modifier = Modifier
                    .width(100.dp)
                    .padding(start = 40.dp, top = 8.dp)
            )
            CustomTextView().apply {
                customTextView(
                    text = context.getString(R.string.label_connect_with),
                    upperCase = true,
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp),
                    color = Colors.whiteSecundary,
                    textStyle = FontsTheme(
                        shadow = Shadow(
                            color = Colors.whitePrimary,
                        )
                    ).typography.h3,
                    fontSize = 14.sp
                )
            }
            Divider(
                color = Colors.whiteSecundary, thickness = 2.dp, modifier = Modifier
                    .width(100.dp)
                    .padding(end = 20.dp, top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CustomCircularButton().apply {
                customCircularButton(
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_new_facebook),
                            contentDescription = "",
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                        )
                    },
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.function_not_implemented),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Colors.bluePrimary),
                    contentPadding = ButtonDefaults.ContentPadding,
                    enabled = true,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .width(190.dp)
                        .height(40.dp)
                        .padding(start = 32.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            CustomCircularButton().apply {
                customCircularButton(
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_new_google),
                            contentDescription = "",
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                        )
                    },
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.function_not_implemented),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Colors.black),
                    contentPadding = ButtonDefaults.ContentPadding,
                    enabled = true,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .width(180.dp)
                        .height(40.dp)
                        .padding(end = 32.dp),
                )
            }
        }
    }
}

