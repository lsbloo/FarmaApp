package com.farma.poc.core.config.application

import android.app.Application
import com.farma.poc.core.base.BaseViewModelModule
import com.farma.poc.core.config.data.FarmaDatabase
import com.farma.poc.core.config.network.RetrofitInitializer
import com.farma.poc.features.onboarding.data.di.OnboardingSetup
import com.farma.poc.features.splash.data.di.SplashSetup
import com.farma.poc.home.data.di.HomeSetup
import com.farma.poc.login.data.di.LoginSetup
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@MainApplication)

            modules(
                listOf<Module>(
                    RetrofitInitializer.getRetrofitNetModule(),
                    FarmaDatabase.farmaDatabaseModule(),
                    BaseViewModelModule.setupBaseViewModel(),
                    SplashSetup.setupSplash(),
                    OnboardingSetup.setupOnboarding(),
                    LoginSetup.setupLogin(),
                    HomeSetup.setupHome()
                )
            )
        }
    }
}