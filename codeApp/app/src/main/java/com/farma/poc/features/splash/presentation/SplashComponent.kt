package com.farma.poc.features.splash.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
import coil.compose.rememberAsyncImagePainter
import com.farma.poc.R
import com.farma.poc.core.firebase.downloadUriImageFirebase
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.utils.composables.ComposableUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun screenSplash(splashViewModel: SplashViewModel, context: Context) {

    ComposableUtils.setSystemUiControllerWithColorStatusBarAndDarkIcon(
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
        viewImageLogoApp(splashViewModel)
        splashViewModel.redirectToOnboarding(
            onNavigateCalled = {}
        )
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun viewImageLogoApp(splashViewModel: SplashViewModel) {
    downloadUriImageFirebase(onSuccess = {
        splashViewModel.setImageSplashScreen(it)
    }, onFailure = {})
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Crossfade(
            targetState = splashViewModel.visibilityImageLogo,
            animationSpec = tween(SplashViewModel.TIME_ANIMATION_CROSS_FADE_TWEEN)
        ) { visibility ->
            AnimatedVisibility(visible = visibility) {
                Image(
                    painter = rememberAsyncImagePainter(splashViewModel.dataImage,),
                    contentDescription = "",
                    modifier = Modifier
                        .width(245.dp)
                        .height(264.dp)
                )
            }
        }
    }

}