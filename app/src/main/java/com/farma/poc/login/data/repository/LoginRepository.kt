package com.farma.poc.login.data.repository

import com.farma.poc.login.data.models.ResponseLoginDTO
import com.farma.poc.login.data.task.LoginApiTask

class LoginRepository(private val loginApiTask: LoginApiTask) {


    suspend fun authenticateUser(onSuccess: (ResponseLoginDTO?) -> Unit,onFailure: (ResponseLoginDTO?) -> Unit,
    onShowLoading: (Boolean) -> Unit): Unit {
        onShowLoading.invoke(true)
        loginApiTask.authenticateUser(
            onSuccess = {
                onSuccess.invoke(it.data)
                onShowLoading.invoke(false)
            },
            onFailure = {
                onFailure.invoke(it.data)
                onShowLoading.invoke(false)
            }
        )
    }
}