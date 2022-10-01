package com.farma.poc.core.config.application

import android.app.Application
import com.farma.poc.core.base.BaseViewModelModule
import com.farma.poc.core.config.data.FarmaDatabase
import com.farma.poc.core.config.network.RetrofitInitializer
import com.farma.poc.featuresApp.compose.onboarding.data.di.OnboardingSetup
import com.farma.poc.featuresApp.compose.splash.data.di.SplashSetup
import com.farma.poc.featuresApp.compose.home.data.di.HomeSetup
import com.farma.poc.featuresApp.compose.login.data.di.LoginSetup
import com.farma.poc.featuresApp.compose.settings.home.data.di.SettingsSetup
import com.farma.poc.featuresApp.compose.singup.data.di.SingUpSetup
import com.farma.poc.featuresApp.compose.address.data.di.AddressSetup
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
                    AddressSetup.setupAddress(),
                    LoginSetup.setupLogin(),
                    HomeSetup.setupHome(),
                    SingUpSetup.setupSingUp(),
                    SettingsSetup.setup()
                )
            )
        }
    }
}