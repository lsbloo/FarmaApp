package com.farma.poc.featuresApp.compose.address.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.base.BaseNetworkTaskImpl.HttpStatusResponse.BAD_REQUEST
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.featuresApp.compose.address.data.api.AddressAPI
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.onboarding.data.models.ItensOboardingDTO
import com.farma.poc.featuresApp.compose.settings.home.data.model.GetSettingsDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.lang.reflect.Type

class GetAddressListTask(private val addressAPI: AddressAPI, context: Context) :
    BaseNetworkTaskImpl<AddressDTO, List<AddressDTO>, ResponseBody>(
        context
    ) {
    override suspend fun call(
        e: AddressDTO?,
        callback: (ResultTask.OnSuccess<List<AddressDTO>>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit
    ) {
        if (verifyIfHasNetworkAvailable()) {
            callback.invoke(null, null, true)
            CoroutineScope(Dispatchers.IO).launch {
                e?.let {
                    it.client_id_token?.let { token ->
                        val result = addressAPI.getAddresses(token)
                        if (result.isSuccessful) {
                            val data: List<AddressDTO>? =
                                convertClientResponseToDTO(result.body()?.responseDTO)
                            callback(ResultTask.OnSuccess(data = data), null, false)

                        } else if (result.code() == BAD_REQUEST) {
                            callback(ResultTask.OnSuccess(data = null), null, false)
                        } else {
                            callback(null, ResultTask.OnFailure(data = result?.errorBody()), false)
                        }
                    }
                }

            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }


    private fun convertClientResponseToDTO(data: String?): List<AddressDTO>? {
        return Gson().fromJson(data, Array<AddressDTO>::class.java).asList()
    }
}
