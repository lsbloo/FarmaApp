package com.farma.poc.home.data.di

import com.farma.poc.core.config.data.FarmaAppDatabase
import com.farma.poc.home.data.api.HomeAPI
import com.farma.poc.home.data.dao.HomeDAO
import com.farma.poc.home.data.repository.HomeRepository
import com.farma.poc.home.data.task.HomeCategoryTask
import com.farma.poc.home.presentation.HomeViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeSetup {

    private fun provideHomeApi(retrofit: Retrofit): HomeAPI = retrofit.create(HomeAPI::class.java)

    private fun provideHomeCategoryApiTask(homeAPI: HomeAPI): HomeCategoryTask =
        HomeCategoryTask(homeAPI)

    private fun provideHomeRepository(
        homeCategoryTask: HomeCategoryTask,
        homeDAO: FarmaAppDatabase
    ): HomeRepository = HomeRepository(homeCategoryTask, homeDAO = homeDAO.homeDao())


    private fun provideHomeViewModel(
        homeRepository: HomeRepository
    ): HomeViewModel = HomeViewModel(homeRepository = homeRepository)


    fun setupHome() = module {

        single {
            provideHomeApi(get())
        }
        single { provideHomeCategoryApiTask(get()) }
        single { provideHomeRepository(get(),get())}
        single { provideHomeViewModel(get())}
    }

}