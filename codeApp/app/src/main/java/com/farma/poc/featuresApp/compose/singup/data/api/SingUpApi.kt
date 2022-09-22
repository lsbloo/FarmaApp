package com.farma.poc.featuresApp.compose.singup.data.api

import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.featuresApp.compose.singup.constants.SingUpConstants.API.SING_UP_ENDPOINT
import com.farma.poc.featuresApp.compose.singup.data.dao.SingUpCredentialsDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SingUpApi {


    @POST(SING_UP_ENDPOINT)
    suspend fun registerAccount(@Body singUpCredentialsDTO: SingUpCredentialsDTO): Response<ResponseDTO?>
}