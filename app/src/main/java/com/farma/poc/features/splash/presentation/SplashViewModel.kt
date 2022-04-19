package com.farma.poc.features.splash.presentation

import android.content.Context
import android.opengl.Visibility
import android.os.Handler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.features.splash.constants.SplashConstants.Companion.TIME_TO_REDIRECT_ONBOARDING

class SplashViewModel(context: Context) : BaseViewModel(context) {

    var visibilityImageLogo by mutableStateOf(false)

    fun redirectToOnboarding(onNavigateCalled: (() -> Unit)? = null) {
        Handler().postDelayed(
            {
                onNavigateCalled?.invoke()
                this.routerNavigation?.navigateTo(RouterNavigationEnum.ONBOARDING)
            }
        ,TIME_TO_REDIRECT_ONBOARDING)
    }

    fun changeVisibilityImageLogo(visibility: Boolean){
        visibilityImageLogo = visibility
    }


    companion object {
        const val TIME_ANIMATION_CROSS_FADE_TWEEN = 1500
    }
}