package com.farma.poc.core.utils.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDTO(
    val message: String? = null,
    val description: String? = null,
    val isError: Boolean? = false
): Parcelable
