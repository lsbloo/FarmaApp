package com.farma.poc.features.splash.data.di

import com.farma.poc.features.splash.presentation.SplashViewModel
import org.koin.dsl.module

object SplashSetup {


    private fun provideSplashViewModel() = SplashViewModel()

    fun setupSplash() = module {

        single {
            provideSplashViewModel()
        }
    }
}