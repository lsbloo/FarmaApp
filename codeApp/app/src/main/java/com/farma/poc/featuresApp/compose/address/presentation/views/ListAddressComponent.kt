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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.utils.components.ButtonRight
import com.farma.poc.core.utils.components.CustomAddressesView
import com.farma.poc.core.utils.components.DefaultSnackBar
import com.farma.poc.core.utils.components.TopBarDefault
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.featuresApp.compose.address.presentation.AddressViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenListAddress(context: Context, addressViewModel: AddressViewModel) {
    ComposableUtils.setSystemUiControllerWithColorStatusBar(
        color = Colors.redQuin
    )
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopBarDefault(
                backGroundColor = Colors.redQuin,
                R.drawable.ic_arrow_back,
                textTopBar = context.getString(R.string.list_address),
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
                bodyContentListAddress(addressViewModel, context, scaffoldState = scaffoldState)
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
fun bodyContentListAddress(
    addressViewModel: AddressViewModel,
    context: Context,
    scaffoldState: ScaffoldState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(addressViewModel.getAllAddresses()) { index, item ->
            CustomAddressesView(
                address = item,
                index = index,
                onClickAddress = { address ->
                    addressViewModel.redirectToDetailAddressWithPersistAddressTemp(address)
                },
                onClickDeleteAddress = {
                    addressViewModel.deleteAddress(it)
                }
            ).setup()
        }
    }
}