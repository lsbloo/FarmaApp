package com.farma.poc.core.navigation

enum class RouterNavigationEnum(route: String) {
    SPLASH(route = "/splash"),
    ONBOARDING(route = "/onboarding"),
    LOGIN(route = "/login"),
    HOME(route = "/home"),
    SINGUP(route = "/singup"),
    SETTINGS(route = "/settings"),
    ADDRESS(route = "/address"),
    LIST_ADDRESS(route = "/list_address"),
    DETAIL_ADDRESS(route = "/detail_address")
}