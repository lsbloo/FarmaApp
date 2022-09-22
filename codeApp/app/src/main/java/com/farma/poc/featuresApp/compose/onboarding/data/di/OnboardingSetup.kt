package com.farma.poc.featuresApp.compose.onboarding.data.di

import android.content.Context
import com.farma.poc.core.config.data.FarmaAppDatabase
import com.farma.poc.featuresApp.compose.onboarding.data.api.OnboardingAPI
import com.farma.poc.featuresApp.compose.onboarding.data.repository.OnboardingRepository
import com.farma.poc.featuresApp.compose.onboarding.data.task.OnboardingTask
import com.farma.poc.featuresApp.compose.onboarding.presentation.OnboardingViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object OnboardingSetup {


    private fun provideOnboardingRepository(
        onboardingTask: OnboardingTask,
        instanceDatabase: FarmaAppDatabase
    ): OnboardingRepository {
        return OnboardingRepository(
            onboardingTask = onboardingTask,
            onboardingDAO = instanceDatabase.onboardingDAO()
        )
    }

    private fun provideOnboardingTask(onboardingAPI: OnboardingAPI, context: Context) =
        OnboardingTask(onboardingAPI = onboardingAPI, context = context)

    private fun provideOnboardingAPI(retrofit: Retrofit) =
        retrofit.create(OnboardingAPI::class.java)

    private fun provideOnboardingViewModel(
        onboardingRepository: OnboardingRepository,
        context: Context
    ) = OnboardingViewModel(onboardingRepository = onboardingRepository, context = context)

    fun setupOnboarding() = module {
        single { provideOnboardingAPI(get()) }
        single { provideOnboardingTask(get(),get()) }
        single { provideOnboardingRepository(get(), get()) }
        single { provideOnboardingViewModel(get(),get()) }
    }
}