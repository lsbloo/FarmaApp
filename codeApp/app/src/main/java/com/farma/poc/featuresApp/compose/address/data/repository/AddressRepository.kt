package com.farma.poc.featuresApp.compose.address.data.repository

import com.farma.poc.core.utils.dto.MessageClientResponseDTO
import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.featuresApp.compose.address.data.dao.AddressDAO
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.address.data.models.GeoLocateDTO
import com.farma.poc.featuresApp.compose.address.data.task.*
import okhttp3.ResponseBody

class AddressRepository(
    private val createAddressTask: CreateAddressTask,
    private val getGeoLocateTask: GetLatLngAddressTask,
    private val getAddressListTask: GetAddressListTask,
    private val deleteAddressTask: DeleteAddressTask,
    private val selectAddressPrincipalTask: SelectAddressPrincipalTask,
    private val addressDAO: AddressDAO
) {


    suspend fun deleteAddress(
        onSuccessData: (Long) -> Unit,
        onFailureError: (ResponseBody?) -> Unit,
        onShowLoading: (Boolean) -> Unit,
        errorNetWorkNotAvailablex: () -> Unit,
        address: AddressDTO
    ) {
        deleteAddressTask.call(
            e = address,
            callback = { onSuccess, onFailure, onShouldLoading ->
                onSuccess?.data?.let {
                    onSuccessData.invoke(it)
                }
                onFailure?.data.let {
                    onFailureError.invoke(it)
                }
                if (onShouldLoading != null) {
                    onShowLoading.invoke(onShouldLoading)
                }
            },
            errorNetWorkNotAvailable = {
                errorNetWorkNotAvailablex.invoke()
            }
        )
    }

    suspend fun getAddresses(
        onSuccessData: (List<AddressDTO>?) -> Unit,
        onFailureError: ((ResponseBody?) -> Unit) ?= null,
        onShowLoading: (Boolean) -> Unit,
        errorNetWorkNotAvailablex: () -> Unit,
        address: AddressDTO
    ) {
        getAddressListTask.call(
            e = address,
            callback = { onSuccess, onFailure, onShouldLoading ->
                onSuccess?.data.let {
                    onSuccessData.invoke(it)
                }
                onFailure?.data.let {
                    onFailureError?.invoke(it)
                }
                if (onShouldLoading != null) {
                    onShowLoading.invoke(onShouldLoading)
                }
            },
            errorNetWorkNotAvailable = {
                errorNetWorkNotAvailablex.invoke()
            }
        )
    }

    suspend fun getGeoLocateTask(
        onSuccessData: (GeoLocateDTO?) -> Unit,
        onFailureError: (ResponseBody?) -> Unit,
        onShowLoading: (Boolean) -> Unit,
        errorNetWorkNotAvailablex: () -> Unit,
        address: String
    ) {
        getGeoLocateTask.call(
            e = address,
            callback = { onSuccess, onFailure, onShouldLoading ->

                onSuccess?.data?.let {
                    onSuccessData.invoke(it)
                }
                onFailure?.data.let {
                    onFailureError.invoke(it)
                }
                if (onShouldLoading != null) {
                    onShowLoading.invoke(onShouldLoading)
                }
            },
            errorNetWorkNotAvailable = {
                errorNetWorkNotAvailablex.invoke()
            }
        )
    }

    suspend fun createAddress(
        onSuccessData: (MessageClientResponseDTO?) -> Unit,
        onFailureError: (ResponseBody?) -> Unit,
        onShowLoading: (Boolean) -> Unit,
        errorNetWorkNotAvailablex: () -> Unit,
        dto: AddressDTO
    ) {

        createAddressTask.call(
            e = dto,
            callback = { onSuccess, onFailure, onShouldLoading ->

                onSuccess?.data?.let {
                    saveAddress(dto)
                    onSuccessData.invoke(it)
                }
                onFailure?.data.let {
                    onFailureError.invoke(it)
                }
                if (onShouldLoading != null) {
                    onShowLoading.invoke(onShouldLoading)
                }
            },
            errorNetWorkNotAvailable = {
                errorNetWorkNotAvailablex.invoke()
            }
        )
    }

    private fun saveAddress(addressDTO: AddressDTO) {
        addressDAO.insertAddress(addressDTO)
    }

    suspend fun getAllAddresses() = addressDAO.getAllAddress()


    suspend fun selectAddressPrincipal(
        onSuccessData:  (AddressDTO?) -> Unit,
        onFailureError: ((ResponseBody?) -> Unit) ?= null,
        onShowLoading: (Boolean) -> Unit,
        errorNetWorkNotAvailablex: () -> Unit,
        address: AddressDTO
    ) {
        selectAddressPrincipalTask.call(
            e = address,
            callback = { onSuccess, onFailure, onShouldLoading ->
                onSuccess?.data.let {
                    onSuccessData.invoke(it)
                }
                onFailure?.data.let {
                    onFailureError?.invoke(it)
                }
                if (onShouldLoading != null) {
                    onShowLoading.invoke(onShouldLoading)
                }
            },
            errorNetWorkNotAvailable = {
                errorNetWorkNotAvailablex.invoke()
            }
        )

    }

}