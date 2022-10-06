package com.farma.poc.featuresApp.compose.address.presentation.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.components.*
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.address.presentation.AddressViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenAddressDetail(context: Context, addressViewModel: AddressViewModel) {
    ComposableUtils.setSystemUiControllerWithColorStatusBar(
        color = Colors.redQuin
    )
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopBarDefault(
                backGroundColor = Colors.redQuin,
                R.drawable.ic_arrow_back,
                textTopBar = context.getString(R.string.address_detail),
                onCLickImageLeft = {
                    addressViewModel.redirectToSettings()
                },
                buttonRight = ButtonRight(R.drawable.ic_more1, onClick = {
                    addressViewModel.redirectToCreateAddress()
                })
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
                bodyContentAddressDetail(addressViewModel, context, scaffoldState = scaffoldState)
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
fun bodyContentAddressDetail(
    addressViewModel: AddressViewModel,
    context: Context,
    scaffoldState: ScaffoldState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextView().apply {
                customTextView(
                    text = "Endereço Principal",
                    upperCase = false,
                    color = Colors.whitePrimary,
                    modifier = Modifier.padding(top = 18.dp),
                    textStyle =
                    FontsTheme(
                        shadow = Shadow(
                            color = Colors.whitePrimary,
                        )
                    ).typography.h3,
                    fontSize = TextUnit(20F, TextUnitType(20))
                )
            }
            Switch(
                modifier = Modifier.width(50.dp),
                checked = addressViewModel.stateCheckedSwitch.value,
                onCheckedChange = {
                    addressViewModel.setAddressPrincipal(
                        addressDTO = addressViewModel.detailAddressSelectedTmp.value,
                        it
                    )
                })
        }
    }

}

