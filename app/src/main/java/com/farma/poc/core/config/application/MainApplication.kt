package com.farma.poc.core.config.application

import android.app.Application
import com.farma.poc.core.config.data.FarmaDatabase
import com.farma.poc.core.config.network.RetrofitInitializer
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

            modules(listOf<Module>(
                RetrofitInitializer.getRetrofitNetModule(), FarmaDatabase.farmaDatabaseModule()
            ))
        }
    }
}