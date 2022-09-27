package com.farma.poc.core.utils.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDTO(
    var message: String? = null,
    var description: String? = null,
    var isError: Boolean? = false,
    var httpStatusCode: Long? = null,
    var messageClientResponseDTO: MessageClientResponseDTO? = null
): Parcelable


@Parcelize
data class MessageClientResponseDTO(
    var title: String? = null,
    var description: String? = null,
    var httpStatusCode: Long? = null,
    var responseDTO: String? = null
): Parcelable