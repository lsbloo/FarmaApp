package com.farma.poc.features.settings.home.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
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

    fun logout() {
        routerNavigation?.navigatePop(RouterNavigationEnum.LOGIN)
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