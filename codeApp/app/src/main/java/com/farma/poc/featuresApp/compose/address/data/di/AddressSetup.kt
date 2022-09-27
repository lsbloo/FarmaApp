package com.farma.poc.featuresApp.compose.address.data.di

import android.content.Context
import com.farma.poc.featuresApp.compose.address.data.api.AddressAPI
import com.farma.poc.featuresApp.compose.address.data.dao.AddressDAO
import com.farma.poc.featuresApp.compose.address.data.repository.AddressRepository
import com.farma.poc.featuresApp.compose.address.data.task.CreateAddressTask
import com.farma.poc.featuresApp.compose.address.presentation.AddressViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object AddressSetup {


    private fun provideAPI(retrofit: Retrofit) = retrofit.create(AddressAPI::class.java)

    private fun provideCreateAddressTask(addressAPI: AddressAPI, context: Context) =
        CreateAddressTask(addressAPI, context)

    private fun provideAddressRepository(createAddressTask: CreateAddressTask, addressDAO: AddressDAO) =
        AddressRepository(createAddressTask, addressDAO)

    private fun provideAddressViewModel(context: Context, addressRepository: AddressRepository) =
        AddressViewModel(context, addressRepository)

    fun setupAddress() = module {

        single {
            provideAPI(get(named("noAuth")))
            provideCreateAddressTask(get(), androidContext())
            provideAddressRepository(get(),get())
            provideAddressViewModel(get(), get())
        }

    }
}