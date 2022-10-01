package com.farma.poc.featuresApp.compose.address.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.utils.removeAcents
import com.farma.poc.core.utils.safeLet
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.featuresApp.compose.address.data.repository.AddressRepository
import com.farma.poc.featuresApp.compose.address.validators.interfaces.AddressValidator
import com.farma.poc.core.utils.safeLet
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import kotlinx.coroutines.launch

class AddressViewModel(
    context: Context,
    private val addressRepository: AddressRepository,
    private val addressValidator: AddressValidator<PojoValidator>,
) : BaseViewModel(context) {


    var street = mutableStateOf("")
    var number = mutableStateOf("")
    var district = mutableStateOf("")
    var city = mutableStateOf("")
    var description = mutableStateOf("")

    var messageErrorStreet = mutableStateOf("")
    var isErrorStreet = mutableStateOf(false)

    var messageErrorNumber = mutableStateOf("")
    var isErrorNumber = mutableStateOf(false)

    var messageErrorDistrict = mutableStateOf("")
    var isErrorDistrict = mutableStateOf(false)

    var messageErrorCity = mutableStateOf("")
    var isErrorCity = mutableStateOf(false)


    var showErrorFeedBack = mutableStateOf(false)

    var showLoadingPage = mutableStateOf(false)

    var showToastSuccess = mutableStateOf(false)


    fun createAddressUser() {
        addressValidator.validateCredentialsAddress(
            street = street.value,
            number = number.value,
            district = district.value,
            city = city.value,
            description = description.value,
            onFailure = {},
            onValidate = {

                it.data?.let { validator ->
                    validator.dontHasError?.let { result ->
                        if (result) {

                            messageErrorStreet.value =
                                validator.dataMessages?.first()?.second.toString()

                            validator.dataMessages?.first()?.third?.let { error ->
                                isErrorStreet.value = error
                            }

                            messageErrorNumber.value =
                                validator.dataMessages?.get(1)?.second.toString()

                            validator.dataMessages?.get(1)?.third?.let { error ->
                                isErrorNumber.value = error
                            }

                            messageErrorDistrict.value =
                                validator.dataMessages?.get(2)?.second.toString()

                            validator.dataMessages?.get(2)?.third?.let { error ->
                                isErrorDistrict.value = error
                            }

                            messageErrorCity.value =
                                validator.dataMessages?.last()?.second.toString()

                            validator.dataMessages?.last()?.third?.let { error ->
                                isErrorCity.value = error
                            }


                        } else {
                            getLocateByAddress(
                                street.value,
                                district.value,
                                city.value
                            )
                        }
                    }
                }
            }
        )
    }

    private fun showErrorFeedBack() {
        showErrorFeedBack.value = true
    }

    private fun getLocateByAddress(
        streetx: String,
        numberx: String,
        cityx: String
    ) {
        val address = "$streetx,$cityx".removeAcents()

        viewModelScope.launch {
            addressRepository.getGeoLocateTask(
                onSuccessData = { dataLocate ->

                    getClientToken(onResult = { clientToken ->
                        val addressDTO = AddressDTO().apply {
                            latitude = dataLocate?.geometry?.location?.lat
                            longitude = dataLocate?.geometry?.location?.lng
                            street = streetx
                            number = numberx
                            city = cityx
                            client_id_token = clientToken
                        }
                        createAddressByLocate(addressDTO)
                    })

                }, onFailureError = {
                }, onShowLoading = {
                    showLoadingPage.value = it
                }, address = address, errorNetWorkNotAvailablex = {})
        }

    }

    private fun createAddressByLocate(addressDTO: AddressDTO) {

        viewModelScope.launch {
            addressRepository.createAddress(
                onSuccessData = {
                    if (it?.httpStatusCode == 202L) {
                        showToastSuccess.value = true
                    }
                },
                onShowLoading = {
                    showLoadingPage.value = it
                },
                onFailureError = {},
                errorNetWorkNotAvailablex = {},
                dto = addressDTO
            )
        }
    }

    fun redirectToHome() {
        routerNavigation?.navigateTo(RouterNavigationEnum.HOME)
    }
}