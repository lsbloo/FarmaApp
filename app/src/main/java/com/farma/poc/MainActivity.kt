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
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.navigation.RouterNavigationManager
import com.farma.poc.features.onboarding.presentation.OnboardingViewModel
import com.farma.poc.features.onboarding.presentation.setupOnboardingScreen
import com.farma.poc.features.splash.presentation.SplashViewModel
import com.farma.poc.features.splash.presentation.screenSplash
import com.farma.poc.home.presentation.homeComponent
import com.farma.poc.login.presentation.LoginViewModel
import com.farma.poc.login.presentation.screenLogin
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
            FarmaAppTheme {
                // A surface container using the 'background' color from the theme
                val loginViewModel = getViewModel<LoginViewModel>()
                val splashViewModel = getViewModel<SplashViewModel>()
                val onboardingViewModel = getViewModel<OnboardingViewModel>()

                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = RouterNavigationEnum.SPLASH.name
                    ) {
                        val routerNavigationManager = RouterNavigationManager(navController, this)
                        splashViewModel.setNavigation(routerNavigationManager)
                        loginViewModel.setNavigation(routerNavigationManager)
                        onboardingViewModel.setNavigation(routerNavigationManager)


                        composable(route = RouterNavigationEnum.SPLASH.name,
                            enterTransition = {
                                when (initialState.destination.route) {
                                    RouterNavigationEnum.ONBOARDING.name ->
                                        slideIntoContainer(
                                            AnimatedContentScope.SlideDirection.Left,
                                            animationSpec = tween(DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING)
                                        )
                                    else -> null
                                }
                            },
                            exitTransition = {
                                when (targetState.destination.route) {
                                    RouterNavigationEnum.ONBOARDING.name ->
                                        slideOutOfContainer(
                                            AnimatedContentScope.SlideDirection.Left,
                                            animationSpec = tween(DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING)
                                        )
                                    else -> null
                                }
                            },
                            popEnterTransition = {
                                when (initialState.destination.route) {
                                    RouterNavigationEnum.ONBOARDING.name ->
                                        slideIntoContainer(
                                            AnimatedContentScope.SlideDirection.Right,
                                            animationSpec = tween(DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING)
                                        )
                                    else -> null
                                }
                            },
                            popExitTransition = {
                                when (targetState.destination.route) {
                                    RouterNavigationEnum.ONBOARDING.name ->
                                        slideOutOfContainer(
                                            AnimatedContentScope.SlideDirection.Right,
                                            animationSpec = tween(DURATION_ANIMATION_SPEC_TRANSITION_SPLASH_TO_ONBOARDING)
                                        )
                                    else -> null
                                }
                            }) {
                            screenSplash(
                                splashViewModel = splashViewModel,
                                context = this@MainActivity
                            )
                        }
                        composable(route = RouterNavigationEnum.ONBOARDING.name, enterTransition = {
                            when (initialState.destination.route) {
                                RouterNavigationEnum.ONBOARDING.name ->
                                    slideIntoContainer(
                                        AnimatedContentScope.SlideDirection.Left,
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        },
                            exitTransition = {
                                when (targetState.destination.route) {
                                    RouterNavigationEnum.LOGIN.name ->
                                        slideOutOfContainer(
                                            AnimatedContentScope.SlideDirection.Left,
                                            animationSpec = tween(700)
                                        )
                                    else -> null
                                }
                            },
                            popEnterTransition = {
                                when (initialState.destination.route) {
                                    RouterNavigationEnum.LOGIN.name ->
                                        slideIntoContainer(
                                            AnimatedContentScope.SlideDirection.Right,
                                            animationSpec = tween(700)
                                        )
                                    else -> null
                                }
                            }
                        ) {
                            setupOnboardingScreen(
                                onboardingViewModel = onboardingViewModel,
                                context = this@MainActivity
                            )
                        }
                        composable(route = RouterNavigationEnum.LOGIN.name) {
                            screenLogin(
                                loginViewModel,
                                this@MainActivity
                            )
                        }
                        composable(route = RouterNavigationEnum.HOME.name) { homeComponent() }
                    }
                }
            }
        }
    }
}