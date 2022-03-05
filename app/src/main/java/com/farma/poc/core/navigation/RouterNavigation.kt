package com.farma.poc.core.navigation

interface RouterNavigation {

    fun navigateTo(
        router: RouterNavigationEnum,
        arguments: Any,
    )

    fun navigateTo(
        router: RouterNavigationEnum,
    )

    fun popBackStack()

    fun navigatePop()

}