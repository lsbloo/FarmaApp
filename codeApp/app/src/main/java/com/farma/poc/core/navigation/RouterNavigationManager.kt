package com.farma.poc.core.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.farma.poc.core.animation.TransitionComponents
import com.farma.poc.core.config.data.provider.DataProvider
import com.farma.poc.core.config.data.provider.OnDataProvider
import com.farma.poc.core.config.data.provider.OnDataProviderSettingsNavigation
import com.google.accompanist.navigation.animation.AnimatedNavHost

class RouterNavigationManager(
    private val navController: NavHostController,
    private val navGraphBuilder: NavGraphBuilder,
    private val context: Context
) : RouterNavigation {

    private var onRouterActionNavigationListener: OnRouterActionNavigationListener? = null

    override fun navigateTo(navigationRouter: RouterNavigationEnum, arguments: Any) {}

    override fun navigateTo(router: RouterNavigationEnum) {
        navController.navigate(router.name)
    }

    override fun setRouterActionNavigationListener(listener: OnRouterActionNavigationListener) {
        onRouterActionNavigationListener = listener
    }

    override fun navigateToWithArgs(
        destination: RouterNavigationEnum,
        dataProvider: OnDataProviderSettingsNavigation?,
        onResult: ((Any) -> Unit)?
    ) {
        dataProvider?.let {
            (DataProvider(context) as OnDataProvider).let {
                provider ->
                provider.initializeProvider()
                provider.setOnActionDataProviderRedirect(it)
                navigateTo(destination)
                provider.getResultOnActionDataProvider(it, onResult = {
                    result ->
                    onResult?.invoke(result)
                })
                onRouterActionNavigationListener?.anActionWasTriggered()
            }
        }?: also {
            navigateTo(destination)
        }
    }

    override fun recoveryActionWithNavigate(
        dataProvider: OnDataProviderSettingsNavigation?,
        onResult: ((Any) -> Unit)?
    ) {
        dataProvider?.let {
            (DataProvider(context) as OnDataProvider).let { provider ->
                provider.initializeProvider()
                provider.getResultOnActionDataProvider(it, onResult = { result ->
                    onResult?.invoke(result)
                    provider.setOnActionDataProviderRedirect(dataProvider)
                })
            }
        }
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
        navController.popBackStack(
            route = routerBackDestination.name,
            inclusive = false,
            saveState = false
        )
    }

    override fun getNavController() = navController
}