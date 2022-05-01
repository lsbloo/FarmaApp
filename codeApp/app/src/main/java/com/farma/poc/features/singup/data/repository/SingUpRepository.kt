package com.farma.poc.features.singup.data.repository

import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.features.singup.data.dao.SingUpCredentialsDTO
import com.farma.poc.features.singup.data.task.SingUpTask
import okhttp3.ResponseBody

class SingUpRepository(private val singUpTask: SingUpTask) {


    suspend fun registerAccount(
        email: String,
        password: String,
        name: String,
        cpf: String,
        onSuccessData: (ResponseDTO) -> Unit,
        onFailureError: (ResponseBody?) -> Unit,
        onShowLoading: (Boolean) -> Unit
    ) {

        singUpTask.call(
            e = SingUpCredentialsDTO(name = name, password = password, email = email, cpf = cpf),
            callback = { onSuccess, onFailure, onShouldLoading ->
                onSuccess?.data?.let {
                    onSuccessData.invoke(it)
                }
                onFailure?.data?.let {
                    onFailureError.invoke(it)
                }
                if (onShouldLoading != null) {
                    onShowLoading.invoke(onShouldLoading)
                }
            },
            errorNetWorkNotAvailable = {

            }
        )
    }

}