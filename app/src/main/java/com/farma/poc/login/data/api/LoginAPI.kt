package com.farma.poc.login.data.api

import retrofit2.http.POST


interface LoginAPI {

    @POST("bff/auth/login")
    fun authenticateUser()


}