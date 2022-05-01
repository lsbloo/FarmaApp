package com.farma.poc.features.singup.data.api

import com.farma.poc.core.utils.dto.ResponseDTO
import com.farma.poc.features.singup.constants.SingUpConstants.API.SING_UP_ENDPOINT
import com.farma.poc.features.singup.data.dao.SingUpCredentialsDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*

interface SingUpApi {


    @POST(SING_UP_ENDPOINT)
    suspend fun registerAccount(@Body singUpCredentialsDTO: SingUpCredentialsDTO): Response<ResponseDTO?>
}