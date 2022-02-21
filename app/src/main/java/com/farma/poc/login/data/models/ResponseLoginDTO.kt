package com.farma.poc.login.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseLoginDTO(
    val bearerToken: String? = null,
    val dataExpires: String? = null
): Parcelable
