package com.farma.poc.core.base

import org.koin.dsl.module

object BaseViewModelModule {


    fun provideBaseViewModule() = BaseViewModel()

    fun setupBaseViewModel() = module {
        single { provideBaseViewModule() }
    }
}