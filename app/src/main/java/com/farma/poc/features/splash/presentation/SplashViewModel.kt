package com.farma.poc.features.splash.presentation

import android.os.Handler
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.features.splash.constants.SplashConstants.Companion.TIME_TO_REDIRECT_ONBOARDING

class SplashViewModel : BaseViewModel() {

    fun redirectToOnboarding() {
        Handler().postDelayed(
            { this.routerNavigation?.navigateTo(RouterNavigationEnum.ONBOARDING) }
        ,TIME_TO_REDIRECT_ONBOARDING)
    }
}