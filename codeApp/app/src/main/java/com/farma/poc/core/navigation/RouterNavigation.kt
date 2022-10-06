package com.farma.poc.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.farma.poc.core.config.data.provider.DataProvider
import com.farma.poc.core.config.data.provider.OnDataProviderSettingsNavigation

interface RouterNavigation {

    fun navigateTo(
        router: RouterNavigationEnum,
        arguments: Any,
    )

    fun navigateTo(
        router: RouterNavigationEnum,
    )

    fun setRouterActionNavigationListener(listener: OnRouterActionNavigationListener)

    fun navigateToWithArgs(
        destination: RouterNavigationEnum,
        dataProvider: OnDataProviderSettingsNavigation? = null,
        onResult: ((Any) -> Unit)? = null
    )

    fun recoveryActionWithNavigate(
        dataProvider: OnDataProviderSettingsNavigation? = null,
        onResult: ((Any) -> Unit)? = null
    )

    @Composable
    fun navigateToWithAnimationTransition(
        initRouterNavigationEnum: RouterNavigationEnum,
        destinationRouter: RouterNavigationEnum,
        timeDurationTween: Int
    )

    fun popBackStack()

    fun navigatePop(routerBackDestination: RouterNavigationEnum)

    fun getNavController(): NavController

}