package com.farma.poc.featuresApp.compose.settings.home.data.repository

import com.farma.poc.featuresApp.compose.settings.home.data.dao.SettingsDAO
import com.farma.poc.featuresApp.compose.settings.home.data.model.GetSettingsDTO
import com.farma.poc.featuresApp.compose.settings.home.data.task.SettingsTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart
import okhttp3.ResponseBody

class SettingsRepository(
    private val settingsTask: SettingsTask,
    private val settingsDAO: SettingsDAO
) {


    suspend fun getSettingsDataScreen(
        onSuccessData: (GetSettingsDTO?) -> Unit,
        onFailureData: (ResponseBody?) -> Unit,
        onShouldLoader: (Boolean) -> Unit,
        hasErrorNetwork: () -> Unit
    ) {
        settingsTask.call(
            callback = { onSuccess, onFailure, onShouldLoading ->
                onSuccess?.data?.let {
                    settingsDAO.insertSettings(it)
                    onSuccessData.invoke(it)
                }

                onShouldLoading?.let {
                    onShouldLoader.invoke(it)
                }

                onFailure?.data?.let {
                    onFailureData.invoke(it)
                }

            },
            errorNetWorkNotAvailable = {
                hasErrorNetwork.invoke()
            }
        )
    }

    suspend fun getSettingsDataCached() = settingsDAO.getSettingsFlow(SINGLE_ID_SETTINGS).onStart { delay(1000) }


    companion object {
        private const val SINGLE_ID_SETTINGS = 1
    }
}