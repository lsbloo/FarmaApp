package com.farma.poc.featuresApp.compose.settings.home.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class GetSettingsDTO(
    val labelButtonLogout: String? = null,
    val labelVersionApp: String? = null,
    val nameUser: String? = null,
    val labelOrder: String? = null,
    val labelInfoUser: String? = null,
    val labelAsks: String? = null,
    val labelMethodPayment: String? = null,
    val labelAddress: String? = null,
    val labelCloseAccount: String? = null,
    val genre: String? = null,
    val labelBiometric: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 1
) : Parcelable