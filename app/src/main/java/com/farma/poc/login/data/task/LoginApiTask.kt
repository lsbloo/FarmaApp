package com.farma.poc.login.data.task

import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.login.data.api.LoginAPI
import com.farma.poc.login.data.models.ResponseLoginDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody


class LoginApiTask(private val loginAPI: LoginAPI): BaseNetworkTaskImpl<ResponseLoginDTO,ResponseBody>() {

    override suspend fun call(
        t: ResponseLoginDTO?,
        callback: (ResultTask.OnSuccess<ResponseLoginDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            callback.invoke(null,null,true)
            val result = loginAPI.authenticateUser()
            result.isSuccessful.let {
                if (it) {
                    callback.invoke(ResultTask.OnSuccess(data = result.body()),null, false)
                } else {
                    callback.invoke(null,ResultTask.OnFailure(data = result.errorBody()), false)
                }
            }
        }
    }
}