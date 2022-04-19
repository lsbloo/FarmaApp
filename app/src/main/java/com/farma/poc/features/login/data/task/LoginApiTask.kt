package com.farma.poc.features.login.data.task

import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.core.utils.safeLet
import com.farma.poc.features.login.data.api.LoginAPI
import com.farma.poc.features.login.data.models.CredentialsDTO
import com.farma.poc.features.login.data.models.ResponseLoginDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody


class LoginApiTask(private val loginAPI: LoginAPI) :
    BaseNetworkTaskImpl<CredentialsDTO, ResponseLoginDTO, ResponseBody>() {


    override suspend fun call(
        e: CredentialsDTO?,
        callback: (ResultTask.OnSuccess<ResponseLoginDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit
    ) {
        callback.invoke(null, null, true)
        safeLet(e?.email, e?.password, onResult = { email, password ->
            CoroutineScope(Dispatchers.IO).launch {
                val result = loginAPI.authenticateUser(CredentialsDTO(email, password = password))
                result.isSuccessful.let {
                    if (it) {
                        callback.invoke(ResultTask.OnSuccess(data = result.body()), null, false)
                    } else {
                        callback.invoke(
                            null,
                            ResultTask.OnFailure(data = result.errorBody()),
                            false
                        )
                    }
                }
            }
        })
    }


}