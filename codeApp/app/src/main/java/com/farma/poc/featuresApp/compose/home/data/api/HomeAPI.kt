package com.farma.poc.featuresApp.compose.home.data.api

import com.farma.poc.featuresApp.compose.home.constants.HomeConstants
import com.farma.poc.featuresApp.compose.home.data.models.CategoryDTO
import com.farma.poc.featuresApp.compose.home.data.models.HightLightsProductDTO
import com.farma.poc.featuresApp.compose.home.data.models.ItemsHomeDTO
import retrofit2.Response
import retrofit2.http.GET

interface HomeAPI {


    @GET(HomeConstants.API.ENDPOINT_GET_CATEGORIES)
    suspend fun getCategories(): Response<CategoryDTO>

    @GET(HomeConstants.API.ENDPOINT_GET_HIGHLIGHT_PRODUCTS)
    suspend fun getHighLightsProducts(): Response<HightLightsProductDTO>

    @GET(HomeConstants.API.ENDPOINT_GET_ITEMS_HOME)
    suspend fun getItemsHome(): Response<ItemsHomeDTO?>


}