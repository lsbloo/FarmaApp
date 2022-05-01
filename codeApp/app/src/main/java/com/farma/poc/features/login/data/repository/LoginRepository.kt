package com.farma.poc.features.login.data.repository

import androidx.lifecycle.LiveData
import com.farma.poc.features.login.data.dao.LoginDAO
import com.farma.poc.features.login.data.models.CredentialsDTO
import com.farma.poc.features.login.data.models.ResponseLoginDTO
import com.farma.poc.features.login.data.task.LoginApiTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        )
    }

    private fun getDataCached(onSuccess: (ResponseLoginDTO?) -> Unit) =
        onSuccess.invoke(loginDAO.getLoginToken(SINGLE_ID_USER))

    companion object {
        private const val SINGLE_ID_USER = 1
    }
}