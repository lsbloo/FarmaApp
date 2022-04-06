package com.farma.poc.login.data.api


import com.farma.poc.login.constants.LoginConstants
import com.farma.poc.login.data.models.ResponseLoginDTO
import retrofit2.Response
import retrofit2.http.POST


interface LoginAPI {

    @POST(LoginConstants.API.ENDPOINT_LOGIN)
    suspend fun authenticateUser(): Response<ResponseLoginDTO?>


}