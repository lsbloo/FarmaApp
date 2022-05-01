package com.farma.poc.features.singup.data.dao

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
