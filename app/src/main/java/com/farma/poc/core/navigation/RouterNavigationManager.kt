package com.farma.poc.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

class RouterNavigationManager(private val navController: NavHostController, private val navGraphBuilder: NavGraphBuilder) : RouterNavigation {


    override fun navigateTo(navigationRouter: RouterNavigationEnum, arguments: Any) {

    }

    override fun navigateTo(router: RouterNavigationEnum) {
        navController.navigate(router.name)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun navigatePop() {
    }

}