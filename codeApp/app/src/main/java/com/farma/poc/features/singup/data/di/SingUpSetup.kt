package com.farma.poc.features.singup.data.di

import android.content.Context
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.features.singup.data.api.SingUpApi
import com.farma.poc.features.singup.data.repository.SingUpRepository
import com.farma.poc.features.singup.data.task.SingUpTask
import com.farma.poc.features.singup.presentation.SingUpViewModel
import com.farma.poc.features.singup.validators.SingUpValidatorImpl
import com.farma.poc.features.singup.validators.interfaces.SingUpValidator
import org.koin.dsl.module
import retrofit2.Retrofit

object SingUpSetup {


    private fun provideTask(singUpApi: SingUpApi, context: Context) =
        SingUpTask(singUpApi, context = context)

    private fun provideApi(retrofit: Retrofit) = retrofit.create(SingUpApi::class.java)

    private fun provideRepository(singUpTask: SingUpTask) = SingUpRepository(singUpTask)

    private fun provideViewModel(
        singUpRepository: SingUpRepository,
        context: Context,
        singUpValidator: SingUpValidator<PojoValidator>
    ) = SingUpViewModel(singUpRepository, context, singUpValidator)

    private fun provideValidator() = SingUpValidatorImpl<PojoValidator>()

    fun setupSingUp() = module {

        single { provideApi(get()) }
        single { provideTask(get(), get()) }
        single { provideRepository(get()) }
        single { provideViewModel(get(), get(), provideValidator()) }

    }
}