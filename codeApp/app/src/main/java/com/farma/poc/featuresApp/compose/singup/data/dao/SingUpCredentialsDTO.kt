package com.farma.poc.featuresApp.compose.singup.data.dao

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SingUpCredentialsDTO(
    val name: String,
    val cpf: String,
    val email: String,
    val password: String
) : Parcelable {

}
