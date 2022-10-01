package com.farma.poc.featuresApp.compose.settings.home.data.api

import com.farma.poc.core.utils.dto.MessageClientResponseDTO
import com.farma.poc.featuresApp.compose.settings.home.constants.SettingsConstants
import com.farma.poc.featuresApp.compose.settings.home.data.model.GetSettingsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SettingsAPI {


    @GET(SettingsConstants.API.GET_DATA_SETTINGS)
    suspend fun getDataScreenSettings(@Header("accessToken") accessToken: String): Response<MessageClientResponseDTO?>
}