package com.farma.poc.core.base

import android.content.Context
import org.koin.dsl.module

object BaseViewModelModule {


    fun provideBaseViewModule(context: Context) = BaseViewModel(context)

    fun setupBaseViewModel() = module {
        single { provideBaseViewModule(get()) }
    }
}