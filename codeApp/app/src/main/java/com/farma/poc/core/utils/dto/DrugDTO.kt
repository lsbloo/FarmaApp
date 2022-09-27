package com.farma.poc.core.utils.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class DrugDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var activePrinciple: String? = null,
    var code: String? = null,
    var barCode: String? = null,
    var quantity: Int? = 0,
    var manufacturer: String? = null,
    var ean1: String? = null,
    var ean2: String? = null,
    var restriction: String? = null,
    var apresentation: String? = null,
    var classe: String? = null,
    var tarja: String? = null,
    var canSell: Boolean = false,
    var isAntibiotic: Boolean = false,
    var pmc_20: String? = null,
) : Parcelable