package com.farma.poc.core.base

import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farma.poc.R
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.core.store.DataStoreConfig
import com.farma.poc.core.utils.composables.ComposableUtils
import com.farma.poc.core.utils.enums.DurationSnackBarEnum
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

open class BaseViewModel(private val context: Context) : ViewModel() {

    fun transformCollectToAddressDTO(data: String): AddressDTO {
        return Gson().fromJson(data, AddressDTO::class.java)
    }

    var routerNavigation: RouterNavigation? = null

    val gson
    get() = Gson()

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

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

    fun getClientToken(onResult: (String) -> Unit) {
        viewModelScope.launch {
            getDataStoreConfig().sharedClientTokenFlow.take(1).collect {
                onResult.invoke(it)
            }
        }
    }

    fun getBearerToken(onResult: (String) -> Unit) {
        viewModelScope.launch {
            getDataStoreConfig().sharedTokenSessionFlow.take(1).collect { accessToken ->
                getDataStoreConfig().sharedTimeSessionFlow.take(1).collect { typeToken ->
                    onResult.invoke(typeToken + accessToken)
                }
            }
        }
    }

    fun getAccessToken(onResult: (String) -> Unit) {
        viewModelScope.launch {
            getDataStoreConfig().sharedTokenSessionFlow.take(1).collect {
                onResult.invoke(it)
            }
        }
    }

    fun getTypeAccessToken(onResult: (String) -> Unit) {
        viewModelScope.launch {
            getDataStoreConfig().sharedTimeSessionFlow.take(1).collect {
                onResult.invoke(it)
            }
        }
    }


    object HTTPStatusResponse {
        const val STATUS_ACCEPTED = 202L
    }

}