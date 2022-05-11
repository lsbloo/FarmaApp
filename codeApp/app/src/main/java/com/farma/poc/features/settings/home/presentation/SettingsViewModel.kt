package com.farma.poc.features.settings.home.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.config.constants.ConfigApplicationConstants
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.features.settings.home.data.model.GetSettingsDTO
import com.farma.poc.features.settings.home.data.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository, context: Context) :
    BaseViewModel(context) {

    var datasetScreenSettings = mutableStateOf(GetSettingsDTO())
    var hasFlagShowBiometric = mutableStateOf(false)

    fun getDataScreen() {
        viewModelScope.launch {
            settingsRepository.getSettingsDataScreen(
                onSuccessData = {
                    if (it != null) {
                        datasetScreenSettings.value = it
                    }
                },
                onFailureData = {
                    tryRecoveryDataCached { onRecovery ->
                        if (!onRecovery) {
                            // show Error
                        }
                    }
                },
                onShouldLoader = {

                },
                hasErrorNetwork = {

                }
            )
        }
    }

    fun onDismissDialog() {
        dismissDialogLogout.value = false
    }

    private fun tryRecoveryDataCached(onRecovery: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            settingsRepository.getSettingsDataCached().collect {
                it?.let {
                    onRecovery(true)
                    datasetScreenSettings.value = it
                } ?: also {
                    onRecovery(false)
                }
            }
        }
    }

    fun activateBiometric(flagShow: Boolean) {
        ConfigApplicationConstants.PREFERENCES_SECURITY.AUTHENTICATE_WITH_BIOMETRIC.value = flagShow
        viewModelScope.launch {
            getDataStoreConfig().setFlagShowBiometric(ConfigApplicationConstants.PREFERENCES_SECURITY.AUTHENTICATE_WITH_BIOMETRIC.value)
        }
    }

    fun getStatusShowBiometric() {
        viewModelScope.launch {
            getDataStoreConfig().sharedFlagShowBiometric.collect {
                hasFlagShowBiometric.value = it
            }
        }
    }

    fun logout(logoutClick: Boolean) {
        logoutAppEvent.value = logoutClick
    }

    fun redirectToHome() {
        routerNavigation?.navigatePop(RouterNavigationEnum.HOME)
    }


    fun redirectToOrder() {

    }

    fun redirectToDataUser() {

    }

    fun redirectToFaq() {

    }

    fun redirectMethodsPayment() {

    }

    fun redirectAddress() {

    }

}