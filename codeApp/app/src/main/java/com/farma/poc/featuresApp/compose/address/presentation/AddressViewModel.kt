package com.farma.poc.featuresApp.compose.address.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.config.constants.ConfigApplicationConstants
import com.farma.poc.core.config.data.provider.OnDataProviderSettingsNavigation
import com.farma.poc.core.navigation.OnRouterActionNavigationListener
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.utils.removeAcents
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.address.data.repository.AddressRepository
import com.farma.poc.featuresApp.compose.address.validators.interfaces.AddressValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddressViewModel(
    context: Context,
    private val addressRepository: AddressRepository,
    private val addressValidator: AddressValidator<PojoValidator>,
) : BaseViewModel(context) {

    var selectAddressPrincipalChecked = mutableStateOf(false)

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

    var stateCheckedSwitch =
        mutableStateOf(false)

    var detailAddressSelectedTmp = mutableStateOf(AddressDTO())

    private var addressesUser = mutableStateOf<List<AddressDTO>?>(null)

    init {

        getAllAddress()

    }


    fun observeAsActionRedirect() {
        routerNavigation?.setRouterActionNavigationListener(object :
            OnRouterActionNavigationListener {
            override fun anActionWasTriggered() {
                getActionSettingsToAddressNavigation()
                getAllAddress()
            }
        })
    }

    private fun getAllAddress() {
        getClientToken { clientToken ->
            viewModelScope.launch {
                addressRepository.getAddresses(
                    onSuccessData = {
                        it?.let { data ->
                            addressesUser.value = data
                        }
                    },
                    onFailureError = {

                    }, onShowLoading = {
                        showLoadingPage.value = it
                    }, errorNetWorkNotAvailablex = {

                    },
                    address = AddressDTO().apply { client_id_token = clientToken }
                )
            }
        }
    }

    fun deleteAddress(addressDTO: AddressDTO) {
        getClientToken {
            viewModelScope.launch {
                addressRepository.deleteAddress(onSuccessData = {
                    if (it == HTTPStatusResponse.STATUS_ACCEPTED) {
                        getAllAddress()
                    }
                }, onShowLoading = {
                    showLoadingPage.value = it
                }, onFailureError = {


                }, errorNetWorkNotAvailablex = {

                },
                    address = addressDTO.apply { client_id_token = it }
                )
            }
        }
    }

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
                            getLocateByAddressAndCreateAddressWithResponseAsSuccess(
                                street.value,
                                number.value,
                                city.value,
                                description.value,
                                district.value
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

    private fun getLocateByAddressAndCreateAddressWithResponseAsSuccess(
        streetx: String,
        numberx: String,
        cityx: String,
        descriptionx: String,
        districtx: String
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
                            number = "$districtx-$numberx"
                            city = cityx
                            client_id_token = clientToken
                            description = descriptionx
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
                    if (it?.httpStatusCode == HTTPStatusResponse.STATUS_ACCEPTED) {
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

    fun redirectToSettings() {
        routerNavigation?.navigateTo(RouterNavigationEnum.SETTINGS)
    }

    fun redirectToCreateAddress() {
        routerNavigation?.navigateTo(RouterNavigationEnum.ADDRESS)
    }

    fun redirectToDetailAddressWithPersistAddressTemp(addressDTO: AddressDTO) {
        detailAddressSelectedTmp.value = addressDTO
        addressDTO.isPrincipal?.let {
            stateCheckedSwitch.value = it
        }
        routerNavigation?.navigateTo(RouterNavigationEnum.DETAIL_ADDRESS)

    }

    private fun persistAddressDetailTmp(addressDTO: AddressDTO) {
        viewModelScope.launch {
            addressDTO.id?.let {
                getDataStoreConfig().setAddressDetailTmp(
                    address = gson.toJson(
                        addressDTO
                    )
                )
            }
        }
    }

    private fun persistAddressDetail(addressDTO: AddressDTO) {
        viewModelScope.launch {
            addressDTO.id?.let {
                getDataStoreConfig().setAddressDetailSelected(
                    address = gson.toJson(
                        addressDTO
                    )
                )
            }
        }
    }

    fun getAddressDetail(onAddressSelected: (AddressDTO?) -> Unit) {
        viewModelScope.launch {
            getDataStoreConfig().sharedAddressDetailFlow.collect { data ->
                if (data.isEmpty()) {
                    Log.d("Data Address Selected", "NOT RECOVERY DATA ADDRESS SELECTED DETAIL")
                } else {
                    val addressSelected = transformCollectToAddressDTO(data)
                    Log.d("AddressDetailPressed", "" + addressSelected.id)
                    addressSelected.isPrincipal?.let {
                        stateCheckedSwitch.value = it
                    }
                    onAddressSelected.invoke(addressSelected)
                }
            }

        }
    }

    fun getAllAddresses(): List<AddressDTO> {
        val addressList = ArrayList<AddressDTO>()
        addressesUser.value?.forEach { address ->
            addressList.add(address)
        }
        Log.d("addressReturned", "" + addressList.size)
        return addressList
    }

    fun setAddressPrincipal(addressDTO: AddressDTO?, v: Boolean) {
        addressDTO?.let { addressSelect ->
            selectAddressPrincipalChecked.value = v
            Log.d("AddressSelect", "" + addressSelect.id)
            getClientToken {
                addressDTO.client_id_token = it
                addressSelect.isPrincipal = v
                selectAddressToPrincipalCall(addressDTO)
            }
        }
    }

    private fun selectAddressToPrincipalCall(addressDTO: AddressDTO) {
        viewModelScope.launch {
            addressRepository.selectAddressPrincipal(
                onSuccessData = {
                    if (it != null) {
                        persistAddressDetail(it)
                    }
                },
                onShowLoading = {
                    showLoadingPage.value = it
                },
                errorNetWorkNotAvailablex = {},
                address = addressDTO
            )
        }
    }

    private fun getActionSettingsToAddressNavigation() {
        routerNavigation?.recoveryActionWithNavigate(dataProvider = OnDataProviderSettingsNavigation(
            ConfigApplicationConstants.DataProviderActionsRedirect.REDIRECT_ACTION_SETTINGS_TO_ADDRESS,
            false
        ), onResult = {

        })
    }
}