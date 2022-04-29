package com.farma.poc.features.singup.data.task

import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.features.singup.data.api.SingUpApi
import com.farma.poc.features.singup.data.dao.SingUpCredentialsDTO
import okhttp3.ResponseBody

class SingUpTask(private val singUpApi: SingUpApi) :
    BaseNetworkTaskImpl<SingUpCredentialsDTO, ResponseDTO, ResponseBody>() {


    override suspend fun call(
        e: SingUpCredentialsDTO?,
        callback: (ResultTask.OnSuccess<ResponseDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}