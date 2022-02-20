package com.farma.poc.login.data.repository

import com.farma.poc.login.data.task.LoginApiTask

class LoginRepository(private val loginApiTask: LoginApiTask) {


    fun login(){
        loginApiTask.authenticateUser()
    }
}