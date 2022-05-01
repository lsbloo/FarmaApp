package com.farma.poc.features.login.data.api


import com.farma.poc.features.login.constants.LoginConstants
import com.farma.poc.features.login.data.models.CredentialsDTO
import com.farma.poc.features.login.data.models.ResponseLoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginAPI {

    @POST(LoginConstants.API.ENDPOINT_LOGIN)
    suspend fun authenticateUser(@Body credentialsDTO: CredentialsDTO): Response<ResponseLoginDTO?>


}