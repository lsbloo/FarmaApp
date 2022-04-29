package com.farma.poc.core.navigation

import androidx.compose.runtime.Composable

interface RouterNavigation {

    fun navigateTo(
        router: RouterNavigationEnum,
        arguments: Any,
    )

    fun navigateTo(
        router: RouterNavigationEnum,
    )

    @Composable
    fun navigateToWithAnimationTransition(
        initRouterNavigationEnum: RouterNavigationEnum,
        destinationRouter: RouterNavigationEnum,
        timeDurationTween: Int
    )

    fun popBackStack()

    fun navigatePop(routerBackDestination: RouterNavigationEnum)

}