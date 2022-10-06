package com.farma.poc.featuresApp.compose.address.data.di

import android.content.Context
import com.farma.poc.core.config.data.FarmaAppDatabase
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.featuresApp.compose.address.data.api.AddressAPI
import com.farma.poc.featuresApp.compose.address.data.dao.AddressDAO
import com.farma.poc.featuresApp.compose.address.data.repository.AddressRepository
import com.farma.poc.featuresApp.compose.address.data.task.*
import com.farma.poc.featuresApp.compose.address.presentation.AddressViewModel
import com.farma.poc.featuresApp.compose.address.validators.AddressValidatorImpl
import com.farma.poc.featuresApp.compose.address.validators.interfaces.AddressValidator
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.math.sin

object AddressSetup {


    private fun provideAPI(retrofit: Retrofit) = retrofit.create(AddressAPI::class.java)

    private fun provideCreateAddressTask(addressAPI: AddressAPI, context: Context) =
        CreateAddressTask(addressAPI, context)

    private fun provideDeleteAddressTask(addressAPI: AddressAPI, context: Context) =
        DeleteAddressTask(addressAPI, context)

    private fun provideSelectAddressTask(addressAPI: AddressAPI, context: Context) =
        SelectAddressPrincipalTask(addressAPI, context)

    private fun provideGetGeoLocateTask(addressAPI: AddressAPI, context: Context) =
        GetLatLngAddressTask(addressAPI, context)

    private fun provideGetAddressListTask(addressAPI: AddressAPI, context: Context) =
        GetAddressListTask(addressAPI, context)


    private fun provideAddressRepository(
        createAddressTask: CreateAddressTask,
        geoLocateTask: GetLatLngAddressTask,
        getAddressListTask: GetAddressListTask,
        deleteAddressTask: DeleteAddressTask,
        selectAddressPrincipalTask: SelectAddressPrincipalTask,
        databaseInstance: FarmaAppDatabase
    ) =
        AddressRepository(
            createAddressTask,
            geoLocateTask,
            getAddressListTask,
            deleteAddressTask,
            selectAddressPrincipalTask,
            databaseInstance.addressDao()
        )

    private fun provideAddressViewModel(
        context: Context,
        addressRepository: AddressRepository,
        addressValidator: AddressValidator<PojoValidator>
    ) =
        AddressViewModel(context, addressRepository, addressValidator)

    private fun provideAddressValidator() = AddressValidatorImpl<PojoValidator>()

    fun setupAddress() = module {
        single {
            provideAPI(get(named("noAuth")))

        }

        single {
            provideAddressValidator() as AddressValidator<PojoValidator>
        }
        single {
            provideGetAddressListTask(get(), androidContext())
        }
        single {
            provideSelectAddressTask(get(),androidContext())
        }

        single {
            provideDeleteAddressTask(get(), androidContext())
        }

        single {
            provideCreateAddressTask(get(), androidContext())
        }
        single {
            provideGetGeoLocateTask(get(), androidContext())
        }

        single {
            provideAddressRepository(get(), get(), get(), get(),get(),get())
        }

        single {
            provideAddressViewModel(get(), get(), get())
        }

    }
}