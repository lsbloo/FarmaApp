package com.farma.poc.features.settings.home.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.components.CustomCircularButton
import com.farma.poc.core.utils.components.CustomItemsSettings.Companion.setupItemsSettingsScreen
import com.farma.poc.core.utils.components.CustomTextView
import com.farma.poc.core.utils.components.DefaultSnackBar
import com.farma.poc.core.utils.components.TopBarDefault
import com.farma.poc.features.settings.home.data.model.GetSettingsDTO

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalUnitApi
@Composable
fun settingsComponent(context: Context, settingsViewModel: SettingsViewModel) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopBarDefault(
                backGroundColor = Colors.redQuin,
                R.drawable.ic_arrow_back,
                textTopBar = context.getString(R.string.label_screen_settings),
                onCLickImageLeft = {
                    settingsViewModel.redirectToHome()
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
                setupBodyContent(context = context, settingsViewModel = settingsViewModel)
            }
        })
    settingsViewModel.getStatusShowBiometric()
}

@ExperimentalUnitApi
@Composable
fun setupBodyContent(context: Context, settingsViewModel: SettingsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = object : Arrangement.Vertical {
            var currentOffset = 0
            override fun Density.arrange(totalSize: Int, sizes: IntArray, outPositions: IntArray) {
                sizes.forEachIndexed { index, size ->
                    if (size == sizes.lastIndex) {
                        outPositions[index] = totalSize - (size)
                    } else {
                        outPositions[index] = currentOffset
                        currentOffset += size
                    }

                }
            }

        }
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        CustomTextView().customTextView(
            text = context.getString(R.string.label_my_profile),
            modifier = Modifier,
            color = Colors.whitePrimary,
            textStyle = FontsTheme(
                shadow = Shadow(
                    color = Colors.whitePrimary,
                    blurRadius = 2F,
                )
            ).typography.h2
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_male_placeholder_profile),
                contentDescription = "",
                modifier = Modifier
                    .height(75.dp)
                    .width(75.dp)
                    .background(
                        shape = RoundedCornerShape(24.dp),
                        color = Color.Unspecified
                    )
            )
            Spacer(modifier = Modifier.width(12.dp))
            settingsViewModel.datasetScreenSettings.value.nameUser?.let {
                CustomTextView().customTextView(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    color = Colors.whitePrimary,
                    textStyle = FontsTheme(
                        shadow = Shadow(
                            color = Colors.whitePrimary,
                            blurRadius = 2F,
                        )
                    ).typography.h3
                )
            }
        }
        CustomTextView().customTextView(
            text = context.getString(R.string.label_screen_settings),
            modifier = Modifier,
            color = Colors.whitePrimary,
            textStyle = FontsTheme(
                shadow = Shadow(
                    color = Colors.whitePrimary,
                    blurRadius = 2F,
                )
            ).typography.h2
        )
        Spacer(modifier = Modifier.height(24.dp))
        setupItemsSettingsScreen(
            data = settingsViewModel.datasetScreenSettings.value,
            onClickAddress = {
                Toast.makeText(
                    context,
                    context.getString(R.string.function_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
                settingsViewModel.redirectAddress()
            },
            onClickAsks = {
                Toast.makeText(
                    context,
                    context.getString(R.string.function_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
                settingsViewModel.redirectToFaq()
            },
            onClickCloseAccount = {
                Toast.makeText(
                    context,
                    context.getString(R.string.function_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
            },
            onCLickInfoUser = {
                Toast.makeText(
                    context,
                    context.getString(R.string.function_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
                settingsViewModel.redirectToDataUser()
            },
            onCLickMethodPayment = {
                Toast.makeText(
                    context,
                    context.getString(R.string.function_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
                settingsViewModel.redirectMethodsPayment()
            },
            onCLickOrder = {
                Toast.makeText(
                    context,
                    context.getString(R.string.function_not_implemented),
                    Toast.LENGTH_SHORT
                ).show()
                settingsViewModel.redirectToOrder()
            },
            onCheckedValueChangeBiometric = { valueChange ->
                settingsViewModel.activateBiometric(valueChange)
            },
            initStateSwitchToggleBiometric = settingsViewModel.hasFlagShowBiometric.value
        )

        Spacer(modifier = Modifier.height(56.dp))
        CustomCircularButton().apply {
            customCircularButton(
                content = {
                    CustomTextView().apply {
                        settingsViewModel.datasetScreenSettings.value.labelButtonLogout?.let {
                            customTextView(
                                text = it,
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
                    }
                },
                onClick = {
                    settingsViewModel.logout()
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
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 12.dp, end = 12.dp)

            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        settingsViewModel.datasetScreenSettings.value.labelVersionApp?.let {
            CustomTextView().customTextView(
                text = it,
                modifier = Modifier.fillMaxSize(),
                color = Colors.whitePrimary,
                textStyle = FontsTheme(
                    shadow = Shadow(
                        color = Colors.whitePrimary,
                        blurRadius = 2F,
                    )
                ).typography.h3
            )
        }

        settingsViewModel.getDataScreen()
    }
}