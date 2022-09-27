package com.farma.poc.featuresApp.compose.home.data.di

import android.content.Context
import com.farma.poc.core.config.data.FarmaAppDatabase
import com.farma.poc.featuresApp.compose.home.data.api.HomeAPI
import com.farma.poc.featuresApp.compose.home.data.repository.HomeRepository
import com.farma.poc.featuresApp.compose.home.data.task.GetHomeItemsTask
import com.farma.poc.featuresApp.compose.home.presentation.HomeViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeSetup {

    private fun provideHomeApi(retrofit: Retrofit): HomeAPI = retrofit.create(HomeAPI::class.java)

    private fun provideHomeCategoryApiTask(homeAPI: HomeAPI, context: Context): GetHomeItemsTask =
        GetHomeItemsTask(homeAPI, context = context)

    private fun provideHomeRepository(
        getHomeItemsTask: GetHomeItemsTask,
        homeDAO: FarmaAppDatabase
    ): HomeRepository = HomeRepository(getHomeItemsTask, homeDAO = homeDAO.homeDao())


    private fun provideHomeViewModel(
        homeRepository: HomeRepository,
        context: Context
    ): HomeViewModel = HomeViewModel(homeRepository = homeRepository, context)


    fun setupHome() = module {

        single {
            provideHomeApi(get(named("noAuth")))
        }
        single { provideHomeCategoryApiTask(get(),get()) }
        single { provideHomeRepository(get(),get()) }
        single { provideHomeViewModel(get(),get()) }
    }

}