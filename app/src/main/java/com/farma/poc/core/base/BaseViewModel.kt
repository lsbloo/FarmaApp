package com.farma.poc.core.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.farma.poc.core.navigation.RouterNavigation

open class BaseViewModel : ViewModel() {

    var routerNavigation: RouterNavigation? = null

    fun setNavigation(navController: RouterNavigation) {
        this.routerNavigation = navController
    }

}