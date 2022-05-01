package com.farma.poc.features.home.data.api

import com.farma.poc.features.home.constants.HomeConstants
import com.farma.poc.features.home.data.models.CategoryDTO
import com.farma.poc.features.home.data.models.HightLightsProductDTO
import retrofit2.Response
import retrofit2.http.GET

interface HomeAPI {


    @GET(HomeConstants.API.ENDPOINT_GET_CATEGORIES)
    suspend fun getCategories(): Response<CategoryDTO>

    @GET(HomeConstants.API.ENDPOINT_GET_HIGHLIGHT_PRODUCTS)
    suspend fun getHighLightsProducts(): Response<HightLightsProductDTO>


}