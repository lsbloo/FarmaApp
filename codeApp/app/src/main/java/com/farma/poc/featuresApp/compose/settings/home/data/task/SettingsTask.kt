package com.farma.poc.featuresApp.compose.settings.home.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.core.utils.dto.MessageClientResponseDTO
import com.farma.poc.featuresApp.compose.settings.home.data.api.SettingsAPI
import com.farma.poc.featuresApp.compose.settings.home.data.model.GetSettingsDTO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody


class SettingsTask(private val settingsAPI: SettingsAPI, context: Context) :
    BaseNetworkTaskImpl<String, GetSettingsDTO, ResponseBody>(context = context) {

    override suspend fun call(
        e: String?,
        callback: (ResultTask.OnSuccess<GetSettingsDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit
    ) {
        if (verifyIfHasNetworkAvailable()) {
            callback.invoke(null, null, true)
            CoroutineScope(Dispatchers.IO).launch {
                val result = e?.let { settingsAPI.getDataScreenSettings(it) }
                if (result?.isSuccessful == true) {
                    val messageClientResponseDTO = result.body()
                    callback.invoke(
                        ResultTask.OnSuccess(data = convertClientResponseToDTO(messageClientResponseDTO?.responseDTO)
                        ), null, false
                    )
                } else {
                    callback.invoke(
                        null,
                        ResultTask.OnFailure(data = result?.errorBody()),
                        false
                    )
                }
            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }


    private fun convertClientResponseToDTO(data: String?): GetSettingsDTO? {
        val gson = Gson()
        return gson.fromJson(data, GetSettingsDTO::class.java)
    }
}