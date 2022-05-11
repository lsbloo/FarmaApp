package com.farma.poc.features.home.data.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ItemsHomeDTO(
    val categories: List<CategoryDTO>? = null,
    val productsHighLight: List<HightLightsProductDTO>? = null,
    @PrimaryKey
    val id: Long = 1,
) : Parcelable {
}
