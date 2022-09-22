package com.farma.poc.featuresApp.compose.splash.presentation

import android.content.Context
import android.net.Uri
import android.os.Handler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.featuresApp.compose.splash.constants.SplashConstants.Companion.TIME_TO_REDIRECT_ONBOARDING

class SplashViewModel(context: Context) : BaseViewModel(context) {

    var visibilityImageLogo by mutableStateOf(false)
    var dataImage: Uri? = null

    fun redirectToOnboarding(onNavigateCalled: (() -> Unit)? = null) {
        Handler().postDelayed(
            {
                onNavigateCalled?.invoke()
                this.routerNavigation?.navigateTo(RouterNavigationEnum.ONBOARDING)
            }
        ,TIME_TO_REDIRECT_ONBOARDING)
    }

    fun setImageSplashScreen(uri : Uri) {
        visibilityImageLogo = true
        dataImage = uri
    }


    companion object {
        const val TIME_ANIMATION_CROSS_FADE_TWEEN = 2500
    }
}