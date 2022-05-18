package com.farma.poc.features.settings.home.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.features.settings.home.data.api.SettingsAPI
import com.farma.poc.features.settings.home.data.model.GetSettingsDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class SettingsTask(private val settingsAPI: SettingsAPI, context: Context) :
    BaseNetworkTaskImpl<Any, GetSettingsDTO, ResponseBody>(context = context) {

    override suspend fun call(
        e: Any?,
        callback: (ResultTask.OnSuccess<GetSettingsDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit
    ) {
        if (verifyIfHasNetworkAvailable()) {
            callback.invoke(null, null, true)
            CoroutineScope(Dispatchers.IO).launch {
                val result = settingsAPI.getDataScreenSettings()
                if (result.isSuccessful) {
                    callback.invoke(ResultTask.OnSuccess(data = result.body()), null, false)
                } else {
                    callback.invoke(
                        null,
                        ResultTask.OnFailure(data = result.errorBody()),
                        false
                    )
                }
            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }
}