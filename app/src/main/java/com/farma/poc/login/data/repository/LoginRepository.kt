package com.farma.poc.login.data.repository

import com.farma.poc.login.data.models.ResponseLoginDTO
import com.farma.poc.login.data.task.LoginApiTask
import okhttp3.ResponseBody

class LoginRepository(private val loginApiTask: LoginApiTask) {


    suspend fun authenticateUser(onSuccess: (ResponseLoginDTO?) -> Unit,
                                 onFailure: (ResponseBody?) -> Unit,
    onShowLoading: (Boolean) -> Unit): Unit {
        loginApiTask.authenticateUser(
            onSuccess = {
                onSuccess.invoke(it.data)
            },
            onFailure = {
                onFailure.invoke(it.data)
            },
            onShouldLoading = {
                onShowLoading.invoke(it)
            }
        )
    }
}