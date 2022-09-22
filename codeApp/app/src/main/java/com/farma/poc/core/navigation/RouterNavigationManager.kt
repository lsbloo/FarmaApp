package com.farma.poc.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.farma.poc.core.animation.TransitionComponents
import com.google.accompanist.navigation.animation.AnimatedNavHost

class RouterNavigationManager(
    private val navController: NavHostController,
    private val navGraphBuilder: NavGraphBuilder
) : RouterNavigation {


    override fun navigateTo(navigationRouter: RouterNavigationEnum, arguments: Any) {

    }

    override fun navigateTo(router: RouterNavigationEnum) {
        navController.navigate(router.name)
    }

    @Composable
    @ExperimentalAnimationApi
    override fun navigateToWithAnimationTransition(
        initRouterNavigationEnum: RouterNavigationEnum,
        destinationRouter: RouterNavigationEnum,
        timeDurationTween: Int
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = initRouterNavigationEnum.name
        ) {
            TransitionComponents(initRouterNavigationEnum, destinationRouter, timeDurationTween)
                .setupComponentWithAnimation(this, onCallComponentScreen = {
                    navController.navigate(destinationRouter.name)
                })
        }
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun navigatePop(routerBackDestination: RouterNavigationEnum) {
        navController.popBackStack(route = routerBackDestination.name,
            inclusive = false,
            saveState = false
        )
    }

}