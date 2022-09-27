package com.farma.poc.featuresApp.compose.address.presentation

import android.content.Context
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.featuresApp.compose.address.data.repository.AddressRepository

class AddressViewModel(
    context: Context,
    private val addressRepository: AddressRepository
) : BaseViewModel(context) {


    fun createAddressUser() {

    }
}