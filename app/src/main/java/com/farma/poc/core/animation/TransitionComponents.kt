package com.farma.poc.core.animation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.google.accompanist.navigation.animation.composable

class TransitionComponents(
    private val initialRoute: RouterNavigationEnum,
    private val destinationRoute: RouterNavigationEnum,
    private val timeDurationTween: Int
) {
    @ExperimentalAnimationApi
    fun setupComponentWithAnimation(scope: NavGraphBuilder, onCallComponentScreen: @Composable () -> Unit) {
        scope.apply {
            composable(route = initialRoute.name,
                enterTransition = {
                    when (initialState.destination.route) {
                        destinationRoute.name ->
                            slideIntoContainer(
                                AnimatedContentScope.SlideDirection.Left,
                                animationSpec = tween(timeDurationTween)
                            )
                        else -> null
                    }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        destinationRoute.name ->
                            slideOutOfContainer(
                                AnimatedContentScope.SlideDirection.Left,
                                animationSpec = tween(timeDurationTween)
                            )
                        else -> null
                    }
                },
                popEnterTransition = {
                    when (initialState.destination.route) {
                        destinationRoute.name ->
                            slideIntoContainer(
                                AnimatedContentScope.SlideDirection.Right,
                                animationSpec = tween(timeDurationTween)
                            )
                        else -> null
                    }
                },
                popExitTransition = {
                    when (targetState.destination.route) {
                        destinationRoute.name ->
                            slideOutOfContainer(
                                AnimatedContentScope.SlideDirection.Right,
                                animationSpec = tween(timeDurationTween)
                            )
                        else -> null
                    }
                }) {
                onCallComponentScreen.invoke()
            }
        }
    }
}