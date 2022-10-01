package com.farma.poc.featuresApp.compose.address.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.core.utils.dto.MessageClientResponseDTO
import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.featuresApp.compose.address.data.api.AddressAPI
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class CreateAddressTask(private val addressAPI: AddressAPI, context: Context) :
    BaseNetworkTaskImpl<AddressDTO, MessageClientResponseDTO, ResponseBody>(context) {


    override suspend fun call(
        e: AddressDTO?,
        callback: (ResultTask.OnSuccess<MessageClientResponseDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit
    ) {

        if (verifyIfHasNetworkAvailable()) {
            callback.invoke(null, null, true)
            CoroutineScope(Dispatchers.IO).launch {
                val result = e?.let { addressAPI.createAddress(it) }
                if (result?.isSuccessful == true) {
                    callback(ResultTask.OnSuccess(data = result.body()), null, false)
                } else {
                    callback(null, ResultTask.OnFailure(data = result?.errorBody()), false)
                }
            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }


}