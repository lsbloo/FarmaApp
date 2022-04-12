package com.farma.poc.features.splash.presentation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.utils.composables.ComposableUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenSplash(splashViewModel: SplashViewModel, context: Context) {

    ComposableUtils.getSystemUiControllerWithColorStatusBarAndDarkIcon(
        color = Colors.colorBackGroundPrimaryTheme,
        darkIcons = true
    )

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
        viewImageLogoApp()
        splashViewModel.redirectToOnboarding()
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun viewImageLogoApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_farma), contentDescription = "",
            modifier = Modifier
                .width(245.dp)
                .height(264.dp)
        )
    }

}