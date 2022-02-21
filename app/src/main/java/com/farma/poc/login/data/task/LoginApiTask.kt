package com.farma.poc.login.data.task

import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.login.data.api.LoginAPI
import com.farma.poc.login.data.models.ResponseLoginDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginApiTask(private val loginAPI: LoginAPI) {


    suspend fun authenticateUser(onSuccess: (ResultTask.OnSuccess<ResponseLoginDTO>) -> Unit, onFailure: (ResultTask.OnFailure<ResponseLoginDTO>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = loginAPI.authenticateUser()
            result?.let {
                onSuccess.invoke(ResultTask.OnSuccess(data = it))
            }?: also {
                onFailure.invoke(ResultTask.OnFailure(data = null))
            }
        }

    }

}