package com.farma.poc.features.singup.data.di

import android.content.Context
import com.farma.poc.features.singup.data.api.SingUpApi
import com.farma.poc.features.singup.data.repository.SingUpRepository
import com.farma.poc.features.singup.data.task.SingUpTask
import com.farma.poc.features.singup.presentation.SingUpViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object SingUpSetup {


    private fun provideTask(singUpApi: SingUpApi) = SingUpTask(singUpApi)

    private fun provideApi(retrofit: Retrofit) = retrofit.create(SingUpApi::class.java)

    private fun provideRepository(singUpTask: SingUpTask) = SingUpRepository(singUpTask)

    private fun provideViewModel(singUpRepository: SingUpRepository, context: Context) = SingUpViewModel(singUpRepository,context)

    fun setupSingUp() = module {

        single { provideApi(get())}
        single { provideTask(get())}
        single { provideRepository(get())}
        single { provideViewModel(get(), get())}
    }
}