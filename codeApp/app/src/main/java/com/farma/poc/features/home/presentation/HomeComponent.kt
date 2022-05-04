package com.farma.poc.features.home.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor
import com.farma.poc.core.utils.components.CustomTopBar
import com.farma.poc.core.utils.components.DefaultSnackBar
import com.farma.poc.core.utils.composables.ComposableUtils


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalUnitApi
@Composable
fun homeComponent(context: Context, homeViewModel: HomeViewModel) {

    val scaffoldState = rememberScaffoldState()

    ComposableUtils.setSystemUiControllerWithColorStatusBar(
        color = Colors.redQuin
    )

    Scaffold(
        topBar = {
            CustomTopBar(
                backGroundColor = Colors.redQuin,
                imageSupportText = R.drawable.logo_farma,
                textTopBar = context.getString(R.string.app_name),
                imageCart = R.drawable.ic_cart,
                labelSearchTextField = context.getString(R.string.label_field_search),
                iconLabelSearchField = R.drawable.ic_search,
                hasSearchField = true,
                colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor()
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
                bodyContent(homeViewModel, context, scaffoldState = scaffoldState)
            }
        })

    ComposableUtils.setBackHandler(
        enable = false,
        onClickBackPressed = {

        }
    )
}

@Composable
fun bodyContent(homeViewModel: HomeViewModel, context: Context, scaffoldState: ScaffoldState) {

}