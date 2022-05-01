package com.farma.poc.features.login.data.di

import android.content.Context
import com.farma.poc.core.config.data.FarmaAppDatabase
import com.farma.poc.features.login.data.api.LoginAPI
import com.farma.poc.features.login.data.repository.LoginRepository
import com.farma.poc.features.login.data.task.LoginApiTask
import com.farma.poc.features.login.presentation.LoginViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object LoginSetup {

    private fun provideLoginApi(retrofit: Retrofit): LoginAPI =
        retrofit.create(LoginAPI::class.java)

    private fun provideLoginApiTask(loginAPI: LoginAPI, context: Context): LoginApiTask {
        return LoginApiTask(loginAPI = loginAPI, context)
    }

    private fun provideLoginRepository(
        loginApiTask: LoginApiTask,
        databaseInstance: FarmaAppDatabase
    ): LoginRepository {
        return LoginRepository(loginApiTask = loginApiTask, loginDAO = databaseInstance.loginDao())
    }

    private fun provideLoginViewModel(loginRepository: LoginRepository, context: Context): LoginViewModel {
        return LoginViewModel(loginRepository = loginRepository, context)
    }


    fun setupLogin() = module {
        single {
            provideLoginApi(get())
        }
        single {
            provideLoginApiTask(get(),get())
        }
        single { provideLoginRepository(get(), get()) }
        single { provideLoginViewModel(get(),get()) }
    }

}