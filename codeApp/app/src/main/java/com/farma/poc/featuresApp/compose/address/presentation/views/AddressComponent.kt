package com.farma.poc.featuresApp.compose.address.presentation.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor
import com.farma.poc.core.utils.components.*
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.featuresApp.compose.address.presentation.AddressViewModel
import com.farma.poc.featuresApp.compose.singup.presentation.bodyContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenAddress(context: Context, addressViewModel: AddressViewModel) {
    ComposableUtils.setSystemUiControllerWithColorStatusBar(
        color = Colors.redQuin
    )
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopBarDefault(
                backGroundColor = Colors.redQuin,
                R.drawable.ic_arrow_back,
                textTopBar = context.getString(R.string.add_address),
                onCLickImageLeft = {
                    addressViewModel.redirectToSettings()
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
                bodyContent(addressViewModel, context, scaffoldState = scaffoldState)
            }
        })

    ComposableUtils.setBackHandler(
        enable = false,
        onClickBackPressed = {
            addressViewModel.redirectToSettings()
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun bodyContent(
    addressViewModel: AddressViewModel,
    context: Context,
    scaffoldState: ScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()

    var edittextStreet by remember {
        mutableStateOf("")
    }

    var edittextDistrict by remember {
        mutableStateOf("")
    }

    var edittextNumber by remember {
        mutableStateOf("")
    }

    var edittextCity by remember {
        mutableStateOf("")
    }

    var edittextDescription by remember {
        mutableStateOf("")
    }


    with(addressViewModel) {
        street.value = edittextStreet
        district.value = edittextDistrict
        number.value = edittextNumber
        city.value = edittextCity
        description.value = edittextDescription



        showLoadingPage.value.let { showLoading ->
            if (showLoading) {
                CustomProgressButton().apply {
                    customProgressButton()
                }
            }
        }

        showToastSuccess.value.let {
            if (it) {
                edittextStreet = ""
                edittextDistrict = ""
                edittextNumber = ""
                edittextCity = ""
                edittextDescription = ""
                showToast(context.getString(R.string.address_created_success))
                redirectToHome()
                showToastSuccess.value = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = remember {
            object : Arrangement.Vertical {
                override fun Density.arrange(
                    totalSize: Int,
                    sizes: IntArray,
                    outPositions: IntArray
                ) {
                    var currentOffeset = 0
                    sizes.forEachIndexed { index, size ->
                        if (index == sizes.lastIndex) {
                            outPositions[index] = totalSize - (size + 80)

                        } else {
                            outPositions[index] = currentOffeset
                            currentOffeset += size
                        }

                    }
                }

            }
        }
    ) {

        Spacer(modifier = Modifier.height(18.dp))
        CustomTextView().apply {
            customTextView(
                text = context.getString(R.string.label_title_address_page),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 8.dp, end = 8.dp),
                color = Colors.whitePrimary,
                textStyle = FontsTheme(
                    shadow = Shadow(
                        color = Colors.whitePrimary,
                        blurRadius = 2F,
                    )
                ).typography.h4
            )
        }
        Divider(
            color = Colors.whiteSecundary, thickness = 2.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        customTextField(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
            isPassword = false,
            state = edittextStreet,
            isError = addressViewModel.isErrorStreet.value,
            onValueChange = { newValue ->
                edittextStreet = newValue
                if (newValue.isNullOrEmpty()) {
                    addressViewModel.messageErrorStreet.value = ""
                    addressViewModel.isErrorStreet.value = false
                }
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.placeholder_address_street_page),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 1.dp),
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
        Text(
            text = addressViewModel.messageErrorStreet.value,
            color = Colors.redPrimary,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        customTextField(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
            isPassword = false,
            state = edittextDistrict,
            isError = addressViewModel.isErrorDistrict.value,
            onValueChange = { newValue ->
                edittextDistrict = newValue
                if (newValue.isNullOrEmpty()) {
                    addressViewModel.isErrorDistrict.value = false
                    addressViewModel.messageErrorDistrict.value = ""
                }
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.placeholder_address_district_page),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 1.dp),
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
        Text(
            text = addressViewModel.messageErrorDistrict.value,
            color = Colors.redPrimary,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        customTextField(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
            isPassword = false,
            state = edittextNumber,
            isError = addressViewModel.isErrorNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { newValue ->
                edittextNumber = newValue
                if (newValue.isNullOrEmpty()) {
                    addressViewModel.isErrorNumber.value = false
                    addressViewModel.messageErrorNumber.value = ""
                }
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.placeholder_address_number_page),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 1.dp),
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
        Text(
            text = addressViewModel.messageErrorNumber.value,
            color = Colors.redPrimary,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        customTextField(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
            isPassword = false,
            state = edittextCity,
            isError = addressViewModel.isErrorCity.value,
            onValueChange = { newValue ->
                edittextCity = newValue
                if (newValue.isNullOrEmpty()) {
                    addressViewModel.isErrorCity.value = false
                    addressViewModel.messageErrorCity.value = ""
                }
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.placeholder_address_city_page),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 1.dp),
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
        Text(
            text = addressViewModel.messageErrorCity.value,
            color = Colors.redPrimary,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        customTextField(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
            isPassword = false,
            state = edittextDescription,
            isError = false,
            onValueChange = { newValue ->
                edittextDescription = newValue
                if (newValue.isNullOrEmpty()) { }
            },
            colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor(),
            placeholder = {
                CustomTextView().apply {
                    customTextView(
                        text = context.getString(R.string.placeholder_address_description_page),
                        upperCase = false,
                        color = Colors.whiteSecundary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 1.dp),
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


        CustomCircularButton().apply {
            customCircularButton(
                content = {
                    CustomTextView().apply {
                        customTextView(
                            text = context.getString(R.string.label_button_add),
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
                    addressViewModel.createAddressUser()
                },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Colors.greenPrimary),
                contentPadding = ButtonDefaults.ContentPadding,
                enabled = true,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 32.dp, end = 32.dp)

            )
        }
    }
}