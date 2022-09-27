package com.farma.poc.featuresApp.compose.settings.home.data.di

import android.content.Context
import com.farma.poc.core.config.data.FarmaAppDatabase
import com.farma.poc.featuresApp.compose.settings.home.data.api.SettingsAPI
import com.farma.poc.featuresApp.compose.settings.home.data.dao.SettingsDAO
import com.farma.poc.featuresApp.compose.settings.home.data.repository.SettingsRepository
import com.farma.poc.featuresApp.compose.settings.home.data.task.SettingsTask
import com.farma.poc.featuresApp.compose.settings.home.presentation.SettingsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object SettingsSetup {

    private fun provideSettingsApi(retrofit: Retrofit): SettingsAPI =
        retrofit.create(SettingsAPI::class.java)

    private fun provideSettingsTask(settingsAPI: SettingsAPI, context: Context): SettingsTask =
        SettingsTask(settingsAPI, context = context)

    private fun provideSettingsRepository(
        settingsTask: SettingsTask,
        settingsDAO: SettingsDAO
    ): SettingsRepository =
        SettingsRepository(settingsTask = settingsTask, settingsDAO = settingsDAO)

    private fun provideSettingsDAO(farmaAppDatabase: FarmaAppDatabase): SettingsDAO =
        farmaAppDatabase.settingsDao()

    private fun provideSettingsViewModel(
        settingsRepository: SettingsRepository,
        context: Context
    ): SettingsViewModel =
        SettingsViewModel(settingsRepository, context)

    fun setup() = module {
        single {
            provideSettingsApi(get(named("noAuth")))
        }
        single {
            provideSettingsTask(get(), get())
        }
        single {
            provideSettingsDAO(get())
        }
        single {
            provideSettingsRepository(get(), get())
        }
        single {
            provideSettingsViewModel(get(), get())
        }

    }
}