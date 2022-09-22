package com.farma.poc.featuresApp.compose.home.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.utils.dto.ProductDTO
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ItemsHomeDTO(
    val categories: List<CategoryDTO>? = null,
    val productsHighLight: List<ProductDTO>? = null,
    val highLights: List<ProductDTO>? = null,
    @PrimaryKey
    val id: Long = 1,
) : Parcelable {
}
