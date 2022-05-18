package com.farma.poc.core.base

import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.farma.poc.R
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.core.store.DataStoreConfig
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.core.utils.enums.DurationSnackBarEnum
import kotlinx.coroutines.CoroutineScope

open class BaseViewModel(private val context: Context) : ViewModel() {

    var routerNavigation: RouterNavigation? = null

    fun setNavigation(navController: RouterNavigation) {
        this.routerNavigation = navController
    }

    fun getDataStoreConfig() = DataStoreConfig(context = context)

    var logoutAppEvent = mutableStateOf(false)
    var dismissDialogLogout = mutableStateOf(true)
    var hasNetworkError = mutableStateOf(false)


    @Composable
    fun showDefaultErrorNetwork(
        state: MutableState<Boolean>,
        courotineScope: CoroutineScope,
        scaffoldState: ScaffoldState,
        onApplyCalled: () -> Unit,
    ) {
        state.value.let { showStatus ->
            ComposableUtils.showSnackBarError(scaffoldState = scaffoldState.snackbarHostState,
                coroutineScope = courotineScope,
                enable = showStatus,
                durationSnackBarEnum = DurationSnackBarEnum.LONG,
                message = context.getString(R.string.service_unavailable_network),
                actionLabel = context.getString(R.string.label_closed_button), onActionPerformed = {
                    Handler().postDelayed({
                        onApplyCalled.invoke()
                    }, 3000)
                })
        }
    }

    fun showToastNetworkUnavailable() {
        Toast.makeText(context, context.getString(R.string.network_unavailable), Toast.LENGTH_SHORT)
            .show()
    }

}