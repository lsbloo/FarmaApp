package com.farma.poc.login.data.task

import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.login.data.api.LoginAPI
import com.farma.poc.login.data.models.ResponseLoginDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody


class LoginApiTask(private val loginAPI: LoginAPI) {


    suspend fun authenticateUser(onSuccess: (ResultTask.OnSuccess<ResponseLoginDTO>) -> Unit,
                                 onFailure: (ResultTask.OnFailure<ResponseBody>) -> Unit,
    onShouldLoading: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onShouldLoading.invoke(true)
            val result = loginAPI.authenticateUser()
            result.isSuccessful.let {
                if(it) {
                    onSuccess.invoke(ResultTask.OnSuccess(data = result.body()))
                    onShouldLoading.invoke(false)
                } else {
                    onFailure.invoke(ResultTask.OnFailure(data = result.errorBody()))
                    onShouldLoading.invoke(false)
                }
            }
        }

    }

}