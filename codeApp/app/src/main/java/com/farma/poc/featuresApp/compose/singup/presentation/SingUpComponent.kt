package com.farma.poc.featuresApp.compose.singup.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.colors.Colors.redQuin
import com.farma.poc.core.resources.colors.Colors.whitePrimary
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor
import com.farma.poc.core.utils.components.*
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.featuresApp.compose.singup.constants.SingUpConstants.DIALOG.LABEL_CONFIRM_BUTTON
import com.farma.poc.featuresApp.compose.singup.constants.SingUpConstants.DIALOG.LABEL_DISMISS_BUTTON
import com.farma.poc.featuresApp.compose.singup.constants.SingUpConstants.DIALOG.LABEL_TEXT_DESCRIPTION
import com.farma.poc.featuresApp.compose.singup.constants.SingUpConstants.DIALOG.TITLE_SINGUP


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenSingUp(context: Context, singUpViewModel: SingUpViewModel) {
    ComposableUtils.setSystemUiControllerWithColorStatusBar(
        color = Colors.redQuin
    )
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopBarDefault(
                backGroundColor = redQuin,
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
                        )
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

    val coroutineScope = rememberCoroutineScope()

    with(singUpViewModel) {
        this.name.value = nameState
        this.cpf.value = cpfState
        this.email.value = emailState
        this.password.value = passwordState



        showErrorFeedBackEmptyCredentials.value.let {
            ComposableUtils.showSnackBarError(scaffoldState = scaffoldState.snackbarHostState,
                coroutineScope = coroutineScope, enable = it,
                message = context.getString(R.string.error_login_labels),
                actionLabel = context.getString(R.string.label_closed_button), onApply = {
                    showErrorFeedBackEmptyCredentials.value = false
                })
        }

        showErrorFeedBack.value.let {
            ComposableUtils.showSnackBarError(scaffoldState = scaffoldState.snackbarHostState,
                coroutineScope = coroutineScope, enable = it,
                message = context.getString(R.string.error_singup_request),
                actionLabel = context.getString(R.string.label_closed_button), onApply = {
                    showErrorFeedBack.value = false
                })
        }

        shouldLoading.value.let { showLoading ->
            if (showLoading) {
                CustomProgressButton().apply {
                    customProgressButton()
                }
            }
        }

        var (showDialog, setShowDialog) = remember { mutableStateOf(false)}
        shouldDialogSuccessfulRegister.value.let {
            if (it) {
                CustomAlertDialog(
                    TITLE_SINGUP,
                    LABEL_CONFIRM_BUTTON,
                    LABEL_DISMISS_BUTTON,
                    LABEL_TEXT_DESCRIPTION,
                    onClickConfirmButton = {
                        shouldDialogSuccessfulRegister.value = false
                        showDialog = false
                        singUpViewModel.backToNavigate()
                    },
                    onClickDismissButton = {
                        shouldDialogSuccessfulRegister.value = false
                        showDialog = false
                    }
                , onDismiss = {}).apply { setupDialog() }
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
        Image(
            painter = painterResource(id = R.drawable.logo_farm), contentDescription = "",
            modifier = Modifier
                .width(162.dp)
                .height(148.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
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
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding(start = 40.dp, end = 20.dp)) {
            customTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 10.dp
                ),
                isPassword = false,
                state = nameState,
                onValueChange = { newValue ->
                    nameState = newValue
                    if (newValue.isEmpty()) {
                        singUpViewModel.isErrorName.value = false
                        singUpViewModel.messageErrorNameValidator.value = ""
                    }
                },
                isError = singUpViewModel.isErrorName.value,
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

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = singUpViewModel.messageErrorNameValidator.value,
                color = Colors.redPrimary,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(all = 1.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding(start = 40.dp, end = 20.dp)) {
            customTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 10.dp
                ),
                isPassword = false,
                state = emailState,
                onValueChange = { newValue ->
                    emailState = newValue
                    if (newValue.isEmpty()) {
                        singUpViewModel.isErrorEmail.value = false
                        singUpViewModel.messageErrorEmailValidator.value = ""
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = singUpViewModel.isErrorEmail.value,
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
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = singUpViewModel.messageErrorEmailValidator.value,
                color = Colors.redPrimary,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(all = 1.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding(start = 40.dp, end = 20.dp)) {
            customTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 10.dp
                ),
                isPassword = false,
                state = cpfState,
                onValueChange = { newValue ->
                    cpfState = newValue
                    if (newValue.isEmpty()) {
                        singUpViewModel.isErrorCpf.value = false
                        singUpViewModel.messageErrorCpfValidator.value = ""
                    }
                },
                isError = singUpViewModel.isErrorCpf.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = singUpViewModel.messageErrorCpfValidator.value,
                color = Colors.redPrimary,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(all = 1.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .padding(start = 40.dp, end = 20.dp)
        ) {
            customTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 10.dp
                ),
                isPassword = false,
                state = passwordState,
                onValueChange = { newValue ->
                    passwordState = newValue
                    if (newValue.isEmpty()) {
                        singUpViewModel.isErrorPassword.value = false
                        singUpViewModel.messageErrorPasswordValidator.value = ""
                    }
                },
                isError = singUpViewModel.isErrorPassword.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = singUpViewModel.messageErrorPasswordValidator.value,
                color = Colors.redPrimary,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(all = 1.dp)
            )
        }
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
                    singUpViewModel.registerAccount()
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