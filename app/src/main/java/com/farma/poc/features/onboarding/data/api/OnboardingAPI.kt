package com.farma.poc.features.onboarding.data.api

import com.farma.poc.features.onboarding.constants.OnboardingConstants
import com.farma.poc.features.onboarding.data.models.OnboardingDTO
import retrofit2.Response
import retrofit2.http.GET

interface OnboardingAPI {


    @GET(OnboardingConstants.API.ENDPOINT_GET_ONBOARDING)
    suspend fun getOnboarding(): Response<OnboardingDTO?>

}