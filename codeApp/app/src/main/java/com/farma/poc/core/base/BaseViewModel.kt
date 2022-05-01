package com.farma.poc.core.base

import android.content.Context
import androidx.lifecycle.ViewModel
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.core.store.DataStoreConfig

open class BaseViewModel(private val context: Context) : ViewModel() {

    var routerNavigation: RouterNavigation? = null

    fun setNavigation(navController: RouterNavigation) {
        this.routerNavigation = navController
    }

    fun getDataStoreConfig() = DataStoreConfig(context = context)

}