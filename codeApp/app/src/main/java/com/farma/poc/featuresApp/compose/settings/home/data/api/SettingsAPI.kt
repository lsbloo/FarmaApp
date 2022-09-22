package com.farma.poc.featuresApp.compose.settings.home.data.api

import com.farma.poc.featuresApp.compose.settings.home.constants.SettingsConstants
import com.farma.poc.featuresApp.compose.settings.home.data.model.GetSettingsDTO
import retrofit2.Response
import retrofit2.http.GET

interface SettingsAPI {


    @GET(SettingsConstants.API.GET_DATA_SETTINGS)
    suspend fun getDataScreenSettings(): Response<GetSettingsDTO?>
}