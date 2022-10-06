package com.farma.poc.featuresApp.compose.address.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.featuresApp.compose.address.data.api.AddressAPI
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class SelectAddressPrincipalTask(private val addressAPI: AddressAPI, context: Context) : BaseNetworkTaskImpl<AddressDTO,AddressDTO, ResponseBody>(context) {
    override suspend fun call(


        e: AddressDTO?,
        callback: (ResultTask.OnSuccess<AddressDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit
    ) {

        if (verifyIfHasNetworkAvailable()) {
            callback.invoke(null, null, true)
            CoroutineScope(Dispatchers.IO).launch {
                e?.let {
                    it.client_id_token?.let { token ->
                        val result = addressAPI.selectAddressPrincipal(token,it.id!!,it.isPrincipal!!)
                        if (result.isSuccessful) {
                            val data: AddressDTO? = convertClientResponseToDTO(result.body()?.responseDTO)
                            callback(ResultTask.OnSuccess(data = data), null, false)

                        } else if (result.code() == HttpStatusResponse.BAD_REQUEST) {
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

    private fun convertClientResponseToDTO(data: String?): AddressDTO? {
        return Gson().fromJson(data, AddressDTO::class.java)
    }
}