package com.farma.poc.core.config.data

import android.app.Application
import androidx.room.Room
import com.farma.poc.core.config.constants.ConfigApplicationConstants.NAME_DATABASE
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object FarmaDatabase {

    fun farmaDatabaseModule() = module {

        single {
            provideDatabase(androidApplication())
        }
    }
    private fun provideDatabase(application: Application): FarmaAppDatabase {
        return Room.databaseBuilder(application, FarmaAppDatabase::class.java, NAME_DATABASE)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }


}