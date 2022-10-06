package com.farma.poc.featuresApp.compose.address.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.featuresApp.compose.address.data.api.AddressAPI
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.address.data.models.setAddressId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class DeleteAddressTask(private val addressAPI: AddressAPI, context: Context) :
    BaseNetworkTaskImpl<AddressDTO, Long, ResponseBody>(context = context) {

    override suspend fun call(
        e: AddressDTO?,
        callback: (ResultTask.OnSuccess<Long>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit
    ) {
        if (verifyIfHasNetworkAvailable()) {
            callback.invoke(null, null, true)
            CoroutineScope(Dispatchers.IO).launch {
                val result = e?.let {
                    it.setAddressId(it.id!!)
                    addressAPI.deleteAddress(it.client_id_token!!, it.address_id!!)
                }
                if (result?.isSuccessful == true) {
                    callback(
                        ResultTask.OnSuccess(data = result.body()?.httpStatusCode),
                        null,
                        false
                    )
                } else {
                    callback(null, ResultTask.OnFailure(data = result?.errorBody()), false)
                }
            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }
}