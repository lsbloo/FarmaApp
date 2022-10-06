package com.farma.poc.featuresApp.compose.address.data.api

import com.farma.poc.core.utils.dto.MessageClientResponseDTO
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.address.data.models.GeoLocateDTO
import retrofit2.Response
import retrofit2.http.*

interface AddressAPI {


    @POST(CREATE_ADDRESS)
    suspend fun createAddress(@Body addressDTO: AddressDTO): Response<MessageClientResponseDTO>


    @GET(GET_LAT_LGN)
    suspend fun getGeoLocate(@Query("address") address: String): Response<GeoLocateDTO>

    @GET(GET_ALL_ADDRESS)
    suspend fun getAddresses(@Query("client_id_token") token: String): Response<MessageClientResponseDTO>

    @DELETE(CREATE_ADDRESS)
    suspend fun deleteAddress(
        @Query("client_id_token") token: String,
        @Query("address_id") id: Long
    ): Response<MessageClientResponseDTO>

    @GET(SELECT_ADDRESS_PRINCIPAL)
    suspend fun selectAddressPrincipal(
        @Query("client_id_token") token: String,
        @Query("address_id") id: Long,
        @Query("is_principal") isPrincipal: Boolean
    ): Response<MessageClientResponseDTO>

    companion object {
        private const val CREATE_ADDRESS = "/address"
        private const val GET_LAT_LGN = "/api/geolocation"
        private const val GET_ALL_ADDRESS = "/address/all"
        private const val SELECT_ADDRESS_PRINCIPAL = "/address/select"
    }

}