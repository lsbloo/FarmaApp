package com.farma.poc.featuresApp.compose.settings.home.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class GetSettingsDTO(
    var labelButtonLoggout: String? = null,
    var labelVersionApp: String? = null,
    var nameUser: String? = null,
    var labelOrder: String? = null,
    var labelInfoUser: String? = null,
    var labelAsks: String? = null,
    var labelMethodPayment: String? = null,
    var labelAddress: String? = null,
    var labelCloseAccount: String? = null,
    var genre: String? = null,
    var labelBiometric: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 1
) : Parcelable