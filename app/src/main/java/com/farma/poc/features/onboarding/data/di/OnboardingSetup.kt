package com.farma.poc.features.onboarding.data.di

import com.farma.poc.core.config.data.FarmaAppDatabase
import com.farma.poc.features.onboarding.data.api.OnboardingAPI
import com.farma.poc.features.onboarding.data.repository.OnboardingRepository
import com.farma.poc.features.onboarding.data.task.OnboardingTask
import com.farma.poc.features.onboarding.presentation.OnboardingViewModel
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

    private fun provideOnboardingTask(onboardingAPI: OnboardingAPI) =
        OnboardingTask(onboardingAPI = onboardingAPI)

    private fun provideOnboardingAPI(retrofit: Retrofit) =
        retrofit.create(OnboardingAPI::class.java)

    private fun provideOnboardingViewModel(onboardingRepository: OnboardingRepository) = OnboardingViewModel(onboardingRepository = onboardingRepository)

    fun setupOnboarding() = module {
        single { provideOnboardingAPI(get()) }
        single { provideOnboardingTask(get()) }
        single { provideOnboardingRepository(get(), get()) }
        single { provideOnboardingViewModel(get()) }
    }
}