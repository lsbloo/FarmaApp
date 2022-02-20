package com.farma.poc.login.presentation

import com.farma.poc.login.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) {


    fun login(){
        loginRepository.login()
    }
}