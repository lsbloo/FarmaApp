package com.farma.poc.features.singup.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.colors.Colors.blackBackGroundPrimaryTheme
import com.farma.poc.core.resources.colors.Colors.colorBackGroundPrimaryTheme
import com.farma.poc.core.resources.colors.Colors.redQuin
import com.farma.poc.core.resources.colors.Colors.whitePrimary
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor
import com.farma.poc.core.utils.components.*
import com.farma.poc.core.utils.composables.ComposableUtils


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenSingUp(context: Context, singUpViewModel: SingUpViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopBarDefault(
                backGroundColor = blackBackGroundPrimaryTheme,
                R.drawable.ic_arrow_back,
                R.drawable.logo_farma,
                context.getString(R.string.app_name),
                onCLickImageLeft = {
                    singUpViewModel.backToNavigate()
                }
            ).apply {
                setupTopBar()
            }
        },
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
                bodyContent(singUpViewModel, context, scaffoldState = scaffoldState)
            }
        })

    ComposableUtils.setBackHandler(
        enable = false,
        onClickBackPressed = {
            singUpViewModel.backToNavigate()
        }
    )

}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun bodyContent(singUpViewModel: SingUpViewModel, context: Context, scaffoldState: ScaffoldState) {

    var nameState by remember {
        mutableStateOf("")
    }

    var emailState by remember {
        mutableStateOf("")
    }
    var cpfState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
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
        Spacer(modifier = Modifier.height(20.dp))
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
                text = context.getString(R.string.label_create_account),
                upperCase = true,
                color = Colors.whiteSecundary,
                modifier = Modifier.padding(start = 40.dp, end = 40.dp),
                textStyle =
                FontsTheme(
                    shadow = Shadow(
                        color = whitePrimary,
                    )
                ).typography.h1,
                fontSize = TextUnit(48F, TextUnitType(48)),
                letterSpacing = 4.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        customTextField(modifier = Modifier
            .padding(start = 40.dp, end = 20.dp)
            .fillMaxWidth()
            .padding(
                end = 10.dp
            ),
            isPassword = false,
            state = nameState,
            onValueChange = { newValue ->
                nameState = newValue
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.label_name),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.4f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = whitePrimary,
                            )
                        ).typography.h3,
                        textAlign = TextAlign.Left
                    )
                }
            })

        Spacer(modifier = Modifier.height(8.dp))
        customTextField(modifier = Modifier
            .padding(start = 40.dp, end = 20.dp)
            .fillMaxWidth()
            .padding(
                end = 10.dp
            ),
            isPassword = false,
            state = emailState,
            onValueChange = { newValue ->
                emailState = newValue
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.label_email),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.4f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = whitePrimary,
                            )
                        ).typography.h3,
                        textAlign = TextAlign.Left
                    )
                }
            })
        Spacer(modifier = Modifier.height(8.dp))
        customTextField(modifier = Modifier
            .padding(start = 40.dp, end = 20.dp)
            .fillMaxWidth()
            .padding(
                end = 10.dp
            ),
            isPassword = false,
            state = cpfState,
            onValueChange = { newValue ->
                cpfState = newValue
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.label_cpf),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.4f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = whitePrimary,
                            )
                        ).typography.h3,
                        textAlign = TextAlign.Left
                    )
                }
            })
        Spacer(modifier = Modifier.height(8.dp))
        customTextField(modifier = Modifier
            .padding(start = 40.dp, end = 20.dp)
            .fillMaxWidth()
            .padding(
                end = 10.dp
            ),
            isPassword = false,
            state = passwordState,
            onValueChange = { newValue ->
                passwordState = newValue
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.label_password),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.4f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        textStyle =
                        FontsTheme(
                            shadow = Shadow(
                                color = whitePrimary,
                            )
                        ).typography.h3,
                        textAlign = TextAlign.Left
                    )
                }
            })
        Spacer(modifier = Modifier.height(20.dp))
        CustomCircularButton().apply {
            customCircularButton(
                content = {
                    CustomTextView().apply {
                        customTextView(
                            text = context.getString(R.string.label_create_account_second),
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
                            ).typography.h2
                        )
                    }
                },
                onClick = {

                },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Colors.redSecundary),
                contentPadding = ButtonDefaults.ContentPadding,
                enabled = true,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 32.dp, end = 32.dp)
                    .border(BorderStroke(1.dp, Colors.black), shape = RoundedCornerShape(6.dp))

            )
        }
    }

}