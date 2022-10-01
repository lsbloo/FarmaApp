package com.farma.poc.featuresApp.compose.address.data.api

import com.farma.poc.core.utils.dto.MessageClientResponseDTO
import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.address.data.models.GeoLocateDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressAPI {


    @POST(CREATE_ADDRESS)
    suspend fun createAddress(@Body addressDTO: AddressDTO): Response<MessageClientResponseDTO>


    @GET(GET_LAT_LGN)
    suspend fun getGeoLocate(@Query("address") address: String): Response<GeoLocateDTO>


    companion object {
        private const val CREATE_ADDRESS = "/address"
        private const val GET_LAT_LGN = "/api/geolocation"
    }

}