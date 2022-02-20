package com.farma.poc.login.data.task

import com.farma.poc.login.data.api.LoginAPI
import retrofit2.Retrofit

class LoginApiTask(private val retrofit: Retrofit, private val loginAPI: LoginAPI) {

    private fun getRetrofit(): LoginAPI {
        return retrofit.create(loginAPI::class.java)
    }


    fun authenticateUser() {
        getRetrofit().authenticateUser()
    }
}