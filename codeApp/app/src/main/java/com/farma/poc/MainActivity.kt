package com.farma.poc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.farma.poc.core.animation.TransitionComponents
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.navigation.RouterNavigationManager
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.colors.Colors.colorBackGroundPrimaryTheme
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.core.utils.composables.ComposableUtils.Companion.setSystemUiControllerWithColorStatusBar
import com.farma.poc.features.home.presentation.HomeViewModel
import com.farma.poc.features.onboarding.presentation.OnboardingViewModel
import com.farma.poc.features.onboarding.presentation.setupOnboardingScreen
import com.farma.poc.features.splash.presentation.SplashViewModel
import com.farma.poc.features.splash.presentation.screenSplash
import com.farma.poc.features.home.presentation.homeComponent
import com.farma.poc.features.login.presentation.LoginViewModel
import com.farma.poc.features.login.presentation.screenLogin
import com.farma.poc.features.singup.presentation.SingUpViewModel
import com.farma.poc.features.singup.presentation.screenSingUp
import com.farma.poc.ui.theme.FarmaAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    companion object {
        private const val DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING = 1000
    }

    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setSystemUiControllerWithColorStatusBar(color = colorBackGroundPrimaryTheme)
            FarmaAppTheme {
                // A surface container using the 'background' color from the theme
                val loginViewModel = getViewModel<LoginViewModel>()
                val splashViewModel = getViewModel<SplashViewModel>()
                val onboardingViewModel = getViewModel<OnboardingViewModel>()
                val singUpViewModel = getViewModel<SingUpViewModel>()
                val homeViewModel = getViewModel<HomeViewModel>()

                Surface(color = colorBackGroundPrimaryTheme) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = RouterNavigationEnum.SPLASH.name
                    ) {
                        val routerNavigationManager = RouterNavigationManager(navController, this)
                        splashViewModel.setNavigation(routerNavigationManager)
                        loginViewModel.setNavigation(routerNavigationManager)
                        onboardingViewModel.setNavigation(routerNavigationManager)
                        singUpViewModel.setNavigation(routerNavigationManager)

                        TransitionComponents(
                            RouterNavigationEnum.SPLASH,
                            RouterNavigationEnum.ONBOARDING,
                            DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING
                        )
                            .setupComponentWithAnimation(this, onCallComponentScreen = {
                                screenSplash(
                                    splashViewModel = splashViewModel,
                                    context = this@MainActivity
                                )
                            })

                        TransitionComponents(
                            RouterNavigationEnum.ONBOARDING,
                            RouterNavigationEnum.LOGIN,
                            DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING
                        )
                            .setupComponentWithAnimation(this, onCallComponentScreen = {
                                setupOnboardingScreen(
                                    onboardingViewModel = onboardingViewModel,
                                    context = this@MainActivity
                                )
                            })

                        TransitionComponents(
                            RouterNavigationEnum.LOGIN,
                            RouterNavigationEnum.HOME,
                            DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING
                        )
                            .setupComponentWithAnimation(this, onCallComponentScreen = {
                                screenLogin(
                                    loginViewModel,
                                    this@MainActivity,
                                    this@MainActivity
                                )
                            })


                        TransitionComponents(
                            RouterNavigationEnum.HOME,
                            RouterNavigationEnum.SINGUP,
                            DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING
                        ).setupComponentWithAnimation(
                            this, onCallComponentScreen = {
                                homeComponent(
                                    context = this@MainActivity,
                                    homeViewModel = homeViewModel)
                            }
                        )

                        composable(route = RouterNavigationEnum.SINGUP.name) { screenSingUp(
                            context = this@MainActivity,
                            singUpViewModel = singUpViewModel
                        ) }

                    }
                }
            }
        }
    }
}