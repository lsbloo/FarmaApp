package com.farma.poc.featuresApp.compose.singup.data.di

import android.content.Context
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.featuresApp.compose.singup.data.api.SingUpApi
import com.farma.poc.featuresApp.compose.singup.data.repository.SingUpRepository
import com.farma.poc.featuresApp.compose.singup.data.task.SingUpTask
import com.farma.poc.featuresApp.compose.singup.presentation.SingUpViewModel
import com.farma.poc.featuresApp.compose.singup.validators.SingUpValidatorImpl
import com.farma.poc.featuresApp.compose.singup.validators.interfaces.SingUpValidator
import org.koin.core.qualifier.named
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

        single { provideApi(get(named("auth"))) }
        single { provideTask(get(), get()) }
        single { provideRepository(get()) }
        single { provideViewModel(get(), get(), provideValidator()) }

    }
}