package com.farma.poc.features.singup.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.features.singup.data.api.SingUpApi
import com.farma.poc.features.singup.data.dao.SingUpCredentialsDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class SingUpTask(private val singUpApi: SingUpApi, context: Context) :
    BaseNetworkTaskImpl<SingUpCredentialsDTO, ResponseDTO, ResponseBody>(context) {


    override suspend fun call(
        e: SingUpCredentialsDTO?,
        callback: (ResultTask.OnSuccess<ResponseDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit,
    ) {
        if (hasNetworkAvailable) {
            CoroutineScope(Dispatchers.IO).launch {
                callback.invoke(null, null, true)
                val result = e?.let { singUpApi.registerAccount(it) }
                if (result?.isSuccessful == true) {
                    callback.invoke(ResultTask.OnSuccess(data = result.body()), null, false)
                } else {
                    callback.invoke(null, ResultTask.OnFailure(data = result?.errorBody()), false)
                }
            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }

}