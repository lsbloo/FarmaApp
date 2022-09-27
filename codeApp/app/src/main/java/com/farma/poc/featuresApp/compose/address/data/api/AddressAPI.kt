package com.farma.poc.featuresApp.compose.address.data.api

import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import retrofit2.Response
import retrofit2.http.POST

interface AddressAPI {



    @POST(CREATE_ADDRESS)
    fun createAddress(addressDTO: AddressDTO): Response<ResponseDTO>


    companion object {
        private const val CREATE_ADDRESS = "/address"
    }

}