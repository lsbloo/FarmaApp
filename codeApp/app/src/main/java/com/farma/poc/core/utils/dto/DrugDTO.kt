package com.farma.poc.core.utils.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class DrugDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val activePrinciple: String? = null,
    val code: String? = null,
    val barCode: String? = null,
    val quantity: Int? = 0,
    val manufacturer: String? = null,
    val ean1: String? = null,
    val ean2: String? = null,
    val restriction: String? = null,
    val apresentation: String? = null,
    val classe: String? = null,
    val tarja: String? = null,
    val canSell: Boolean = false,
    val isAntibiotic: Boolean = false,
    val pmc_20: String? = null,
) : Parcelable