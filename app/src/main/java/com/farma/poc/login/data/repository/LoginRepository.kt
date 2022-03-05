package com.farma.poc.login.data.repository

import androidx.lifecycle.LiveData
import com.farma.poc.login.data.dao.LoginDAO
import com.farma.poc.login.data.models.ResponseLoginDTO
import com.farma.poc.login.data.task.LoginApiTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class LoginRepository(private val loginApiTask: LoginApiTask, private val loginDAO: LoginDAO) {


    suspend fun authenticateUser(onSuccess: (LiveData<ResponseLoginDTO?>) -> Unit,
                                 onFailure: (ResponseBody?) -> Unit,
                                 onShowLoading: (Boolean) -> Unit): Unit {
        loginApiTask.authenticateUser(
            onSuccess = {
                it.data?.let { data -> loginDAO.insertLoginToken(data) }
                getDataCached { dataCached ->
                    onSuccess.invoke(dataCached)
                }
            },
            onFailure = {
                onFailure.invoke(it.data)
            },
            onShouldLoading = {
                onShowLoading.invoke(it)
            }
        )
    }

    private fun getDataCached(onSuccess: (LiveData<ResponseLoginDTO?>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onSuccess.invoke(loginDAO.getLoginToken(SINGLE_ID_USER))
        }
    }

    companion object {
        private const val SINGLE_ID_USER = 1
    }
}