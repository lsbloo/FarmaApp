package com.farma.poc.featuresApp.compose.login.data.repository

import com.farma.poc.featuresApp.compose.login.data.dao.LoginDAO
import com.farma.poc.featuresApp.compose.login.data.models.CredentialsDTO
import com.farma.poc.featuresApp.compose.login.data.models.ResponseLoginDTO
import com.farma.poc.featuresApp.compose.login.data.task.LoginApiTask
import okhttp3.ResponseBody

class LoginRepository(private val loginApiTask: LoginApiTask, private val loginDAO: LoginDAO) {


    suspend fun authenticateUser(
        email: String,
        password: String,
        onSuccessData: (ResponseLoginDTO?) -> Unit,
        onFailureError: (ResponseBody?) -> Unit,
        onShowLoading: (Boolean) -> Unit
    ) {
        loginApiTask.call(
            e = CredentialsDTO(email, password = password),
            callback = { onSuccess, onFailure, onShouldLoading ->
                onSuccess?.data?.let { data ->
                    loginDAO.insertLoginToken(data)
                    onSuccessData.invoke(data)
                }

                onFailure?.data?.let {
                    onFailureError.invoke(it)
                }
                onShouldLoading?.let {
                    onShowLoading.invoke(it)
                }
            },
            errorNetWorkNotAvailable = {

            }
        )
    }

    private fun getDataCached(onSuccess: (ResponseLoginDTO?) -> Unit) =
        onSuccess.invoke(loginDAO.getLoginToken(SINGLE_ID_USER))

    companion object {
        private const val SINGLE_ID_USER = 1
    }
}