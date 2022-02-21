package com.farma.poc.login.data.api


import com.farma.poc.login.data.models.ResponseLoginDTO
import retrofit2.http.POST


interface LoginAPI {

    @POST("/login")
    suspend fun authenticateUser(): ResponseLoginDTO?


}