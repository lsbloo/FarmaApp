package com.farma.poc.login.data.di

import com.farma.poc.login.data.api.LoginAPI
import com.farma.poc.login.data.repository.LoginRepository
import com.farma.poc.login.data.task.LoginApiTask
import com.farma.poc.login.presentation.LoginViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object LoginSetup {

    private fun provideLoginApi(retrofit: Retrofit): LoginAPI = retrofit.create(LoginAPI::class.java)

    private fun provideLoginApiTask( loginAPI: LoginAPI): LoginApiTask {
        return LoginApiTask(loginAPI = loginAPI)
    }

    private fun provideLoginRepository(loginApiTask: LoginApiTask): LoginRepository {
        return LoginRepository(loginApiTask = loginApiTask)
    }

    private fun provideLoginViewModel(loginRepository: LoginRepository): LoginViewModel{
        return LoginViewModel(loginRepository = loginRepository)
    }


    fun setupLogin() = module {
        single {
            provideLoginApi(get())
        }
        single {
            provideLoginApiTask(get())
        }
        single { provideLoginRepository(get()) }
        single { provideLoginViewModel(get())}
    }

}