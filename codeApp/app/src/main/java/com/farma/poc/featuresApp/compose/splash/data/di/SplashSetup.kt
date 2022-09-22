package com.farma.poc.featuresApp.compose.splash.data.di

import android.content.Context
import com.farma.poc.featuresApp.compose.splash.presentation.SplashViewModel
import org.koin.dsl.module

object SplashSetup {


    private fun provideSplashViewModel(context: Context) = SplashViewModel(context)

    fun setupSplash() = module {

        single {
            provideSplashViewModel(get())
        }
    }
}